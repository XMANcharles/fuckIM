package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.tool.UserRelationalStatus;
import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.GroupFriendsRepository;
import com.honeybadger.im.fuck.user.dao.UserRelationalRepository;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.entity.User;
import com.honeybadger.im.fuck.user.vo.GroupFriends;
import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zcolder
 * @date 2018/02/--
 */
@Service
public class UserRelationalService {
    /**
     * 尚未成立好友关系的状态码
     * 状态在未来应该设计为一个枚举类
     * 此处不考虑了
     */
    private static final String NO = "no";

    /**
     * 没有任何好友备注
     */
    private static final String KONG = "";
    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Autowired
    private UserRelationalRepository userRelationalRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过用户ID拉取好友列表
     * @param userId 。。
     * @return 。。
     */
    public List<GroupFriends> getFriendListByUserId(String userId){
        //用户ID获取好友分组
        List<GroupFriends> groupFriendsList = groupFriendsRepository.findAllByUserId(userId).orElse(null);

        for (GroupFriends groupFriends : groupFriendsList) {
            //用好友分组的ID去获取好友关系列表
            List<UserRelational> userRelationalList = userRelationalRepository.findAllByGroupId(groupFriends.getId()).orElse(null);
            //创建一个好友列表实例
            List<User> userList = new ArrayList<>();
            for (UserRelational userRelational:userRelationalList) {
                //从好友关系中去获取对应的好友信息
                //这里本来应该用的UserVo而且是不带密码属性的
                User user = userRepository.getOne(userRelational.getFriendId());
                //手动的置空密码
                user.setPassword("");
                userList.add(user);
            }
            groupFriends.setUsers(userList);
        }
        return groupFriendsList;
    }

    /**
     * 用户申请添加好友
     * 这只是申请，并不会确立好友关系，
     * 为了性能，申请方必须在申请时就选择一个好友分组
     * 关系表中的uuid都是孤儿数据不需要做任何处理
     * @param userId 申请方
     * @param groupId 申请方好友分组的Id
     * @param friendId 被申请方
     */
    @Deprecated
    @Transactional
    public void userRequestsToAddFriends(String userId,String groupId,String friendId){
        //为申请方添加好友关系 applicant(申请人)
        UserRelational applicant = new UserRelational(Uuid.getUUID(),userId,friendId,KONG,groupId,NO);
        userRelationalRepository.save(applicant);
        //为被申请方添加好友关系 respondent(被调查者)
        UserRelational respondent = new UserRelational(Uuid.getUUID(),friendId,userId,KONG,null,NO);
        userRelationalRepository.save(respondent);
        /*
        这里本身需要做一件事，但没有现在不考虑了
        这里在发起申请的时候，被申请方虽然是薛定谔状态
        但是依然需要向该用户发起一次申请请求(万一对方在线呢，下面的方法也是同理)
        就是调用一个方法，去通知被申请用户，如果同意XXXXXX……,如果拒绝XXXXX……
         */
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
     * 强制添加好友的方法
     * @param userId 申请人ID
     * @param groupId 申请人要将被申请人所存放的分组
     * @param friendId 被申请的ID
     */
    public void forcedAddFriend(String userId,String groupId,String friendId){
        //为申请方添加好友关系 applicant(申请人)
        UserRelational applicant = new UserRelational(Uuid.getUUID(),userId,friendId,KONG,groupId,UserRelationalStatus.GoodFriend.getValue());
        userRelationalRepository.save(applicant);
        /*
           为被申请方添加好友关系 respondent(被调查者)
           这里被申请方的好友列表中并不会出现申请人的账号，因为从逻辑上，没有groupId是无法从好友列表拉取时获取的
           只有被申请人选择了，要添加的分组，才会在列表中显示该好友，但实际上，申请人和被申请人是可以聊天的
           只是在分组中没有申请人的账号
         */
        UserRelational respondent = new UserRelational(Uuid.getUUID(),friendId,userId,KONG,null, UserRelationalStatus.Stranger.getValue());
        userRelationalRepository.save(respondent);
    }
}
