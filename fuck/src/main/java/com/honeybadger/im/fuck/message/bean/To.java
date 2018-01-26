/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: To
 * Author:   Neo Geng
 * Date:     2018/1/26 16:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.message.bean;

import java.io.Serializable;

/**
 * 包含对方信息
 */
public class To implements Serializable {
    private String avatar;
    private String id;
    private String name;
    private String sign;
    /**
     * 聊天类型，一般分friend和group两种，group即群聊
     */
    private String type;
    private String username;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
