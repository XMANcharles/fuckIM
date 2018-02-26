package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zcolder
 * @date 2018/02/24
 */
public interface RoleRepository extends JpaRepository<Role,String> {
}
