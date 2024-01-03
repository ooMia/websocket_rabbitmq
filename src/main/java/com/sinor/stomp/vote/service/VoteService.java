package com.sinor.stomp.vote.service;

import com.sinor.stomp.vote.common.AbstractCrudService;
import com.sinor.stomp.vote.model.dto.request.VoteRequestDto;
import com.sinor.stomp.vote.model.dto.response.VoteResponseDto;
import com.sinor.stomp.vote.model.entity.board.vote.Vote;
import com.sinor.stomp.vote.model.entity.board.vote.VoteItem;
import com.sinor.stomp.vote.repository.VoteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService extends AbstractCrudService<VoteResponseDto, VoteRequestDto, VoteRepository, Vote, Long> {

    private final VoteItemService voteItemService;

    @Autowired
    public VoteService(VoteRepository voteRepository, VoteItemService voteItemService) {
        super(voteRepository);
        this.voteItemService = voteItemService;
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
                        ? entity.getCandidates().stream().map(voteItemService::fromEntitytoResponseDto).toList()
                        : null)
                .totalCount(entity.getCandidates() != null
                        ? getTotalCount(entity.getCandidates())
                        : 0)
                .build();
    }

    private Integer getTotalCount(List<VoteItem> candidates) {
        Integer sum = 0;
        for (VoteItem e : candidates) {
            sum += voteItemService.fromEntitytoResponseDto(e).count();
        }
        return sum;
    }

    @Override
    public VoteResponseDto updateObject(Long id, VoteRequestDto voteRequestDto) {
        return null;
    }


    @Override
    public VoteResponseDto createObject(VoteRequestDto requestDto) {
        Vote voteDidSave = repository.save(fromRequestDtoToEntity(requestDto));
        requestDto.items().forEach(e -> {
            VoteItem item = voteItemService.fromRequestDtoToEntity(e);
            item.setVoteId(voteDidSave.getId());
            voteItemService.createObject(item);
        });
        return fromEntitytoResponseDto(voteDidSave);
    }
}
