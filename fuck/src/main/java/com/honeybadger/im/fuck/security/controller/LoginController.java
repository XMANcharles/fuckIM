/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: LoginController
 * Author:   Neo Geng
 * Date:     2018/2/8 15:20
 * Description: LoginController
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈LoginController〉
 *
 * @author Neo Geng
 * Date 2018/2/8
 * @since 1.0.0
 */
@Controller
public class LoginController {

    /**
     * 请求登录页面，这个View主要用来解决CSRF，传递CSRF到登录页
     * @return 逻辑视图 login
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

}
