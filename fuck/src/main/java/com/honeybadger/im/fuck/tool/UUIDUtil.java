package com.honeybadger.im.fuck.tool;

import java.util.UUID;

/**
 * 随便用来生成uuid的工具类
 * @author zcoloder
 * @date 2018/02/01
 */
public class UUIDUtil {
    /**
     * 未来考虑，接收一个表名，并将生成的uuid在该表中检测所有id是否已经存在，否 则返回uuid。
     * @return UUID
     */
    public static String getUUID() {
       String uuid = UUID.randomUUID().toString();
       return uuid.replace("-","");
    }

}
