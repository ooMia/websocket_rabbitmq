package com.sinor.stomp.websocket.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinor.stomp.websocket.vote.candidate.VoteCandidateRequestDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record VoteRequestDto(
        Long boardId,
        String title,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime validUntil,
        Boolean isAnonymous,
        Boolean isMultiple,
        List<VoteCandidateRequestDto> candidates
) {
}
