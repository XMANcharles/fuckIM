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
package com.honeybadger.im.fuck.base.service.impl;

import com.honeybadger.im.fuck.base.bean.InitData;
import com.honeybadger.im.fuck.base.service.InitDataService;
import com.honeybadger.im.fuck.user.dao.UserDao;
import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserDao userDao;

    @Override
    public InitData getInitData(String id) {
        User me = userDao.getOne(id);

        return new InitData(me);
    }

}
