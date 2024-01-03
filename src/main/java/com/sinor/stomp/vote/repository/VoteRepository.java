package com.sinor.stomp.vote.repository;

import com.sinor.stomp.vote.model.entity.board.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
