/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: InitDataService
 * Author:   Neo Geng
 * Date:     2018/1/21 12:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.init.service;

import com.honeybadger.im.fuck.init.bean.InitData;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2018/1/21
 * @since 1.0.0
 */
public interface InitDataService {

    /**
     * 获取LayIM初始化数据
     * @param id 用户id
     * @return 参考LayIM-InitData
     */
    InitData getInitData(String id);

}
