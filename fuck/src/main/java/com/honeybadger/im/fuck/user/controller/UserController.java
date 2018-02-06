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

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable String id){
        return userRepository.getOne(id);
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 用户注册（写一半）
     * {@link Uuid 生成uuid的工具}
     * @param username 用户名
     * @param password 用户密码
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void userRegistration(@PathVariable String username,
                                 @PathVariable String password){
        User user = new User();
        user.setId(Uuid.getUUID());
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
    }

}
