package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 这个类将用来检测注册用户合法性
 *
 * @author zcolder
 * @date 2018/02/01
 */
public class UserService {

    @Autowired
    private UserRepository userDao;

    public String userRegistration(String username,String password){
        return "";
    }
}
