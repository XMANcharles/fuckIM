package com.honeybadger.im.fuck.tool;

import java.util.UUID;

/**
 * 随便用来生成uuid的工具类
 * @author zcoloder
 * @date 2018/02/01
 */
public class Uuid {
    public static String getUUID() {
       String uuid = UUID.randomUUID().toString();
       return uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);
    }
}
