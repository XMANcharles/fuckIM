/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: GroupFriends
 * Author:   Neo Geng
 * Date:     2018/2/5 15:55
 * Description: 分组好友
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.honeybadger.im.fuck.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 〈分组好友对象〉
 * 针对每个分组，保存分组下所有的好友信息
 *
 * @author Neo Geng
 * Date 2018/2/5
 * @since 1.0.0
 */
@Entity
@Table(name = "user_group")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class GroupFriends implements Serializable {

    @Id
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "GROUP_NAME")
    private String groupname;

    /**
     * {@link @ManyToMany}注释表示Group是多对多关系的一端。
     * <p>{@link @JoinTable}描述了多对多关系的数据表关系。name属性指定中间表名称
     * <li>joinColumns定义中间表与Group表的外键关系。</li>
     * 中间表USER_RELATIONAL的GROUP_ID||USER_ID列是Group表的关联列
     * <li>inverseJoinColumns属性定义了中间表与另外一端(ROLE)的外键关系</li>
     */
    @JsonProperty("list")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_RELATIONAL",
            joinColumns = {
                    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID", insertable = false, updatable = false),
                    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
            },
            inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "ID", insertable = false, updatable = false)})
    private List<User> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public GroupFriends() {
    }

    public GroupFriends(String id,String userId, String groupname) {
        this.id = id;
        this.userId = userId;
        this.groupname = groupname;
    }
}
