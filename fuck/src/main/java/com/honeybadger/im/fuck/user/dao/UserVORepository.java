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

import com.honeybadger.im.fuck.security.userdetails.MyUserDetailsServiceImpl;
import com.honeybadger.im.fuck.user.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 〈〉
 *
 * @author Neo Geng
 * @date 2018/1/20
 * @since 1.0.0
 */
public interface UserVORepository extends JpaRepository<UserVO,String> {

    /**
     * 根据Username查找用户，这个方法主要为security框架内容验证流程提供
     * @see MyUserDetailsServiceImpl
     * @param username 用户名
     * @return 包含用户的权限等信息
     */
    Optional<UserVO> findByUsername(String username);
}
