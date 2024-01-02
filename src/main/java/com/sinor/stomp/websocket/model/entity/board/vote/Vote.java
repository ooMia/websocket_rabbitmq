package com.sinor.stomp.websocket.model.entity.board.vote;

import com.sinor.stomp.websocket.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vote implements BaseEntity<Long> {
    // parent
    private Long boardId;

    // board > vote > vote_candidate
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // child
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "voteId")
    private List<VoteCandidate> candidates;

    // properties
    private String title;
    private LocalDateTime validUntil;
    private Boolean isAnonymous;
    private Boolean isMultiple;

    @Builder
    public Vote(String title, LocalDateTime validUntil, Boolean isAnonymous, Boolean isMultiple, Long boardId) {
        this.title = title;
        this.validUntil = validUntil;
        this.isAnonymous = isAnonymous;
        this.isMultiple = isMultiple;
        this.boardId = boardId;
    }

}
