package com.sinor.stomp.rabbitmq.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sinor.stomp.vote.model.dto.response.VoteLogResponseDto;
import com.sinor.stomp.vote.model.entity.board.vote.QVote;
import com.sinor.stomp.vote.model.entity.board.vote.VoteItem;
import com.sinor.stomp.vote.repository.VoteItemRepository;
import jakarta.persistence.EntityManager;
import java.util.NoSuchElementException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteLogMessageService {

    private final RabbitTemplate rabbitTemplate;
    private final VoteItemRepository voteItemRepository;
    private final EntityManager entityManager;

    @Autowired
    public VoteLogMessageService(RabbitTemplate rabbitTemplate, VoteItemRepository voteItemRepository,
                                 EntityManager entityManager) {
        this.rabbitTemplate = rabbitTemplate;
        this.voteItemRepository = voteItemRepository;
        this.entityManager = entityManager;
    }

    public void broadcastLogByItemId(VoteLogResponseDto voteLogResponseDto) {
        Long voteId = findVoteIdFromVoteItemId(voteLogResponseDto.voteItemId());
        // Broadcast VoteLog for clients who subscribe given roomId
        rabbitTemplate.convertAndSend("vote", voteId + "", voteLogResponseDto);
    }


    private Long findVoteIdFromVoteItemId(Long voteItemId) throws NoSuchElementException {

        // Find voteLogEntity from voteLogId
        VoteItem voteItem = voteItemRepository.findById(voteItemId).orElseThrow();

        // Find voteId from voteItemEntity
        QVote qVote = QVote.vote;
        return new JPAQueryFactory(entityManager)
                .selectFrom(qVote)
                .where(qVote.items.contains(voteItem)).fetchFirst().getId();
    }
}
