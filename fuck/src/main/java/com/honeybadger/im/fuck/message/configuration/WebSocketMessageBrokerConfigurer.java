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
package com.honeybadger.im.fuck.message.configuration;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈WebSocket〉
 *
 * @author Neo Geng
 * Date 2018/1/17
 * @since 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 模拟保存登录用户的集合
     */
    public static Map<String, String> users = new ConcurrentHashMap<>(32);

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //简单的消息代理，基于内存，（包括@MessssageMapiing注解方法的返回值）
        //registry.enableSimpleBroker("/queue","/user");
        //STOMP代理中继
//        registry.enableStompBrokerRelay("/topic", "queue")
//                .setRelayHost("192.168.75.128")
//                //.setRelayPort(5672)
//                .setRelayPort(61613)
//                .setSystemLogin("admin")
//                .setSystemPasscode("admin")
//                //!!!!居然写错API？？？
//                .setClientLogin("admin")
//                .setClientPasscode("admin");
        //服务端处理的前缀，交由@MessageMapping处理
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP端点，客户端通过此端点连接，开启SockJS支持
        registry.addEndpoint("/socket").withSockJS();
    }

    @Bean
    public ApplicationListener<SessionConnectedEvent> webSocketSessionConnectedEvent() {
        return event -> {
            //获取消息头
            /*StompHeaderAccessor headers = StompHeaderAccessor.wrap(event.getMessage());
            //获取账号名
            GenericMessage simpConnectMessage = (GenericMessage) headers.getMessageHeaders().get("simpConnectMessage");
            此方法有BUG，发现好像是StompHeaderAccessor底层数据结构变了 String login = headers.getLogin();
            String login = (String) ((List) simpConnectMessage.getHeaders().get("nativeHeaders", Map.class).get("login")).get(0);
            users.put(login, headers.getSessionId())
            headers.setUser(user);*/
        };
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        /*注册自定义身份验证拦截器
         *请注意，拦截器只需要在CONNECT上认证并设置用户头Message。
         *Spring会记录并保存经过身份验证的用户，并将其与随后的STOMP消息关联到同一会话：*/
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    System.out.println("ChannelInterceptorAdapter");
                    Principal user = accessor::getLogin;
                    accessor.setUser(user);
                }
                return message;
            }
        });
    }
}
