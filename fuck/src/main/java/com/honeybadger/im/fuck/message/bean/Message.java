/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: Mine
 * Author:   Neo Geng
 * Date:     2018/1/26 15:23
 * Description: LayIm需要的message格式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.message.bean;

import java.io.Serializable;

/**
 * 〈LayIm需要的message格式〉
 *
 * @author Neo Geng
 * Date 2018/1/26
 * @since 1.0.0
 */
public class Message implements Serializable {

    private Mine mine;
    private To to;

    public Mine getMine() {
        return mine;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }





}

