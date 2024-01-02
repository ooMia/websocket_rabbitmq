package com.sinor.stomp.rabbitmq.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChatDto2 {
    private Long id;
    private Long chatRoomId;
    private Long memberId;

    private String message;
    private String region;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime regDate;

    @Builder
    public ChatDto2(Long id, String message) {
        this.id = id;
        this.message = message;
    }
}
