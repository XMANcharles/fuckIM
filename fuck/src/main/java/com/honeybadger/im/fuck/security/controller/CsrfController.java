/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: CsrfController
 * Author:   Neo Geng
 * Date:     2018/2/1 14:04
 * Description: CsrfController
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.security.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈CsrfController 〉
 *  暴露CSRF，供前端获取。（解决前端不适用框架的场景，如ajax）
 * @author Neo Geng
 * Date 2018/2/1
 * @since 1.0.0
 */
@RestController
public class CsrfController {

    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

}
