package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.vo.UserRelational;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
    List<UserRelational> findAllByGroupId(String groupId);
}
