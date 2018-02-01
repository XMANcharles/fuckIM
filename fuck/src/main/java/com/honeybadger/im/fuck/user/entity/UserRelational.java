package com.honeybadger.im.fuck.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 用户关系(即好友,一对多)
 * @author zcolder
 * @date 2018/02/01
 */
@Entity(name = "UserRelational")
@Table(name = "UserRelational")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class UserRelational {

    /**
     * jpa的必须有一个id(此表中的uuid是孤儿数据)
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 用户id
     */
    @Column
    private String userId;

    /**
     * 好友id
     */
    @Column
    private String friendId;

    /**
     * 好友在线情况
     */
    @Column
    @JsonSetter
    private boolean friendOnlineStatus;

    /**
     * 好友备注
     */
    @Column
    private String friendNote;

    /**
     * 好友所在分组(此处应该有一个默认值)
     */
    @Column
    private String friendGroupName;

    /**
     * 封装好友信息(将联表所查询到的user id对应信息封印在此)
     */
    private User friendInfo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public boolean isFriendOnlineStatus() {
        return friendOnlineStatus;
    }

    public void setFriendOnlineStatus(boolean friendOnlineStatus) {
        this.friendOnlineStatus = friendOnlineStatus;
    }

    public String getFriendNote() {
        return friendNote;
    }

    public void setFriendNote(String friendNote) {
        this.friendNote = friendNote;
    }

    public String getFriendGroupName() {
        return friendGroupName;
    }

    public void setFriendGroupName(String friendGroupName) {
        this.friendGroupName = friendGroupName;
    }

    public User getFriendInfo() {
        return friendInfo;
    }

    public void setFriendInfo(User friendInfo) {
        this.friendInfo = friendInfo;
    }
}
