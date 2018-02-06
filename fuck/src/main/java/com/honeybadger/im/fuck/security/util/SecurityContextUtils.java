/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: SecurityContextUtils
 * Author:   Neo Geng
 * Date:     2018/1/29 18:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * 〈SecurityContextUtils〉
 *
 * @author Neo Geng
 * Date 2018/1/29
 * @since 1.0.0
 */
public class SecurityContextUtils {

    private SecurityContextUtils(){

    }

    /**
     * 获取HttpSession中保存的SecurityContext，程序内部使用
     * @param request
     * @return 当前登录用户的 {@code SecurityContext}
     */
    public static SecurityContext getSecurityContext(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        //如果不存在SecurityContext，则代表程序异常
        return (SecurityContext) Optional.ofNullable(httpSession).map(t -> t.getAttribute(SPRING_SECURITY_CONTEXT_KEY)).get();
    }

    /**
     * 获取Authentication
     * @param request
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
            Object securityContext = httpSession.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
            if (securityContext instanceof SecurityContext){
                return ((SecurityContext)securityContext).getAuthentication();
            }
        }
        return null;
    }

    public static Principal getPrincipal(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
            Object securityContext = httpSession.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
            if (securityContext instanceof SecurityContext){
                return (Principal) ((SecurityContext)securityContext).getAuthentication().getPrincipal();
            }
        }
        return null;
    }

}
