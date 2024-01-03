package com.sinor.stomp.vote.service;

import com.sinor.stomp.rabbitmq.service.VoteLogMessageService;
import com.sinor.stomp.vote.common.AbstractCrudService;
import com.sinor.stomp.vote.model.dto.request.VoteLogRequestDto;
import com.sinor.stomp.vote.model.dto.response.VoteLogResponseDto;
import com.sinor.stomp.vote.model.entity.board.vote.VoteLog;
import com.sinor.stomp.vote.repository.VoteLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteLogService extends
        AbstractCrudService<VoteLogResponseDto, VoteLogRequestDto, VoteLogRepository, VoteLog, Long> {


    private final VoteLogMessageService voteLogMessageService;


    @Autowired
    public VoteLogService(VoteLogRepository voteLogRepository, VoteLogMessageService voteLogMessageService) {
        super(voteLogRepository);
        this.voteLogMessageService = voteLogMessageService;
    }


    @Override
    public VoteLog fromRequestDtoToEntity(VoteLogRequestDto requestDto) {
        return VoteLog.builder()
                .voteItemId(requestDto.voteItemId())
                .memberId(requestDto.memberId())
                .build();
    }

    @Override
    public VoteLogResponseDto fromEntitytoResponseDto(VoteLog entity) {
        return VoteLogResponseDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .voteItemId(entity.getVoteItemId())
                .build();
    }

    @Override
    public VoteLogResponseDto createObject(VoteLogRequestDto requestDto) {
        VoteLogResponseDto responseDto = super.createObject(requestDto);
        voteLogMessageService.broadcastLogByItemId(responseDto);
        return responseDto;
    }

    @Override
    public VoteLogResponseDto updateObject(Long id, VoteLogRequestDto requestDto) {
        return null;
    }


}