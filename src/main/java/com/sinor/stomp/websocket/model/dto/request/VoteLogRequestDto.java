package com.sinor.stomp.websocket.model.dto.request;

import lombok.Builder;

@Builder
public record VoteLogRequestDto(
        Long voteCandidateId,
        Long memberId
) {
}
