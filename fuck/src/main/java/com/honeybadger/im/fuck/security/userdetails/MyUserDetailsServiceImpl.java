/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: MyUserDetailsService
 * Author:   Neo Geng
 * Date:     2018/1/28 16:30
 * Description: MyUserDetailsService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.security.userdetails;

import com.honeybadger.im.fuck.user.dao.UserVORepository;
import com.honeybadger.im.fuck.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 〈MyUserDetailsService〉
 * 这个类继承UserDetailsService，主要作用于{@link DaoAuthenticationProvider}
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 * @author Neo Geng
 * Date 2018/1/28
 * @since 1.0.0
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserVORepository userVORepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //查出User
        UserVO loginUser = userVORepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        //对User授权
        Set<GrantedAuthority> authorities = new HashSet<>();
        loginUser.getRoles().forEach(t->authorities.add(new SimpleGrantedAuthority(t.getRolename())));
        //是否可用、是否过期、证书不过期为true、账户未锁定为true
        return new UserExpand(
                username, loginUser.getPassword(),
                true,
                true,
                true,
                true,
                authorities,loginUser.getId());
    }

}
