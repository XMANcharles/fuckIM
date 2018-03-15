package com.honeybadger.im.fuck.user.service;

/**
 * @author zcolder
 * @date 2018/02/21
 */
public interface UserService {

    /**
     * 用户注册
     * @param username 用户名
     * @param passwordOne 用户密码
     * @param passwordTwo 再次确认的密码
     * @return {@code true 注册成功}
     */
    boolean registerUser(String username,String passwordOne, String passwordTwo);

}
