package com.sinor.stomp.websocket.member;

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
@RequestMapping(path = "/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping
    public ResponseEntity<MemberResponseDto> createObject(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(memberService.createObject(requestDto));
    }

    @GetMapping("/{member_id}")
    public ResponseEntity<MemberResponseDto> readObject(@PathVariable(value = "member_id") Long id) {
        return ResponseEntity.ok(memberService.readObject(id));
    }


    @PutMapping("/{member_id}")
    public ResponseEntity<MemberResponseDto> updateObject(@PathVariable(value = "member_id") Long id,
                                                          MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.updateObject(id, memberRequestDto));
    }

    @DeleteMapping("/{member_id}")
    public ResponseEntity<MemberResponseDto> deleteObject(@PathVariable(value = "member_id") Long id) {
        return ResponseEntity.ok(memberService.deleteObject(id));
    }

}
