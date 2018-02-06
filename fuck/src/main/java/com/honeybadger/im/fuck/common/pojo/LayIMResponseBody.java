/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: ResponseEntity
 * Author:   Neo Geng
 * Date:     2018/1/20 22:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.common.pojo;

/**
 * 〈LayIM通用ResponseEntity〉
 *
 * @author Neo Geng
 * Date 2018/1/20
 * @since 1.0.0
 */
public class LayIMResponseBody<T> {

    /**
     *  0表示成功，其它表示失败
     */
    private final int code;
    /**
     * 失败信息
     */
    private final String msg;
    private final T data;

    public LayIMResponseBody(int code, String msg, T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> LayIMResponseBody<T> buildSuccess(T data) {
        return new LayIMResponseBody<>(0, null, data);
    }

    public static <T> LayIMResponseBody<T> buildFail(String msg) {
        return new LayIMResponseBody<>(1,msg, null);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
