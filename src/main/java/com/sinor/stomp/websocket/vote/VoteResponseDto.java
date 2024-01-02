package com.sinor.stomp.websocket.vote;

import com.sinor.stomp.websocket.vote.candidate.VoteCandidateResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record VoteResponseDto(
        Long id,
        List<VoteCandidateResponseDto> candidates,
        LocalDateTime validUntil,
        Boolean isAnonymous,
        Boolean isMultiple,
        Integer totalCount
) {
}
