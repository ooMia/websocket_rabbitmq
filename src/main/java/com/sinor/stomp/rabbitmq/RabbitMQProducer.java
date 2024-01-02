package com.sinor.stomp.rabbitmq;

import com.sinor.stomp.GlobalVariables;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Log4j2
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate, GlobalVariables globalVariables) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = globalVariables.getRabbitExchangeExample();
        this.routingKey = globalVariables.getRabbitRoutingKeyExample();
    }

    @GetMapping("/publish")
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info(String.format("[x] Message sent -> %s", message));
    }
}