package com.honeybadger.im.fuck.user.service.impl;

import com.honeybadger.im.fuck.tool.UUIDUtil;
import com.honeybadger.im.fuck.tool.UserRelationalStatus;
import com.honeybadger.im.fuck.user.dao.UserRelationalRepository;
import com.honeybadger.im.fuck.user.service.UserRelationalService;
import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 好友关系暂不处理(02/17)
 * @author zcolder
 * @date 2018/02/12
 */
@Service
public class UserRelationalServiceImpl implements UserRelationalService{
    /**
     * 没有任何好友备注
     */
    private static final String NULL_OF_STRING = "null";
    /**
     * 没有好友分组
     */
    private static final String NULL_GROUP = "null";

    /**
     * 黑名单
     */
    private static final String BLACK_LIST = "black_list";

    @Autowired
    private UserRelationalRepository userRelationalRepository;

    @Override
    public void userRequestsToAddFriends(String userId,String groupId,String friendId){
        //为申请方添加好友关系 applicant(申请人)
        UserRelational applicant = new UserRelational(
                UUIDUtil.getUUID(),
                userId,friendId,
                NULL_OF_STRING,groupId,
                UserRelationalStatus.GoodFriend.getValue()
        );
        userRelationalRepository.save(applicant);
        /*
            为被申请方添加好友关系 respondent(被调查者)
            这是强制添加的方法，意识着各个用户没有 管理被添加的权限
        */
        UserRelational respondent = new UserRelational(
                UUIDUtil.getUUID(),
                friendId,userId,
                NULL_OF_STRING,NULL_GROUP,
                UserRelationalStatus.Stranger.getValue()
        );
        userRelationalRepository.save(respondent);
    }

    @Deprecated
    @Override
    public void alterUserRelational(String userId, String groupId, String friendId, String status){
        userRelationalRepository.modifyUserRelationships(userId, groupId, friendId, status);
    }

    @Override
    public void transferGroup(String userId, String fromGroupId, String toGroupId){
        userRelationalRepository.groupFriendTransfer(userId, fromGroupId, toGroupId);
    }

    @Override
    public void shieldingUsers(String userId, String shieldingId){
        /*
        这里需要判断两用户间在这之前是否已经产生了用户关系
        有则修改(update)用户关系
        没有就插入(insert)用户关系
         */

    }

}
