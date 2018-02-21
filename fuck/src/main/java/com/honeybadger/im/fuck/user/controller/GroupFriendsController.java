package com.honeybadger.im.fuck.user.controller;

import com.honeybadger.im.fuck.user.service.impl.GroupFriendsServiceImpl;
import com.honeybadger.im.fuck.user.vo.GroupFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 好友分组的action
 * @author 张朝锋
 * @date 2018/02/17
 */
@RestController()
@RequestMapping(value = "/GroupFriends")
public class GroupFriendsController {

    @Autowired
    private GroupFriendsServiceImpl groupFriendsService;

    /**
     * 获取好友列表
     * @param userId 用户ID
     * @return 好友列表
     */
    @RequestMapping(value = "/friendList/{userId}")
    public List<GroupFriends> getFriendListByUserId(@PathVariable String userId){
        return groupFriendsService.getFriendListByUserId(userId);
    }

}
