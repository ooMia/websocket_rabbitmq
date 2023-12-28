package com.sinor.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * @apiNote <a
 * href="https://stackoverflow.com/questions/64942023/cannot-connect-to-rabbitmq-stomp-from-spring-boot">reference</a>
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableStompBrokerRelay("/topic", "/queue")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setSystemLogin("server")
                .setSystemPasscode("verysecret")
                .setClientLogin("client")
                .setClientPasscode("secret")
                .setAutoStartup(true)
                .setSystemHeartbeatSendInterval(10 * 1000)
                .setSystemHeartbeatReceiveInterval(10 * 1000);
        config.setApplicationDestinationPrefixes("/app");
    }
}