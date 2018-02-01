package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserDao;
import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 这个类将用来检测注册用户合法性
 *
 * @author zcolder
 * @date 2018/02/01
 */
public class UserService {

    @Autowired
    private UserDao userDao;

    public String userRegistration(String username,String password){
        return "";
    }
}
