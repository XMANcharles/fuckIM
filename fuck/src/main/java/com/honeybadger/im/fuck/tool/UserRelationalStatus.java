package com.honeybadger.im.fuck.tool;

/**
 * @author zcolder
 * @date 2018/02//07
 */
public enum UserRelationalStatus {
    /**
     * 好友
     */
    GoodFriend("gf"),
    /**
     * 陌生人
     */
    Stranger("sr"),
    /**
     * 黑名单
     */
    PullBlack("bk"),
    /**
     * 特别关心
     */
    TrueLove("tl");

    String value;

    UserRelationalStatus(String value) {
        this.value= value;
    }

    public String getValue() {
        return value;
    }

}
