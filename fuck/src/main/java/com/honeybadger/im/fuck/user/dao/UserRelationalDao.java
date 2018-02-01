package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcolder
 * @date 2018/02/01
 */
public interface UserRelationalDao extends JpaRepository<User,String> {
}
