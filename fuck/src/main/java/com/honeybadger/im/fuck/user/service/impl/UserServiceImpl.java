package com.honeybadger.im.fuck.user.service.impl;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.dao.UserRoleRepository;
import com.honeybadger.im.fuck.user.entity.User;
import com.honeybadger.im.fuck.user.entity.UserRole;
import com.honeybadger.im.fuck.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑层
 * 所有固定的变量都应该被声明为private final static (凡是这样声明的变量，其作用都是'只读'所有用户共享内存中的值)
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

    /**
     * 默认用户权限等级
     */
    private final static String DEFAULT_ROLE = "0003";
    /**
     * 默认的用户名非英文长度/暂时搁置，应该用正则表达式解决
     */
    private final static int MAX_NOENGLISH_USERNAME_LENGTH = 8;
    /**
     * 默认的用户名最大英文长度/暂时搁置，应该用正则表达式解决
     */
    private final static int MAX_ENGLISH_USERNAME_LENGTH = 20;
    /**
     * 最大的密码长度
     */
    private final static int MAX_PASSWORD_LENGTH = 20;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupFriendsServiceImpl groupFriendsService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public boolean registerUser(String username, String passwordOne, String passwordTwo){
        //用户名是否为空
        if(username == null || "".equals(username.trim())){
            return false;
        }
        //密码是否为空
        if("".equals(passwordOne.trim())){
            return false;
        }
        //密码是否相同
        if(!passwordOne.equals(passwordTwo)){
            return false;
        }
        //最大非英文名长度
        if(username.length() > MAX_NOENGLISH_USERNAME_LENGTH){
            return false;
        }
        //最大密码长度
        if(passwordOne.length() > MAX_PASSWORD_LENGTH){
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
