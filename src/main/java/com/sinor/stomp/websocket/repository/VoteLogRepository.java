package com.sinor.stomp.websocket.repository;

import com.sinor.stomp.websocket.model.entity.board.vote.VoteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteLogRepository extends JpaRepository<VoteLog, Long> {
}
