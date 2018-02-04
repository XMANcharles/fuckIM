package com.honeybadger.im.fuck.user.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户分组（之所以拆表，逻辑问题）
 * @author zcodler
 * @date 2018/02/04
 */
@Entity
@Table(name = "user_group")
public class UserGroup implements Serializable {

    @Id
    @Column
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "group_name")
    private String groupName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
