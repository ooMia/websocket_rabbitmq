package com.sinor.stomp.websocket.vote.candidate;

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
@RequestMapping(path = "/vote/candidate")
public class VoteCandidateController {
    private final VoteCandidateService voteCandidateService;

    @Autowired
    public VoteCandidateController(VoteCandidateService voteCandidateService) {
        this.voteCandidateService = voteCandidateService;
    }

    @PostMapping
    public ResponseEntity<VoteCandidateResponseDto> createObject(
            @RequestBody VoteCandidateRequestDto requestDto
    ) {
        return ResponseEntity.ok(voteCandidateService.createObject(requestDto));
    }

    @GetMapping("/{candidate_id}")
    public ResponseEntity<VoteCandidateResponseDto> readObject(
            @PathVariable(value = "candidate_id") Long id
    ) {
        return ResponseEntity.ok(voteCandidateService.readObject(id));
    }


    @PutMapping("/{candidate_id}")
    public ResponseEntity<VoteCandidateResponseDto> updateObject(
            @PathVariable(value = "candidate_id") Long id,
            VoteCandidateRequestDto voteCandidateRequestDto
    ) {
        return ResponseEntity.ok(voteCandidateService.updateObject(id, voteCandidateRequestDto));
    }

    @DeleteMapping("/{candidate_id}")
    public ResponseEntity<VoteCandidateResponseDto> deleteObject(
            @PathVariable(value = "candidate_id") Long id
    ) {
        return ResponseEntity.ok(voteCandidateService.deleteObject(id));
    }

}
