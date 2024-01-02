package com.sinor.stomp.websocket.vote.candidate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteCandidateRepository extends CrudRepository<VoteCandidate, Long> {
}
