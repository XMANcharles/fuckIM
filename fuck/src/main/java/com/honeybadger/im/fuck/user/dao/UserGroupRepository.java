package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.vo.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcolder
 * @date 2018/02/04
 */
public interface UserGroupRepository extends JpaRepository<UserGroup,String> {
}
