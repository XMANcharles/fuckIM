(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURI(r[2]);
        return null;
    }
})(jQuery);

/**
 * WebSocket页面配置
 */
$(function () {

    //CSRF
    var headers = {};
    var _csrf;
    $.ajax({
        url: "/csrf",
        type: "GET",
        async: false,
        success: function (data) {
            headers[data.headerName] = data.token;
            _csrf = data.token;
        }
    });

    /**
     * Layer的配置
     */
    layui.use('layim', function (layim) {
        //先来个客服模式压压精
        layim.config({
            //brief: true //是否简约模式（如果true则不显示主面板）
            init: {
                url: '/initdata' //接口地址（返回的数据格式见下文）
                , type: 'get' //默认get，一般可不填
                , data: {} //额外参数
            } //获取主面板列表信息

            //获取群员接口（返回的数据格式见下文）
            , members: {
                url: '' //接口地址（返回的数据格式见下文）
                , type: 'get' //默认get，一般可不填
                , data: {} //额外参数
            }

            //上传图片接口（返回的数据格式见下文），若不开启图片上传，剔除该项即可
            , uploadImage: {
                url: '/picture?_csrf='+_csrf //接口地址
                , type: 'post' //默认post
            }

            //上传文件接口（返回的数据格式见下文），若不开启文件上传，剔除该项即可
            , uploadFile: {
                url: '/resource/file' //接口地址
                , type: 'post' //默认post
            }

            //扩展工具栏，下文会做进一步介绍（如果无需扩展，剔除该项即可）
            , tool: [{
                alias: 'code' //工具别名
                , title: '代码' //工具名称
                , icon: '&#xe64e;' //工具图标，参考图标文档
            }]

            , msgbox: '/layui/css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
            , find: '/layui/css/modules/layim/html/find.html' //发现页面地址，若不开启，剔除该项即可
            , chatLog:  '/layui/css/modules/layim/html/chatlog.html' //聊天记录页面地址，若不开启，剔除该项即可
        });

        //开启连接
        var stompClient = null;
        // 开启socket连接
        var socket = new SockJS("http://localhost/socket");
        stompClient = Stomp.over(socket);
        stompClient.connect(headers, function (frame) {
            console.log('*****  Connected  *****');
            //订阅消息队列。。。
            stompClient.subscribe("/user/queue/position-updates", function (data) {
                //data = JSON.parse(data);
                var body = JSON.parse(data.body);
                //监听接收消息
                layim.getMessage({
                    username: body.mine.username //消息来源用户名
                    , avatar: body.mine.avatar //消息来源用户头像
                    , id: body.mine.id //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
                    , type: body.to.type //聊天窗口来源类型，从发送消息传递的to里面获取
                    , content: body.mine.content //消息内容
                    , cid: 0 //消息id，可不传。除非你要对消息进行一些操作（如撤回）
                    , mine: false //是否我发送的消息，如果为true，则会显示在右方
                    , fromid: body.mine.id //消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
                    , timestamp: new Date().getTime() //服务端时间戳毫秒数。注意：如果你返回的是标准的 unix 时间戳，记得要 *1000});
                });
            });
        });
        //发送消息监听
        layim.on('sendMessage', function (res) {
            //包含我发送的消息及我的信息
            var mine = res.mine;
            /*mine的结构如下：
            {
                avatar: "avatar.jpg" //我的头像
                ,content: "你好吗" //消息内容
                ,id: "100000" //我的id
                ,mine: true //是否我发送的消息
                ,username: "纸飞机" //我的昵称
            }*/
            var to = res.to; //对方的信息
            //to的结构如下：
            /*{
                avatar: "avatar.jpg"
                ,id: "100001"
                ,name: "贤心"
                ,sign: "这些都是测试数据，实际使用请严格按照该格式返回"
                ,type: "friend" //聊天类型，一般分friend和group两种，group即群聊
                ,username: "贤心"
            }*/
            var message = JSON.stringify(res);
            stompClient.send("/app/message/send", {}, message);
        });
    });
});

