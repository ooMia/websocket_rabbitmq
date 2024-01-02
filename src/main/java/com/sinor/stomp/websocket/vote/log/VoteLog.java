package com.sinor.stomp.websocket.vote.log;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class VoteLog {
    // parent
    private Long voteCandidateId;

    // vote_candidate > vote_log <-> member
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // mapping
    private Long memberId;

    // properties

    @Builder
    public VoteLog(Long voteCandidateId, Long memberId) {
        this.voteCandidateId = voteCandidateId;
        this.memberId = memberId;
    }

}
