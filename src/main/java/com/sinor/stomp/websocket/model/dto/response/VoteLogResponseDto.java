package com.sinor.stomp.websocket.model.dto.response;


import lombok.Builder;

@Builder

public record VoteLogResponseDto(
        Long id,
        Long voteCandidateId,
        Long memberId
) {

}
