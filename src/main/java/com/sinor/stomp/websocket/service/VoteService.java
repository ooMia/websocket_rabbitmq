package com.sinor.stomp.websocket.service;

import com.sinor.stomp.websocket.common.AbstractCrudService;
import com.sinor.stomp.websocket.model.dto.request.VoteRequestDto;
import com.sinor.stomp.websocket.model.dto.response.VoteResponseDto;
import com.sinor.stomp.websocket.model.entity.board.vote.Vote;
import com.sinor.stomp.websocket.model.entity.board.vote.VoteCandidate;
import com.sinor.stomp.websocket.repository.VoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService extends AbstractCrudService<VoteResponseDto, VoteRequestDto, VoteRepository, Vote, Long> {

    private final VoteCandidateService voteCandidateService;

    @Autowired
    public VoteService(VoteRepository voteRepository, VoteCandidateService voteCandidateService) {
        super(voteRepository);
        this.voteCandidateService = voteCandidateService;
    }

    @Override
    protected Vote fromRequestDtoToEntity(VoteRequestDto voteRequestDto) {
        return Vote.builder()
                .boardId(voteRequestDto.boardId())
                .isAnonymous(voteRequestDto.isAnonymous())
                .isMultiple(voteRequestDto.isMultiple())
                .validUntil(voteRequestDto.validUntil())
                .title(voteRequestDto.title())
                .build();
    }

    @Override
    protected VoteResponseDto fromEntitytoResponseDto(Vote entity) {
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

    @Override
    public VoteResponseDto updateObject(Long id, VoteRequestDto voteRequestDto) {
        return null;
    }


    @Override
    // TODO: related candidates does not appeared in the `voteDidSave`
    public VoteResponseDto createObject(VoteRequestDto voteRequestDto) {
        Vote voteDidSave = repository.save(fromRequestDtoToEntity(voteRequestDto));
        voteRequestDto.candidates().forEach(e -> {
            VoteCandidate candidate = voteCandidateService.fromRequestDtoToEntity(e);
            candidate.setVoteId(voteDidSave.getId());
            voteCandidateService.createObject(candidate);
        });
        return fromEntitytoResponseDto(voteDidSave);
    }
}
