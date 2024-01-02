package com.sinor.stomp.rabbitmq.model;

import lombok.Data;

@Data
public class ChatMessageDTO2 {
    private String roomId;
    private String writer;
    private String message;
}