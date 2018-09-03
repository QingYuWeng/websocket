package com.moyixianqi.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//开启使用STOMP协议类传输基于代理(message broker)的消息,这时控制器支持使用@MessageMapping
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //使一个简单的基于内存的消息代理能够将问候消息以“/topic”为前缀的目的地带回客户机。
        registry.enableSimpleBroker("/topic");

        //为绑定到@messagemapping注释方法的消息指定“/app”前缀。这个前缀将用于定义所有的消息映射;
        //例如，“/app/hello”是将GreetingController.greeting()方法映射到处理的端点。
        registry.setApplicationDestinationPrefixes("/app");
    }


    /**
     * registerStompEndpoints()方法注册“/gs-guide-websocket”端点，启用SockJS回退选项，
     * 以便在WebSocket不可用时可以使用备用传输。SockJS客户端将尝试连接到“/gs-guide-websocket”，
     * 并使用可用的最佳传输(websocket、xhr-streaming、xhr-polling等)。
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }
}
