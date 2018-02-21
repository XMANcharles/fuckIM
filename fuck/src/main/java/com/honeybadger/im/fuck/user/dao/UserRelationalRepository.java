package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author zcolder
 * @date 2018/02/01
 */
public interface UserRelationalRepository extends JpaRepository<UserRelational,String> {
    /**
     * 获取用户的好友列表
     * @param userId 用户id
     * @return 用户关系列表
     */
    Optional<List<UserRelational>> findAllByUserId(String userId);

    /**
     * 根据分组返回所在分组的好友
     * @param groupId 组ID
     * @return 好友列表
     */
    Optional<List<UserRelational>> findAllByGroupId(String groupId);

    /**
     * 为指定用户的其中一个分组的好友转移到另一个分组
     * @param userId 用户Id
     * @param fromGroupId 被转移的分组
     * @param toGroupId 被转进的分组
     */
    @Query("UPDATE UserRelational AS ur SET ur.groupId=:toGroupId WHERE ur.userId=:userId AND ur.groupId=:fromGroupId")
    void groupFriendTransfer(@Param("userId")String userId, @Param("fromGroupId") String fromGroupId, @Param("toGroupId") String toGroupId);

    /**
     * 修改用户之间的关系
     *   目前暂时大概是修改sr(陌生人)的关系，用于审批其他用户对其用户的申请
     *   有可能需要用户分组，但有时不需要
     * @param userId 用户id
     * @param groupId 分组id(不一定参数)
     * @param friendId 申请方，被处理方的id
     * @param status 修改用户关系为好友关系
     */
    @Query("UPDATE UserRelational AS ur SET ur.groupId=:groupId, ur.status=:status WHERE ur.userId=:userId AND ur.friendId=:friendId")
    void modifyUserRelationships(@Param("userId")String userId,@Param("groupId")String groupId,@Param("friendId")String friendId, @Param("status")String status);

    /**
     * 单向查询用户之间是否存在的关系
     * @param userId 用户ID
     * @param friendId 另一个用户ID(被查询方)
     * @return 用户关系
     */
    UserRelational findByUserIdAndAndFriendId(String userId, String friendId);

    /**
     * 用户拉黑(屏蔽另一个用户)
     * 这是一个单方面操作
     * @param userId 用户ID
     * @param shieldingId 被拉黑(屏蔽)的用户ID
     */
    void shieldingUsers(String userId, String shieldingId);
}
