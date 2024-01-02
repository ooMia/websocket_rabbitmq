package com.sinor.stomp.websocket.vote.candidate;

import com.sinor.stomp.websocket.vote.log.VoteLogResponseDto;
import java.util.List;
import lombok.Builder;

@Builder
public record VoteCandidateResponseDto(
        Long id,
        List<VoteLogResponseDto> voteLogs,
        Integer count
) {
}
