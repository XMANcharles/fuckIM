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

import com.honeybadger.im.fuck.user.dao.UserDao;
import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserDao userDao;

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable String id){
        User one = userDao.getOne(id);
        return one;
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> findAll(){
        return userDao.findAll();
    }

}
