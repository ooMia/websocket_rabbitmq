package com.sinor.stomp.websocket.repository;

import com.sinor.stomp.websocket.model.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
