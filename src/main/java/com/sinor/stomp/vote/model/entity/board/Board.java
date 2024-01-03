package com.sinor.stomp.vote.model.entity.board;

import com.sinor.stomp.vote.common.BaseEntity;
import com.sinor.stomp.vote.model.entity.board.vote.Vote;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Board implements BaseEntity<Long> {
    // top-level class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // child
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boardId")
    private Set<Vote> votes;

    // properties

    @Builder
    public Board(Set<Vote> votes) {
        this.votes = votes;
    }
}
