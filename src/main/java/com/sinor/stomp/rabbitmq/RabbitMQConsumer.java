package com.sinor.stomp.rabbitmq;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQConsumer {
    public void consume(String message) {
        log.info(String.format("Received message -> %s", message));
    }
}