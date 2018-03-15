package com.honeybadger.im.fuck.user.service.impl;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.dao.UserRoleRepository;
import com.honeybadger.im.fuck.user.entity.User;
import com.honeybadger.im.fuck.user.entity.UserRole;
import com.honeybadger.im.fuck.user.service.UserService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑层
 * @author zcolder
 * @date 2018/02/01
 */
@Service
public class UserServiceImpl implements UserService{
    /**
     * 默认初始一个好友分组(我的好友)
     */
    private final static String MY_GOOD_FRIEND = "default_group";

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

    /**
     * 默认用户权限等级
     */
    private final static String DEFAULT_ROLE = "0003";

    private final static int Max_Username_length = 8;

    private final static int Max_Password_length = 16;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupFriendsServiceImpl groupFriendsService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public boolean registerUser(String username, String passwordOne, String passwordTwo){
        if(!passwordOne.equals(passwordTwo)){
            return false;
        }
        if(username == null || "".equals(username.trim())){
            return false;
        }
        if(passwordOne == null || "".equals(passwordOne.trim())){
            return false;
        }
        if(username.length() > Max_Username_length){
            return false;
        }
        if(passwordOne.length() > Max_Password_length){
            return false;
        }
        String userUUID = Uuid.getUUID();
        User user = new User(userUUID, username, new BCryptPasswordEncoder().encode(passwordOne), INIT_STATUS, INIT_SIGN, INIT_AVATAR);
        userRepository.save(user);
        //为用户初始化两个好友分组->"我的好友"
        groupFriendsService.addGroup(userUUID,MY_GOOD_FRIEND);
        //初始化用户权限
        userRoleRepository.save(new UserRole(Uuid.getUUID(),userUUID,DEFAULT_ROLE));
       return true;
    }

}
