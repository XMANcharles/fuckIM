/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: UserController
 * Author:   Neo Geng
 * Date:     2018/1/20 22:45
 * Description: UserController
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.user.controller;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.entity.User;
import com.honeybadger.im.fuck.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈UserController〉
 *
 * @author Neo Geng
 * Date 2018/1/20
 * @since 1.0.0
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取User
     * @param id UserId
     * @return User-如果找到
     */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable String id){
        return userRepository.getOne(id);
    }

    /**
     * 获取Uses
     * @return 所有用户集合
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 用户注册（写一半）
     * {@link Uuid 生成uuid的工具}
     * @param username 用户名
     * @param passwordOne 用户密码
     * @param passwordTwo 再次确认
     * @return {@code true}注册成功
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public boolean userRegistration(@PathVariable String username,
                                 @PathVariable String passwordOne,
                                    @PathVariable String passwordTwo) throws Exception {
        userRepository.findFirstByUsername(username).orElseThrow(()->new Exception("用户已注册！"));
        return userService.registerUser(username, passwordOne, passwordTwo);
    }

}
