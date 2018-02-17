package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.tool.UserRelationalStatus;
import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.GroupFriendsRepository;
import com.honeybadger.im.fuck.user.dao.UserRelationalRepository;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 好友关系暂不处理(02/17)
 * @author zcolder
 * @date 2018/02/12
 */
@Service
public class UserRelationalService {
    /**
     * 没有任何好友备注
     */
    private static final String NULL_OF_STRING = "";

    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Autowired
    private UserRelationalRepository userRelationalRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户申请添加好友
     * 这只是申请，并不会确立好友关系，
     * 关系表中的uuid都是孤儿数据不需要做任何处理
     * @param userId 申请方
     * @param groupId 申请方好友分组的Id
     * @param friendId 被申请方
     */
    public void userRequestsToAddFriends(String userId,String groupId,String friendId){
        //为申请方添加好友关系 applicant(申请人)
        UserRelational applicant = new UserRelational(Uuid.getUUID(),userId,friendId, NULL_OF_STRING,groupId,UserRelationalStatus.GoodFriend.getValue());
        userRelationalRepository.save(applicant);
        //为被申请方添加好友关系 respondent(被调查者)
        UserRelational respondent = new UserRelational(Uuid.getUUID(),friendId,userId, NULL_OF_STRING,null,UserRelationalStatus.Stranger.getValue());
        userRelationalRepository.save(respondent);
    }

    /**
     * 同意申请方添加为好友，并选择该申请方的好友所在分组
     * @param userId 被申请方Id
     * @param groupId 组Id
     * @param friendId 申请方Id
     */
    @Deprecated
    public void agreeToFriendRequest(String userId,String groupId,String friendId){

    }

    /**
     * 将指定用户的指定分组好友转移向另一个分组
     * 之所以在(UserRelationalService)是因为它是对用户关系之间的一个操作，而不是对分组的操作
     * @param userId 用户Id
     * @param fromGroupId 要被转出的分组
     * @param toGroupId 要被转入的分组
     * @return {@code true}转移成功,否则{@code false}转移失败.
     */
    public void transferGroup(String userId, String fromGroupId, String toGroupId){
        userRelationalRepository.groupFriendTeleport(userId, fromGroupId, toGroupId);
    }
}
