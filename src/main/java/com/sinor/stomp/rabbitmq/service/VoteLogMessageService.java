package com.sinor.stomp.rabbitmq.service;

import com.sinor.stomp.vote.model.dto.response.VoteLogResponseDto;
import com.sinor.stomp.vote.repository.VoteItemRepository;
import java.util.NoSuchElementException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteLogMessageService {

    private final RabbitTemplate rabbitTemplate;
    private final VoteItemRepository voteItemRepository;

    @Autowired
    public VoteLogMessageService(RabbitTemplate rabbitTemplate, VoteItemRepository voteItemRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.voteItemRepository = voteItemRepository;
    }

    public void broadcastLogByItemId(VoteLogResponseDto voteLogResponseDto) {
        Long voteId = findVoteIdFromVoteItemId(voteLogResponseDto.voteItemId());
        // Broadcast VoteLog for clients who subscribe given roomId
        rabbitTemplate.convertAndSend("vote", voteId + "", voteLogResponseDto);
    }


    private Long findVoteIdFromVoteItemId(Long voteItemId) throws NoSuchElementException {
        return voteItemRepository.findById(voteItemId).orElseThrow().getVoteId();
    }
}
