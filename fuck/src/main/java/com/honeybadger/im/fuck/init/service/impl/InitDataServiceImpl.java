/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: InitDataServiceImpl
 * Author:   Neo Geng
 * Date:     2018/1/21 12:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.init.service.impl;

import com.honeybadger.im.fuck.init.bean.InitData;
import com.honeybadger.im.fuck.init.service.InitDataService;
import com.honeybadger.im.fuck.user.dao.GroupFriendsRepository;
import com.honeybadger.im.fuck.user.dao.UserRepository;
import com.honeybadger.im.fuck.user.entity.User;
import com.honeybadger.im.fuck.user.vo.GroupFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2018/1/21
 * @since 1.0.0
 */
@Service
public class InitDataServiceImpl implements InitDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupFriendsRepository groupFriendsRepository;

    @Override
    public InitData getInitData(String userId) {
        User me = userRepository.getOne(userId);
        List<GroupFriends> friends = groupFriendsRepository.findAllByUserId(userId).orElse(null);
        return new InitData(me,friends);
    }

}
