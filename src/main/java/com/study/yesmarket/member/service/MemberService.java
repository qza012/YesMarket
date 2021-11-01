package com.study.yesmarket.member.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.dto.MemberDto.JoinRequest;
import com.study.yesmarket.member.exception.DuplicateIdException;
import com.study.yesmarket.member.exception.DuplicateNicknameException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Qualifier("BCrypt")
    private final EncryptService encryptService;
    private final MemberRepository memberRepository;

    @Transactional
    public void join(JoinRequest joinRequest) {
        if (this.isDuplicateId(joinRequest.getId())) {
            throw new DuplicateIdException();
        }

        if (this.isDuplicateId(joinRequest.getId())) {
            throw new DuplicateNicknameException();
        }

        String encryptedPassword = encryptService.encrypt(joinRequest.getPassword());
        Member member = new Member(joinRequest, encryptedPassword);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicateNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicateId(String id) {
        return memberRepository.existsById(id);
    }
}
