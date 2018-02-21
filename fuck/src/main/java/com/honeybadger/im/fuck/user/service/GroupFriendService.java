package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.user.vo.GroupFriends;

import java.util.List;

/**
 * GroupFriend接口
 * @author zcolder
 * @date 2018/02/22
 */
public interface GroupFriendService {

    /**
     * 为指定用户添加好友分组
     * 如果添加的分组是黑名单，则将groupId人为的置为"黑名单"
     * @param userId 用户的ID
     * @param groupName 分组的名字
     * @return {@code true} 添加成功，否则{@code false}
     */
    void addGroup(String userId,String groupName);

    /**
     * 删除分组
     * 如果删除的分组存在好友，这应该是在前端就提前询问的
     * 这里的查询，只为了避免绕过前端JS
     * @param groupId 分组的ID,在表中，groupId是唯一的
     * @return {@code true} 删除成功，否则{@code false}
     */
    boolean deleteGroup(String groupId);

    /**
     * 修改分组的名字
     * @param groupId 分组ID
     * @param groupName 新的分组名字
     * @return {@code true} 更新成功，否则{@code false}
     */
    void updateGroupName(String groupId,String groupName);

    /**
     * 通过用户ID拉取好友列表
     * @param userId 用户ID
     * @return List<GroupFriends>
     */
    List<GroupFriends> getFriendListByUserId(String userId);
}
