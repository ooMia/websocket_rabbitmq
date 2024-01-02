package com.sinor.stomp.websocket.service;

import com.sinor.stomp.websocket.common.AbstractCrudService;
import com.sinor.stomp.websocket.model.dto.request.VoteCandidateRequestDto;
import com.sinor.stomp.websocket.model.dto.response.VoteCandidateResponseDto;
import com.sinor.stomp.websocket.model.entity.board.vote.VoteCandidate;
import com.sinor.stomp.websocket.repository.VoteCandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteCandidateService extends
        AbstractCrudService<VoteCandidateResponseDto, VoteCandidateRequestDto, VoteCandidateRepository, VoteCandidate, Long> {

    private final VoteLogService voteLogService;

    @Autowired
    public VoteCandidateService(VoteCandidateRepository voteCandidateRepository, VoteLogService voteLogService) {
        super(voteCandidateRepository);
        this.voteLogService = voteLogService;
    }

    @Override
    protected VoteCandidate fromRequestDtoToEntity(VoteCandidateRequestDto voteCandidateRequestDto) {
        return VoteCandidate.builder()
                .content(voteCandidateRequestDto.content())
                .build();
    }

    @Override
    protected VoteCandidateResponseDto fromEntitytoResponseDto(VoteCandidate voteCandidate) {
        return VoteCandidateResponseDto.builder()
                .id(voteCandidate.getId())
                .voteLogs(voteCandidate.getVoteLogs() != null
                        ? voteCandidate.getVoteLogs().stream().map(voteLogService::fromEntitytoResponseDto).toList()
                        : null)
                .count(voteCandidate.getVoteLogs() != null
                        ? voteCandidate.getVoteLogs().size()
                        : null)
                .build();
    }

    @Override
    public VoteCandidateResponseDto updateObject(Long id, VoteCandidateRequestDto voteCandidateRequestDto) {
        return null;
    }

}