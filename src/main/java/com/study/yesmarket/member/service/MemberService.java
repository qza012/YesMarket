package com.study.yesmarket.member.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.dto.MemberDto.DuplicateIdResponse;
import com.study.yesmarket.member.dto.MemberDto.DuplicateNicknameResponse;
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
        if (checkDuplicateNickname(joinRequest.getId())) {
            throw new DuplicateIdException();
        }

        if (checkDuplicateId(joinRequest.getNickname())) {
            throw new DuplicateNicknameException();
        }

        String encryptedPassword = encryptService.encrypt(joinRequest.getPassword());
        Member member = new Member(joinRequest, encryptedPassword);
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public DuplicateNicknameResponse isDuplicateNickname(String nickname) {
        return new DuplicateNicknameResponse(checkDuplicateNickname(nickname));
    }

    @Transactional(readOnly = true)
    public DuplicateIdResponse isDuplicateId(String id) {
        return new DuplicateIdResponse(checkDuplicateId(id));
    }

    private boolean checkDuplicateNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    private boolean checkDuplicateId(String id) {
        return memberRepository.existsById(id);
    }
}
