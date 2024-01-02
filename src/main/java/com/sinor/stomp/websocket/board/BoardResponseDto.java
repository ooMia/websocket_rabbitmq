package com.sinor.stomp.websocket.board;

import com.sinor.stomp.websocket.vote.VoteResponseDto;
import java.util.List;
import lombok.Builder;

@Builder
public record BoardResponseDto(
        Long id,
        List<VoteResponseDto> votes
) {

}
