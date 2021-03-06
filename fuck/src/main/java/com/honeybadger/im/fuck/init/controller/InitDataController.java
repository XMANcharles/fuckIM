/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: InitDateController
 * Author:   Neo Geng
 * Date:     2018/1/21 12:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.init.controller;

import com.honeybadger.im.fuck.common.pojo.LayIMResponseBody;
import com.honeybadger.im.fuck.init.service.InitDataService;
import com.honeybadger.im.fuck.security.userdetails.UserExpand;
import com.honeybadger.im.fuck.security.util.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2018/1/21
 * @since 1.0.0
 */
@RestController
public class InitDataController {

    @Autowired
    private InitDataService initDataService;

    @RequestMapping(value = "/initdata",method = RequestMethod.GET)
    public LayIMResponseBody getInitData(HttpServletRequest request){
        SecurityContext securityContext = SecurityContextUtils.getSecurityContext(request);
        UserExpand user = (UserExpand) securityContext.getAuthentication().getPrincipal();
        return LayIMResponseBody.buildSuccess(initDataService.getInitData(user.getId()));
    }

}
