package com.sinor.stomp.websocket.vote;

import com.sinor.stomp.websocket.vote.candidate.VoteCandidateService;
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
@RequestMapping(path = "/vote")
public class VoteController {
    private final VoteService voteService;
    private final VoteCandidateService voteCandidateService;


    @Autowired
    public VoteController(
            VoteService voteService, VoteCandidateService voteCandidateService) {
        this.voteService = voteService;
        this.voteCandidateService = voteCandidateService;
    }


    @PostMapping
    public ResponseEntity<VoteResponseDto> createObject(
            @RequestBody VoteRequestDto requestDto
    ) {
        return ResponseEntity.ok(voteService.createObject(requestDto));
    }

    @GetMapping("/{vote_id}")
    public ResponseEntity<VoteResponseDto> readObject(
            @PathVariable(name = "vote_id") Long id
    ) {
        return ResponseEntity.ok(voteService.readObject(id));
    }

    @PutMapping("/{vote_id}")
    public ResponseEntity<VoteResponseDto> updateObject(
            @PathVariable(name = "vote_id") Long id,
            VoteRequestDto voteRequestDto
    ) {
        return ResponseEntity.ok(voteService.updateObject(id, voteRequestDto));
    }

    @DeleteMapping("/{vote_id}")
    public ResponseEntity<VoteResponseDto> deleteObject(
            @PathVariable(name = "vote_id") Long id
    ) {
        return ResponseEntity.ok(voteService.deleteObject(id));
    }

}
