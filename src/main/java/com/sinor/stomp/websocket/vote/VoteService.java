package com.sinor.stomp.websocket.vote;

import com.sinor.stomp.websocket.vote.candidate.VoteCandidate;
import com.sinor.stomp.websocket.vote.candidate.VoteCandidateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteCandidateService voteCandidateService;

    @Autowired
    public VoteService(VoteRepository voteRepository, VoteCandidateService voteCandidateService) {
        this.voteRepository = voteRepository;
        this.voteCandidateService = voteCandidateService;

    }


    public Vote fromRequestDtoToEntity(VoteRequestDto voteRequestDto) {
        return Vote.builder()
                .boardId(voteRequestDto.boardId())
                .isAnonymous(voteRequestDto.isAnonymous())
                .isMultiple(voteRequestDto.isMultiple())
                .validUntil(voteRequestDto.validUntil())
                .title(voteRequestDto.title())
                .build();
    }


    public VoteResponseDto fromEntitytoResponseDto(Vote entity) {
        return VoteResponseDto.builder()
                .id(entity.getId())
                .validUntil(entity.getValidUntil())
                .isAnonymous(entity.getIsAnonymous())
                .isMultiple(entity.getIsMultiple())
                .candidates(entity.getCandidates() != null
                        ? entity.getCandidates().stream().map(voteCandidateService::fromEntitytoResponseDto).toList()
                        : null)
                .totalCount(entity.getCandidates() != null
                        ? getTotalCount(entity.getCandidates())
                        : 0)
                .build();
    }

    private Integer getTotalCount(List<VoteCandidate> candidates) {
        Integer sum = 0;
        for (VoteCandidate e : candidates) {
            sum += voteCandidateService.fromEntitytoResponseDto(e).count();
        }
        return sum;
    }

    public VoteResponseDto readObject(Long id) {
        return null;
    }

    public VoteResponseDto deleteObject(Long id) {
        return null;
    }

    public VoteResponseDto updateObject(Long id, VoteRequestDto requestDto) {
        return null;
    }


    // TODO: related candidates does not appeared in the `voteDidSave`
    public VoteResponseDto createObject(VoteRequestDto requestDto) {
        Vote voteDidSave = voteRepository.save(fromRequestDtoToEntity(requestDto));
        requestDto.candidates().forEach(e -> {
            VoteCandidate candidate = voteCandidateService.fromRequestDtoToEntity(e);
            candidate.setVoteId(voteDidSave.getId());
            voteCandidateService.createObject(candidate);
        });
        return fromEntitytoResponseDto(voteDidSave);
    }
}
