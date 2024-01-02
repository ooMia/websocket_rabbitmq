package com.sinor.stomp.websocket.vote.candidate;

import com.sinor.stomp.websocket.vote.log.VoteLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteCandidateService {

    private final VoteLogService voteLogService;

    @Autowired
    public VoteCandidateService(VoteCandidateRepository voteCandidateRepository, VoteLogService voteLogService) {
        this.voteLogService = voteLogService;
    }

    public VoteCandidate fromRequestDtoToEntity(VoteCandidateRequestDto voteCandidateRequestDto) {
        return VoteCandidate.builder()
                .content(voteCandidateRequestDto.content())
                .build();
    }

    public VoteCandidateResponseDto fromEntitytoResponseDto(VoteCandidate voteCandidate) {
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

    public VoteCandidateResponseDto readObject(Long id) {
        return null;
    }

    public VoteCandidateResponseDto deleteObject(Long id) {
        return null;
    }

    public VoteCandidateResponseDto updateObject(Long id, VoteCandidateRequestDto requestDto) {
        return null;
    }

    public VoteCandidateResponseDto createObject(VoteCandidateRequestDto requestDto) {
        return null;
    }

    public VoteCandidateResponseDto createObject(VoteCandidate entity) {
        return null;
    }
}