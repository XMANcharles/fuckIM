/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: InitData
 * Author:   Neo Geng
 * Date:     2018/1/21 12:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.base.bean;

import com.honeybadger.im.fuck.user.entity.User;

/**
 * 〈LayIM初始化数据结构〉
 *
 * @author Neo Geng
 * Date 2018/1/21
 * @since 1.0.0
 */
public class InitData {

    private final User mine;

    public InitData(User mine) {
        this.mine = mine;
    }

    public User getMine() {
        return mine;
    }
}
