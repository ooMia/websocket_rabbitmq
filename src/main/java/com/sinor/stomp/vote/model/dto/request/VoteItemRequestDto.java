package com.sinor.stomp.vote.model.dto.request;

import lombok.Builder;

@Builder
public record VoteItemRequestDto(
        String content
) {
}
