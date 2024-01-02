package com.sinor.stomp.websocket.vote.candidate;

import lombok.Builder;

@Builder
public record VoteCandidateRequestDto(
        String content
) {
}
