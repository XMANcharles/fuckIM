package com.honeybadger.im.fuck.user.service;

/**
 * UserRelational接口
 * @author zcolder
 * @date 2018/02/22
 */
public interface UserRelationalService {

    /**
     * 用户申请添加好友
     * 这只是申请，并不会确立好友关系，
     * 关系表中的uuid都是孤儿数据不需要做任何处理
     * @param userId 申请方
     * @param groupId 申请方好友分组的Id
     * @param friendId 被申请方
     */
    void userRequestsToAddFriends(String userId,String groupId,String friendId);

    /**
     * 用户关系的修改
     * @param userId 修改方用户Id
     * @param groupId 分组Id
     * @param friendId 被修方用户Id
     * @param status 需要被修改的关系状态根据Controller的请求来判别
     */
    void alterUserRelational(String userId, String groupId, String friendId, String status);

    /**
     * 将指定用户的指定分组好友转移向另一个分组
     * 之所以在(UserRelationalServiceImpl)是因为它是对用户关系之间的一个操作，而不是对分组的操作
     * @param userId 用户Id
     * @param fromGroupId 要被转出的分组
     * @param toGroupId 要被转入的分组
     * @return {@code true}转移成功,否则{@code false}转移失败.
     */
    void transferGroup(String userId, String fromGroupId, String toGroupId);

    /**
     * 拉黑(屏蔽)用户
     * @param userId 用户ID
     * @param shieldingId 需要拉黑(屏蔽)的ID
     */
    void shieldingUsers(String userId, String shieldingId);
}
