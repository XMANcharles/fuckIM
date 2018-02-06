/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: UserDao
 * Author:   Neo Geng
 * Date:     2018/1/20 22:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.security.core.userdetails.MyUserDetailsServiceImpl;
import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2018/1/20
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User,String> {

    /**
     * 根据Username查找用户，这个方法主要为security框架内容验证流程提供
     * @see MyUserDetailsServiceImpl
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}
