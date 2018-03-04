package com.honeybadger.im.fuck.user.service.impl;

import com.honeybadger.im.fuck.tool.UUIDUtil;
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

    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Autowired
    private UserRelationalRepository userRelationalRepository;


    @Override
    public void addGroup(String userId,String groupName){
        groupFriendsRepository.save(new GroupFriends(UUIDUtil.getUUID(),userId,groupName));
    }

    @Override
    public boolean deleteGroup(String groupId){
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
