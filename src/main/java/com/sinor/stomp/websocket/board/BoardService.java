package com.sinor.stomp.websocket.board;

import com.sinor.stomp.websocket.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final VoteService voteService;

    @Autowired
    public BoardService(VoteService voteService) {
        this.voteService = voteService;
    }

    public Board fromRequestDtoToEntity(BoardRequestDto boardRequestDto) {
        return Board.builder().build();
    }

    public BoardResponseDto fromEntitytoResponseDto(Board entity) {
        return BoardResponseDto.builder()
                .id(entity.getId())
                .votes(entity.getVotes() != null
                        ? entity.getVotes().stream().map(voteService::fromEntitytoResponseDto)
                        .toList() : null)
                .build();
    }

    public BoardResponseDto readObject(Long id) {
        return null;
    }

    public BoardResponseDto deleteObject(Long id) {
        return null;
    }

    public BoardResponseDto updateObject(Long id, BoardRequestDto boardRequestDto) {
        return null;
    }

    public BoardResponseDto createObject(BoardRequestDto boardRequestDto) {
        return null;
    }


}
