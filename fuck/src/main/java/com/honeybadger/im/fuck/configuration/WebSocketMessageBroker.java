/*
 * Copyright (C), 1995-2018, 没钱有限公司
 * FileName: WebSocketMessageBroker
 * Author:   Neo Geng
 * Date:     2018/1/17 11:21
 * Description: WebSocket
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.honeybadger.im.fuck.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 〈WebSocket〉
 *
 * @author Neo Geng
 * Date 2018/1/17
 * @since 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBroker extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //简单的消息代理，基于内存，（包括@MessssageMapiing注解方法的返回值）
        //registry.enableSimpleBroker("/topic");
        //STOMP代理中继
        registry.enableStompBrokerRelay("/topic", "queue")
                .setRelayHost("192.168.75.128")
                //.setRelayPort(5672)
                .setRelayPort(61613)
                .setSystemLogin("admin")
                .setSystemPasscode("admin")
                //!!!!居然写错API？？？
                .setClientLogin("admin")
                .setClientPasscode("admin");
        //服务端处理的前缀，交由@MessageMapping处理
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP端点，来接收客户端的连接。开启SockJS支持
        registry.addEndpoint("/socket").withSockJS();
    }
}
