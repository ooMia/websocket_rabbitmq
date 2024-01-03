package com.sinor.stomp.rabbitmq.listener;

import com.sinor.stomp.rabbitmq.model.OnConnectDto;
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
public class ConnectionListener {
    private final RabbitTemplate template;

    @Autowired
    public ConnectionListener(RabbitTemplate template) {
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
