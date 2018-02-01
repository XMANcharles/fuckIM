package com.honeybadger.im.fuck.user.dao;

import com.honeybadger.im.fuck.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRaletionalDao extends JpaRepository<User,String> {
}
