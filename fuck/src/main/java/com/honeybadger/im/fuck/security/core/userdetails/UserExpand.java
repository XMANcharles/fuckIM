/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: UserExpand
 * Author:   Neo Geng
 * Date:     2018/2/1 11:33
 * Description: 扩展User，加入一些业务字段
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.security.core.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 〈扩展User，加入一些业务字段〉
 *
 * @author Neo Geng
 * Date 2018/2/1
 * @since 1.0.0
 */
public class UserExpand extends User {

    private final String id;
    //private final String

    public UserExpand(String username, String password, Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, authorities);
        this.id = id;
    }

    public UserExpand(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
