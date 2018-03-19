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

import com.honeybadger.im.fuck.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    /**
     * 请求登录页面，这个View主要用来解决CSRF，传递CSRF到登录页
     * @return 逻辑视图 login
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    /**
     * 请求登录页面，这个View主要用来解决CSRF，传递CSRF到登录页
     * @return 逻辑视图 registers
     */
    @RequestMapping(value = "/registers",method = RequestMethod.GET)
    public String getRegister(){
        return "/registers";
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/registers",method = RequestMethod.POST)
    public String postRegister(String username,String passwordOne,String passwordTwo){
        userService.registerUser(username,passwordOne,passwordOne);
        return "redirect:/WebSocket.html";
    }

}
