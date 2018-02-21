package com.honeybadger.im.fuck.user.service;

/**
 * @author zcolder
 * @date 2018/02/21
 */
public interface UserService {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 用户密码
     */
    void userRegistration(String username,String password);

}
