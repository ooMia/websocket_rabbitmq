package com.sinor.stomp.websocket.service;

import com.sinor.stomp.websocket.common.AbstractCrudService;
import com.sinor.stomp.websocket.model.dto.request.MemberRequestDto;
import com.sinor.stomp.websocket.model.dto.response.MemberResponseDto;
import com.sinor.stomp.websocket.model.entity.Member;
import com.sinor.stomp.websocket.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService extends
        AbstractCrudService<MemberResponseDto, MemberRequestDto, MemberRepository, Member, Long> {

    @Autowired
    protected MemberService(MemberRepository repository) {
        super(repository);
    }

    @Override
    protected Member fromRequestDtoToEntity(MemberRequestDto memberRequestDto) {
        return Member.builder().name(memberRequestDto.name()).profile(memberRequestDto.profile()).build();
    }

    @Override
    protected MemberResponseDto fromEntitytoResponseDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .profile(member.getProfile())
                .build();
    }

    @Override
    public MemberResponseDto updateObject(Long id, MemberRequestDto memberRequestDto) {
        return null;
    }


}