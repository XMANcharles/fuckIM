package com.honeybadger.im.fuck.user.controller;

import com.honeybadger.im.fuck.user.service.UserRelationalService;
import com.honeybadger.im.fuck.user.vo.GroupFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zcolder
 * @date 2018/02/01
 */
@RestController
@RequestMapping(value = "/UserRelational")
public class UserRelationalController {

    @Autowired
    private UserRelationalService userRelationalService;
    /**
     * 添加用户关系(即添加好友)
     * 此处须在队列中等待被添加方同意请求
     * 未来可以考虑增加个人隐私控制(即不可以直接申请好友) || 拒绝理由
     * @param userId 主动出击者
     * @param friendId 无奈接收者
     * @return true and false 成功与否
     */
    @RequestMapping(value = "/addFriend")
    public boolean addUserRelational(@PathVariable String userId,
                                     @PathVariable String friendId){

        return true;
    }

    /**
     * 删除用户(之间的)关系(即删除好友)
     * 删除好友不需要对方同意
     * 此处任何无返回
     * @param userId 动手的那个人
     * @param friendId 被干掉的那个人 tony马表面兄弟
     */
    @RequestMapping(value = "/deleteFriend")
    public void deleteUserRelational(@PathVariable String userId,
                                     @PathVariable String friendId){
    }

    /**
     * 尚未考虑拉黑，特别关注好友的等设置，此方法留空
     * 这是一个单方面更新用户关系(好友关系)
     * 此处任何无返回
     * @param userId 你爱人
     * @param friendId 被绿的兄弟
     */
    @RequestMapping(value = "/updateFriendStatus")
    public void updateUserRelational(@PathVariable String userId,
                                     @PathVariable String friendId){

    }

    /**
     * 获取好友列表
     * @param userId 用户ID
     * @return 好友列表
     */
    @RequestMapping(value = "/{userId}/friend")
    public List<GroupFriends> getFriendListByUserId(@PathVariable String userId){
        return userRelationalService.getFriendListByUserId(userId);
    }
}
