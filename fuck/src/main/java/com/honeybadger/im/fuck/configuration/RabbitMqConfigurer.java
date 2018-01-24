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
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * 〈RabbitMqConfigurer〉
 *
 * @author Neo Geng
 * Date 2018/1/18
 * @since 1.0.0
 */
@Configuration
public class RabbitMqConfigurer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Bean
    public Queue Queue(){
        return new Queue("fuck");
    }

    @Bean
    public Queue useQueue(){
        return new Queue("user");
    }

    //消费者-以后肯定需要抽取
    @RabbitListener(queues = "fuck")
    @RabbitHandler
    public void processReceive(String content) {
        System.out.println("读取到"+content);
        simpMessagingTemplate.convertAndSend("/fuckme",content);
    }

}
