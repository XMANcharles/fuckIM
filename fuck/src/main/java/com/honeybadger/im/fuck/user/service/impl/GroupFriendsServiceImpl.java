package com.honeybadger.im.fuck.user.service.impl;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.GroupFriendsRepository;
import com.honeybadger.im.fuck.user.dao.UserRelationalRepository;
import com.honeybadger.im.fuck.user.service.GroupFriendService;
import com.honeybadger.im.fuck.user.vo.GroupFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户组的管理(包括用户好友的拉取)
 * @author zcolder
 * @date 2018/02/07
 */
@Service
public class GroupFriendsServiceImpl implements GroupFriendService{

    /**
     * 默认初始一个好友分组(我的好友)
     */
    private final static String MY_GOOD_FRIEND = "default_group";

    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Autowired
    private UserRelationalRepository userRelationalRepository;


    @Override
    public void addGroup(String userId,String groupName){
        groupFriendsRepository.save(new GroupFriends(Uuid.getUUID(),userId,groupName));
    }

    @Override
    public boolean deleteGroup(String groupId){
        //不可删除默认分组
        if(MY_GOOD_FRIEND.equals(groupId)){
            return false;
        }
        if(userRelationalRepository.findAllByGroupId(groupId).orElse(null).size()>=1){
            return false;
        }
        groupFriendsRepository.deleteById(groupId);
        return true;
    }

    @Override
    public void updateGroupName(String groupId,String groupName){
        groupFriendsRepository.save(new GroupFriends(null,groupId,groupName));
    }

    @Override
    public List<GroupFriends> getFriendListByUserId(String userId){
        return groupFriendsRepository.findAllByUserId(userId).orElse(null);
    }

}
