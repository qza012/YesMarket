package com.study.yesmarket.member.service;

import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.dto.MemberDto.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(JoinRequest joinRequest) {
        memberRepository.save(joinRequest.toEntity());
    }
}
