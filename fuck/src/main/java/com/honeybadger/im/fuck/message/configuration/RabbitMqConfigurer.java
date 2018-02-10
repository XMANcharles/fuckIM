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
package com.honeybadger.im.fuck.message.configuration;

import com.honeybadger.im.fuck.message.bean.Message;
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
    public Queue userQueue(){
        return new Queue("message");
    }

    @Bean
    public Queue groupQueue(){
        return new Queue("group");
    }

    /**
     * 消费者-以后肯定需要抽取
     */
    @RabbitListener(queues = "message")
    @RabbitHandler
    public void processReceive(Message content) {
        System.out.println("读取到"+content);
        //模拟发送给用户
        simpMessagingTemplate.convertAndSendToUser(content.getTo().getName(),"/queue/position-updates",content);
    }

}
