package com.sinor.stomp.rabbitmq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RabbitMQController {
    private final RabbitMQProducer producer;

    public RabbitMQController(RabbitMQProducer rabbitMQProducer) {
        this.producer = rabbitMQProducer;
    }

    // http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendDirectMessage(
            @RequestParam("message") String message
    ) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
