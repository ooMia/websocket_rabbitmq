package com.sinor.stomp.websocket.vote.log;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteLogRepository extends CrudRepository<VoteLog, Long> {
}
