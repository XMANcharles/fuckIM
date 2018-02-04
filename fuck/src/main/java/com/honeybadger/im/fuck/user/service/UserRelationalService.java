package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.user.dao.UserDao;
import com.honeybadger.im.fuck.user.dao.UserRelationalDao;
import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelationalService {

    @Autowired
    private UserRelationalDao userRelationalDao;

    @Autowired
    private UserDao userDao;

    public List<UserRelational> getFriendListByUserId(String userId){
        List<UserRelational> FriendList = userRelationalDao.findAllByUserId(userId);
        for (UserRelational friend : FriendList) {
            friend.setFriendInfo(userDao.getOne(friend.getFriendId()));
        }
        return FriendList;
    }

}
