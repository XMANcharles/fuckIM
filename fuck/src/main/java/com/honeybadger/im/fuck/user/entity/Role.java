/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: Role
 * Author:   Neo Geng
 * Date:     2018/1/28 21:05
 * Description: Role
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 〈Role〉
 *
 * @author Neo Geng
 * Date 2018/1/28
 * @since 1.0.0
 */
@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    @Id
    private String id;

    @Column
    private String rolename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
