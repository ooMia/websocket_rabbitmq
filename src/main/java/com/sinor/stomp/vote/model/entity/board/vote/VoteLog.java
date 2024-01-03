package com.sinor.stomp.vote.model.entity.board.vote;

import com.sinor.stomp.vote.common.BaseEntity;
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
public class VoteLog implements BaseEntity<Long> {
    // parent
    private Long voteItemId;

    // vote_candidate > vote_log <-> member
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // mapping
    private Long memberId;

    // properties

    @Builder
    public VoteLog(Long voteItemId, Long memberId) {
        this.voteItemId = voteItemId;
        this.memberId = memberId;
    }

}
