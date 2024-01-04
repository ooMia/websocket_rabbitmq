package com.sinor.stomp.vote.service;

import com.sinor.stomp.vote.common.AbstractCrudService;
import com.sinor.stomp.vote.model.dto.request.BoardRequestDto;
import com.sinor.stomp.vote.model.dto.response.BoardResponseDto;
import com.sinor.stomp.vote.model.entity.Board;
import com.sinor.stomp.vote.repository.BoardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService extends AbstractCrudService<BoardResponseDto, BoardRequestDto, BoardRepository, Board, Long> {


    @Autowired
    public BoardService(BoardRepository boardRepository) {
        super(boardRepository);
    }

    @Override
    protected Board fromRequestDtoToEntity(BoardRequestDto boardRequestDto) {
        return Board.builder().build();
    }

    @Override
    protected BoardResponseDto fromEntitytoResponseDto(Board entity) {
        return BoardResponseDto.builder()
                .id(entity.getId())
                .votes(entity.getVotes() != null
                        ? entity.getVotes().stream().map(e -> e.fromEntitytoResponseDto())
                        .toList() : null)
                .build();
    }

    @Override
    public BoardResponseDto updateObject(Long id, BoardRequestDto boardRequestDto) {
        return null;
    }

    public List<BoardResponseDto> readAllObjects() {
        return repository.findAll().stream().map(this::fromEntitytoResponseDto).toList();
    }
}
