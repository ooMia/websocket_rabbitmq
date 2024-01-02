package com.sinor.stomp.websocket.model.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record BoardResponseDto(
        Long id,
        List<VoteResponseDto> votes
) {

}
