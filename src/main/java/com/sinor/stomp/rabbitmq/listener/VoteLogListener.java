package com.sinor.stomp.rabbitmq.listener;

import com.sinor.stomp.vote.model.dto.response.VoteLogResponseDto;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
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
            exchange = @Exchange(value = "vote", type = "topic"),
            key = "*"
    ))
    public void onVote(
            @Header(value = "method") String method,
            @Header(value = "amqp_receivedRoutingKey") String routingKey,
            @Headers Map<String, Object> headers,
            VoteLogResponseDto message
    ) {
        log.info("onVote {}", method);
        log.info("onVote {}", routingKey);
//        log.info("onVote {}", headers.toString());
        log.info("onVote {}", message.toString());

        if (String.valueOf(method).equals("put")) {
            template.convertAndSend("vote", routingKey, message, m -> {
                m.getMessageProperties().setHeader("method", "delete");
                return m;
            });
            template.convertAndSend("vote", routingKey, message, m -> {
                m.getMessageProperties().setHeader("method", "post");
                return m;
            });
        } else if (String.valueOf(method).equals("delete")) {
            template.convertAndSend("vote", routingKey, message, m -> {
                m.getMessageProperties().setHeader("method", "delete");
                return m;
            });
        }

    }
}
