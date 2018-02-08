package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    List<UserRelational> findAllByUserId(String userId);

    /**
     * 根据分组返回所在分组的好友
     * @param groupId 组ID
     * @return 好友列表
     */
    Optional<List<UserRelational>> findAllByGroupId(String groupId);

    @Query("UPDATE UserRelational AS ur SET ur.groupId=:groupId,ur.status=:status WHERE ur.userId=:userId AND ur.friendId=:friendId")
    void updateUserRelational(String userId,String friendId,String groupId,String status);

    @Query("UPDATE UserRelational AS ur SET ur.status=:status WHERE ur.userId=:userId AND ur.friendId=:friendId")
    void updateUserRelational(String userId,String friendId,String status);
}
