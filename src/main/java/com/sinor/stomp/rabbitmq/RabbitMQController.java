package com.sinor.stomp.rabbitmq;

import com.sinor.stomp.GlobalVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RabbitMQController {

    private final RabbitTemplate template;
    private final String exchangeName;

    @Autowired
    public RabbitMQController(RabbitTemplate template, GlobalVariables globalVariables) {
        this.template = template;
        this.exchangeName = globalVariables.getRabbitExchangeExample();
    }

//    @MessageMapping("{chatRoomId}.{memberId}")
//    public void enter(OnConnectDto chat, @DestinationVariable String chatRoomId) {
//        chat.setMessage("입장하셨습니다.");
//        template.convertAndSend(exchangeName, "room." + chatRoomId, chat); // exchange
//    }
//
//    @MessageMapping("chat.message.{chatRoomId}")
//    public void send(ChatDto chat, @DestinationVariable String chatRoomId) {
//        template.convertAndSend(exchangeName, "room." + chatRoomId, chat);
//    }


}