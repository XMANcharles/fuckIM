package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
     * 默认初始一个黑名单分组(黑名单)
     */
    private final static String BLACK_LIST = "黑名单";
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
    private final static String INIT_AVATAR = "路径还没有";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupFriendsService groupFriendsService;

    /**
     * 用户注册
     * @param username 用户名
     * @param password 用户密码
     * @return {@code true} 注册成功，否则{@code false}
     */
    @Transactional
    public boolean userRegistration(String username,String password){
        //检查用户名合法性 暂时不写
        String userUUID = Uuid.getUUID();
        User user = new User(userUUID, username, password, INTT_STATUS, INIT_SIGN, INIT_AVATAR);
        userRepository.save(user);
        //为用户初始化两个好友分组，我的好友，黑名单
        groupFriendsService.addGroup(userUUID,MY_GOOD_FRIEND);
        groupFriendsService.addGroup(userUUID,BLACK_LIST);
        return true;
    }

    /**
     * 检查用户名合法性
     * @param username 用户名
     * @return {@code true} 合法，否则{@code false}
     */
    @Deprecated
    public boolean checkUserName(String username){
        //用户名存在
        if(userRepository.findByUsername(username).isPresent()){
            return false;
        }
        int usernameLength = username.length();
        //用户名长度
        if (usernameLength > 32 || usernameLength < 1){
            return false;
        }
        return true;
    }


}
