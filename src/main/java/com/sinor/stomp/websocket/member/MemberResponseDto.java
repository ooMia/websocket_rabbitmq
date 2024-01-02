package com.sinor.stomp.websocket.member;

import lombok.Builder;

@Builder
public record MemberResponseDto(
        Long id,
        String name,
        String profile
) {
}
