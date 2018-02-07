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
package com.honeybadger.im.fuck.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.honeybadger.im.fuck.user.entity.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 〈User〉
 *
 * @author Neo Geng
 * Date 2018/1/20
 * @since 1.0.0
 */
@Entity
@Table(name = "USER")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class UserVO implements Serializable {

    @Id
    private String id;

    @Column
    private String username;
    /**
     * @zcolder
     * 提供给视图层的user对象 一个不需要密码这个属性的
     */
    @Column
    private String password;

    @Column
    private Boolean status;

    @Column
    private String sign;

    @Column
    private String avatar;

    /**
     * {@link @ManyToMany}注释表示User是多对多关系的一端。
     * {@link @JoinTable}描述了多对多关系的数据表关系。name属性指定中间表名称，joinColumns定义中间表与USER表的外键关系。
     * 中间表USER_ROLE的USER_ID列是USER表的主键列对应的外键列，inverseJoinColumns属性定义了中间表与另外一端(ROLE)的外键关系。
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    private List<Role> roles;

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

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", sign='" + sign + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roles=" + roles +
                '}';
    }
}
