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
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
//@NamedQuery(name="User.findAll", query="SELECT t FROM USER t")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class User implements Serializable {

    @Id
    private String id;

    @Column
    private String username;

    @Column
    @JsonSetter
    private boolean status;

    @Column
    private String sign;

    @Column
    private String avatar;

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

    public String getStatus() {
        return status?"online":"hide";
    }

    public void setStatus(boolean status) {
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

    public void setavatar(String avatar) {
        this.avatar = avatar;
    }
}
