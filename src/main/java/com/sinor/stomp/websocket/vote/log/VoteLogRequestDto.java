package com.sinor.stomp.websocket.vote.log;

import lombok.Builder;

@Builder
public record VoteLogRequestDto(
        Long voteCandidateId,
        Long memberId
) {
}
