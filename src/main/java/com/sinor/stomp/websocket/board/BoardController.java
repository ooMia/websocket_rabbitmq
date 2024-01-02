package com.sinor.stomp.websocket.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> createObject(
            @RequestBody BoardRequestDto boardRequestDto
    ) {
        return ResponseEntity.ok(boardService.createObject(boardRequestDto));
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<BoardResponseDto> readObject(
            @PathVariable(name = "board_id") Long id
    ) {
        return ResponseEntity.ok(boardService.readObject(id));
    }


    @PutMapping("/{board_id}")
    public ResponseEntity<BoardResponseDto> updateObject(
            @PathVariable(name = "board_id") Long id,
            @RequestBody BoardRequestDto boardRequestDto
    ) {
        return ResponseEntity.ok(boardService.updateObject(id, boardRequestDto));
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<BoardResponseDto> deleteObject(
            @PathVariable(name = "board_id") Long id
    ) {
        return ResponseEntity.ok(boardService.deleteObject(id));
    }
}
