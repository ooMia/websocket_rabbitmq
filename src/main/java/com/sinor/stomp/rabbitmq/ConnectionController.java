package com.sinor.stomp.rabbitmq;

import com.sinor.stomp.rabbitmq.model.OnConnectDto;
import com.sinor.stomp.rabbitmq.model.SimpleMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
public class ConnectionController {
    private final RabbitTemplate template;

    public ConnectionController(RabbitTemplate template) {
        this.template = template;
    }

    @RabbitListener(queues = "${rabbitmq.queue.connection}")
    public void onConnect(
            OnConnectDto message
    ) {
        log.info("onConnect {}", message.toString());

        template.convertAndSend(
                "vote",
                message.getVoteId() + "." + message.getMemberId(),
                new SimpleMessage("hi " + message.getMemberId())
        );

        template.convertAndSend(
                "vote",
                message.getVoteId() + "",
                new SimpleMessage("member " + message.getMemberId() + " joined the room")
        );
    }

    @SubscribeMapping("/vote")
    public void receive(
            @Payload SimpleMessage message
    ) {
        log.info("receive SimpleMessage");
    }
}
