package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.user.dao.GroupFriendsRepository;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.vo.GroupFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelationalService {

    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Autowired
    private UserRepository userDao;

    public List<GroupFriends> getFriendListByUserId(String userId){
        return groupFriendsRepository.findAllByUserId(userId).orElse(null);
    }

}
