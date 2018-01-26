/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: SocketController
 * Author:   Neo Geng
 * Date:     2018/1/17 11:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.message.controller;

import com.honeybadger.im.fuck.message.bean.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * 〈〉
 *
 * @author Neo Geng
 * Date 2018/1/17
 * @since 1.0.0
 */
@Controller
public class SocketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MessageMapping("/message/send")
    public void fuckYou(Message message) {
        LOGGER.debug("receive web message:"+message.getMine().getContent());
        //发送到RabbitMQ-Queue-fuck
        System.out.println(message.getMine().getContent());
        rabbitTemplate.convertAndSend("message",message);
    }

}
