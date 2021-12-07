package com.study.yesmarket.member.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.dto.MemberDto.LoginRequest;
import com.study.yesmarket.member.exception.NotMatchedIdException;
import com.study.yesmarket.member.exception.NotMatchedPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service("sessionLogin")
public class SessionLoginService implements LoginService{

    @Qualifier("BCrypt")
    private final EncryptService encryptService;

    private final MemberRepository memberRepository;

    private final HttpSession httpSession;

    @Override
    @Transactional(readOnly = true)
    public void login(LoginRequest loginRequest) {
        Member member = memberRepository.findById(loginRequest.getId())
                .orElseThrow(NotMatchedIdException::new);

        if (!encryptService.isMatch(loginRequest.getPassword(), member.getPassword())) {
            throw new NotMatchedPasswordException();
        }

        httpSession.setAttribute(LOGIN_ID, loginRequest.getId());
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(LOGIN_ID);
    }
}
