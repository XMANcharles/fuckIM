package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 张朝锋
 * @date 2018-03-12
 * @since user和role中间表
 */
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
}
