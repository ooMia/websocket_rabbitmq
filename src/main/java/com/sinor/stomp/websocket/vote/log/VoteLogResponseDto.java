package com.sinor.stomp.websocket.vote.log;


import lombok.Builder;

@Builder

public record VoteLogResponseDto(
        Long id,
        Long voteCandidateId,
        Long memberId
) {

}
