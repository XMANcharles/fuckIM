package com.honeybadger.im.fuck.user.service.impl;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.entity.User;
import com.honeybadger.im.fuck.user.service.UserService;
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
public class UserServiceImpl implements UserService{
    /**
     * 默认初始一个好友分组(我的好友)
     */
    private final static String MY_GOOD_FRIEND = "我的好友";
    /**
     * 初始化在线状态
     */
    private final static User.Status INIT_STATUS = User.Status.ONLINE;
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
    private GroupFriendsServiceImpl groupFriendsService;

    @Override
    public boolean registerUser(String username,String password){
        if(username == null || "".equals(username.trim())){
            return false;
        }
        if(password == null || "".equals(password.trim())){
            return false;
        }
        if(username.length() > 8){
            return false;
        }
        if(password.length() > 16){
            return false;
        }
        String userUUID = Uuid.getUUID();
        User user = new User(userUUID, username, new BCryptPasswordEncoder().encode(password), INIT_STATUS, INIT_SIGN, INIT_AVATAR);
        user = userRepository.save(user);
        //为用户初始化两个好友分组->"我的好友"
        groupFriendsService.addGroup(userUUID,MY_GOOD_FRIEND);
        //初始化用户权限
        //敲你🐴🐶🐍🐮 jpa怎么插中间表数据
        return true;
    }

}
