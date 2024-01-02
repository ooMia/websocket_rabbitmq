package com.sinor.stomp.websocket.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    protected MemberService(MemberRepository repository) {
    }

    protected Member fromRequestDtoToEntity(MemberRequestDto memberRequestDto) {
        return Member.builder().name(memberRequestDto.name()).profile(memberRequestDto.profile()).build();
    }

    protected MemberResponseDto fromEntitytoResponseDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .profile(member.getProfile())
                .build();
    }


    public MemberResponseDto readObject(Long id) {
        return null;
    }

    public MemberResponseDto deleteObject(Long id) {
        return null;
    }

    public MemberResponseDto updateObject(Long id, MemberRequestDto requestDto) {
        return null;
    }

    public MemberResponseDto createObject(MemberRequestDto requestDto) {
        return null;
    }
}