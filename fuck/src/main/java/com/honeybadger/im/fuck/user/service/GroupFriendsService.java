package com.honeybadger.im.fuck.user.service;

import com.honeybadger.im.fuck.tool.Uuid;
import com.honeybadger.im.fuck.user.dao.GroupFriendsRepository;
import com.honeybadger.im.fuck.user.dao.UserRelationalRepository;
import com.honeybadger.im.fuck.user.dao.UserVORepository;
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
public class GroupFriendsService {

    @Autowired
    private UserVORepository userVORepository;

    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Autowired
    private UserRelationalRepository userRelationalRepository;

    /**
     * 为指定用户添加好友分组
     * 如果添加的分组是黑名单，则将groupId人为的置为"黑名单"
     * @param userId 用户的ID
     * @param groupName 分组的名字
     * @return {@code true} 添加成功，否则{@code false}
     */
    public boolean addGroup(String userId,String groupName){
        //用户是否存在
        if(!userVORepository.findById(userId).isPresent()){
            return false;
        }
        //添加分组
        groupFriendsRepository.save(new GroupFriends(Uuid.getUUID(),userId,groupName));
        return true;
    }

    /**
     * 删除分组
     * 如果删除的分组存在好友，这应该是在前端就提前询问的
     * 这里的查询，只为了避免绕过前端JS
     * @param groupId 分组的ID,在表中，groupId是唯一的
     * @return {@code true} 删除成功，否则{@code false}
     */
    public boolean deleteGroup(String groupId){
        //如果删除的分组存在好友
        if(userRelationalRepository.findAllByGroupId(groupId).orElse(null).size()>=1){
            return false;
        }
        //否则删除分组
        groupFriendsRepository.deleteById(groupId);
        return true;
    }

    /**
     * 修改分组的名字
     * @param groupId 组ID
     * @param groupName 新的分组名字
     * @return {@code true} 更新成功，否则{@code false}
     */
    public void updateGroupName(String groupId,String groupName){
        groupFriendsRepository.save(new GroupFriends(null,groupId,groupName));
    }

    /**
     * 通过用户ID拉取好友列表
     * @param userId 用户ID
     * @return List<GroupFriends>
     */
    public List<GroupFriends> getFriendListByUserId(String userId){
        //用户ID获取好友分组
        return groupFriendsRepository.findAllByUserId(userId).orElse(null);
    }

}
