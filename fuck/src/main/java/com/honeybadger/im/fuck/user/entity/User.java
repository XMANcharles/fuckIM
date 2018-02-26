/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: User
 * Author:   Neo Geng
 * Date:     2018/1/20 22:46
 * Description: User
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 〈User〉
 *
 * @author Neo Geng
 * Date 2018/1/20
 * @since 1.0.0
 */
@Entity
@Table(name = "USER")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    @Id
    private String id;
    @Column
    private String username;
    @Column
    private String password;
    @Enumerated
    private Status status;
    @Column
    private String sign;
    @Column
    private String avatar;

    public User() {

    }

    public User(String id, String username, String password, Status status, String sign, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.sign = sign;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 在线状态 online：在线、hide：隐身
     */
    public enum Status {
        /**
         * 在线
         */
        ONLINE,
        /**
         * 不在线-隐身
         */
        HIDE;
    }
}
