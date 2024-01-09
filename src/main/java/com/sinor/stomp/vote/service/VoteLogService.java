package com.sinor.stomp.vote.service;

import com.sinor.stomp.rabbitmq.service.VoteLogMessageService;
import com.sinor.stomp.vote.common.AbstractCrudService;
import com.sinor.stomp.vote.model.dto.request.VoteLogRequestDto;
import com.sinor.stomp.vote.model.dto.response.VoteLogResponseDto;
import com.sinor.stomp.vote.model.entity.VoteLog;
import com.sinor.stomp.vote.repository.VoteLogRepository;
import java.util.NoSuchElementException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@Log4j2
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
    public VoteLogResponseDto fromEntityToResponseDto(VoteLog entity) {
        return VoteLogResponseDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .voteItemId(entity.getVoteItemId())
                .build();
    }

    public VoteLogResponseDto createChunkedObject(VoteLogRequestDto requestDto, Long numberDataRemains) {
        VoteLogResponseDto responseDto = super.createObject(requestDto);
        voteLogMessageService.broadcastChunkedLogByItemId(responseDto, "post", numberDataRemains);
        return responseDto;
    }

    public VoteLogResponseDto createChunkedObject(VoteLog entity, Long numberDataRemains) {
        VoteLogResponseDto responseDto = super.createObject(entity);
        voteLogMessageService.broadcastChunkedLogByItemId(responseDto, "post", numberDataRemains);
        return responseDto;
    }


    public VoteLogResponseDto deleteChunkedObject(Long id, Long numberDataRemains) {
        VoteLogResponseDto responseDto = super.deleteObject(id);
        voteLogMessageService.broadcastChunkedLogByItemId(responseDto, "delete", numberDataRemains);
        return responseDto;
    }


    @Override
    public VoteLogResponseDto updateObject(Long id, VoteLogRequestDto requestDto) throws NoSuchElementException {
        VoteLog entity = repository.findById(id).orElseThrow();
        VoteLogResponseDto voteLogToDelete = fromEntityToResponseDto(entity);
        entity.setVoteItemId(requestDto.voteItemId());
        VoteLogResponseDto voteLogToPost = fromEntityToResponseDto(repository.save(entity));

        voteLogMessageService.broadcastChunkedLogByItemId(voteLogToDelete, "delete", 1L);
        voteLogMessageService.broadcastChunkedLogByItemId(voteLogToPost, "post", 0L);
        return voteLogToPost;
    }

    public VoteLogResponseDto findOneByAttributes(Long voteItemId, Long memberId) throws NoSuchElementException {
        Example<VoteLog> example = Example.of(
                VoteLog.builder().voteItemId(voteItemId).memberId(memberId).build());
        return fromEntityToResponseDto(
                repository.findOne(example).orElseThrow());
    }

}