/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: Mine
 * Author:   Neo Geng
 * Date:     2018/1/26 16:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.message.bean;

import java.io.Serializable;

/**
 * 包含我的信息
 * @author Neo Geng
 * @date 2018/1/20
 * @since 1.0.0
 */
public class Mine implements Serializable {
    /**
     * 我的头像
     */
    private String avatar;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 我的id
     */
    private String id;
    /**
     * 是否我发送的消息
     */
    private String mine;
    /**
     * 我的昵称
     */
    private String username;


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        this.mine = mine;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
