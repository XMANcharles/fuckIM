package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 这个类将用来检测注册用户合法性
 *
 * @author zcolder
 * @date 2018/02/01
 */
@Service
public class UserService {
    /**
     * 默认初始一个好友分组(我的好友)
     */
    private final static String MY_GOOD_FRIEND = "我的好友";
    /**
     * 初始化在线状态
     */
    private final static Boolean INTT_STATUS = false;
    /**
     * 初始化个性签名
     */
    private final static String INIT_SIGN = "Fuck World！";
    /**
     * 初始化头像
     */
    private final static String INIT_AVATAR = "http://tp1.sinaimg.cn/5619439268/180/40030060651/1";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupFriendsService groupFriendsService;

    /**
     * 用户注册
     * @param username 用户名
     * @param password 用户密码
     */
    public void userRegistration(String username,String password){
        //检查用户名合法性 暂时不写
        String userUUID = Uuid.getUUID();
        User user = new User(userUUID, username, new BCryptPasswordEncoder().encode(password), INTT_STATUS, INIT_SIGN, INIT_AVATAR);
        userRepository.save(user);
        //为用户初始化两个好友分组，我的好友，黑名单
        groupFriendsService.addGroup(userUUID,MY_GOOD_FRIEND);
    }

}
