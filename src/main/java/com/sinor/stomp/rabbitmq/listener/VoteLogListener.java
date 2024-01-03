package com.sinor.stomp.rabbitmq.listener;

import com.sinor.stomp.rabbitmq.model.SimpleMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class VoteLogListener {
    private final RabbitTemplate template;

    @Autowired
    public VoteLogListener(RabbitTemplate template) {
        this.template = template;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = ""),
            exchange = @Exchange(value = "fanout", type = "fanout")
    ))
    public void onVote(
            @Payload SimpleMessage message
    ) {
        log.info("onVote {}", message.toString());

    }
}
