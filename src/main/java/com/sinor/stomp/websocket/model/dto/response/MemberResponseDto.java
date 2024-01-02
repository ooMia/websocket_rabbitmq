package com.sinor.stomp.websocket.model.dto.response;

import lombok.Builder;

@Builder
public record MemberResponseDto(
        Long id,
        String name,
        String profile
) {
}
