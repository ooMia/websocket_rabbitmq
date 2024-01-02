package com.sinor.stomp.websocket.vote.log;

import com.sinor.stomp.websocket.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteLogService {

    private final MemberService memberService;

    @Autowired
    public VoteLogService(VoteLogRepository voteLogRepository, MemberService memberService) {
        this.memberService = memberService;
    }

    public VoteLog fromRequestDtoToEntity(VoteLogRequestDto voteLogRequestDto) {
        return VoteLog.builder()
                .voteCandidateId(voteLogRequestDto.voteCandidateId())
                .memberId(voteLogRequestDto.memberId())
                .build();
    }

    public VoteLogResponseDto fromEntitytoResponseDto(VoteLog voteLog) {
        return VoteLogResponseDto.builder()
                .id(voteLog.getId())
                .memberId(voteLog.getMemberId())
                .voteCandidateId(voteLog.getVoteCandidateId())
                .build();
    }


    public VoteLogResponseDto readObject(Long id) {
        return null;
    }

    public VoteLogResponseDto deleteObject(Long id) {
        return null;
    }

    public VoteLogResponseDto updateObject(Long id, VoteLogRequestDto boardRequestDto) {
        return null;
    }

    public VoteLogResponseDto createObject(VoteLogRequestDto boardRequestDto) {
        return null;
    }
}