package com.sinor.stomp.config.binding;

import com.sinor.stomp.GlobalVariables;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoteBindingConfig {
    private final GlobalVariables globalVariables;

    public VoteBindingConfig(GlobalVariables globalVariables) {
        this.globalVariables = globalVariables;
    }

    @Bean
    public Queue exampleQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public TopicExchange exampleExchange() {
        return new TopicExchange(globalVariables.getRabbitExchangeExample());
    }


    @Bean
    public Binding exampleBinding() {
        return BindingBuilder
                .bind(exampleQueue())
                .to(exampleExchange())
                .with("#");
    }
}
