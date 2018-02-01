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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.messaging.simp.SimpMessageType.MESSAGE;
import static org.springframework.messaging.simp.SimpMessageType.SUBSCRIBE;

/**
 * 〈WebSocket〉
 *
 * @author Neo Geng
 * Date 2018/1/17
 * @since 1.0.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfigurer extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    public static final Logger LOGGER = LoggerFactory.getLogger(WebSocketMessageBrokerConfigurer.class);

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
            LOGGER.debug("监听到STOMP发送CONNECT frame");
        };
    }


    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                //任何没有目的地的消息（即消息类型为MESSAGE或SUBSCRIBE以外的任何消息）将要求用户被认证
                .nullDestMatcher().authenticated()
                //任何人都可以订阅 / user / queue / errors
                .simpSubscribeDestMatchers("/user/queue/errors").permitAll()
                //任何具有以“/ app /”开头的消息都将要求用户具有角色ROLE_USER
                .simpDestMatchers("/app/**").hasAuthority("USER")
                //任何类型为SUBSCRIBE的“/ user /”或“/ topic / friends /”开头的订阅都需要ROLE_USER
                .simpSubscribeDestMatchers("/user/**", "/topic/friends/*").hasAuthority("USER")
                //任何类型为MESSAGE或SUBSCRIBE的消息都被拒绝
                .simpTypeMatchers(MESSAGE, SUBSCRIBE).denyAll()
                //任何其他消息被拒绝。这是一个好主意，以确保您不会错过任何消息。
                .anyMessage().denyAll();
    }

    /*public void configureClientInboundChannel(ChannelRegistration registration) {
        *//*注册自定义身份验证拦截器
         *请注意，拦截器只需要在CONNECT上认证并设置用户头Message。
         *Spring会记录并保存经过身份验证的用户，并将其与随后的STOMP消息关联到同一会话：*//*
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
    }*/
}
