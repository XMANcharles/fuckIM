/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: RabbitMqConfigurer
 * Author:   Neo Geng
 * Date:     2018/1/18 18:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈RabbitMqConfigurer〉
 *
 * @author Neo Geng
 * Date 2018/1/18
 * @since 1.0.0
 */
@Configuration
public class RabbitMqConfigurer {

    @Bean
    public Queue queue(){
        return new Queue("/fuck");
    }

}
