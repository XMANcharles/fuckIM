package com.honeybadger.im.fuck.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.honeybadger.im.fuck.user.entity.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户关系(即好友,一对多)
 * @author zcolder
 * @date 2018/02/01
 */
@Entity
@Table(name = "user_relational")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class UserRelational implements Serializable {

    /**
     * jpa的必须有一个id(此表中的uuid是孤儿数据)
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 好友id
     */
    @Column(name = "friend_id")
    private String friendId;

    /**
     * 好友备注
     */
    @Column(name = "friend_note")
    private String friendNote;

    /**
     * 好友所在分组的id(此处应该有一个默认值)
     */
    @Column(name = "group_id")
    private int groupId;

    /**
     * 封装好友信息(将联表所查询到的user id对应信息封印在此)
     */
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private User friendInfo;

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

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendNote() {
        return friendNote;
    }

    public void setFriendNote(String friendNote) {
        this.friendNote = friendNote;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public User getFriendInfo() {
        return friendInfo;
    }

    public void setFriendInfo(User friendInfo) {
        this.friendInfo = friendInfo;
    }
}
