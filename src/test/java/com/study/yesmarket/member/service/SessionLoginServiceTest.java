package com.study.yesmarket.member.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.dto.MemberDto.LoginRequest;
import com.study.yesmarket.member.exception.NotMatchedIdException;
import com.study.yesmarket.member.exception.NotMatchedPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionLoginServiceTest {

    @Mock
    private EncryptService encryptService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private SessionLoginService sessionLoginService;

    private LoginRequest loginRequest;

    @BeforeEach
    void createUser() {
        loginRequest = LoginRequest.builder()
                .id("test123")
                .password("pass123")
                .build();
    }

    @DisplayName("아이디와 비밀번호가 일치하면 로그인에 성공한다.")
    @Test
    void login() {
        // given
        Member dbMember = Member.builder()
                .memberId(loginRequest.getId())
                .password(loginRequest.getPassword())
                .build();

        given(memberRepository.findById(loginRequest.getId()))
                .willReturn(Optional.ofNullable(dbMember));
        given(encryptService.isMatch(loginRequest.getPassword(), dbMember.getPassword()))
                .willReturn(true);

        // when
        sessionLoginService.login(loginRequest);

        // then
        verify(memberRepository, times(1)).findById(loginRequest.getId());
        verify(encryptService, times(1)).isMatch(loginRequest.getPassword(), dbMember.getPassword());
        verify(httpSession, times(1)).setAttribute(SessionLoginService.LOGIN_ID, loginRequest.getId());
    }

    @DisplayName("아이디가 존재하지 않는다면, 'NotMatchedIdException' 예외가 발생한다.")
    @Test
    void login_With_Not_Exist_Id() {
        // given
        given(memberRepository.findById(loginRequest.getId()))
                .willReturn(Optional.empty());

        // when-then
        assertThrows(NotMatchedIdException.class, () -> sessionLoginService.login(loginRequest));

        verify(memberRepository, times(1)).findById(loginRequest.getId());
        verify(httpSession, never()).setAttribute(SessionLoginService.LOGIN_ID, loginRequest.getId());
    }

    @DisplayName("아이디는 일치하지만 비밀번호가 틀렸다면, 'NotMatchedPasswordException' 예외가 발생한다 ")
    @Test
    void login_With_Illegal_Password() {
        // given
        Member dbMember = Member.builder()
                .memberId(loginRequest.getId())
                .password("mockPassword")
                .build();

        given(memberRepository.findById(loginRequest.getId()))
                .willReturn(Optional.of(dbMember));
        given(encryptService.isMatch(loginRequest.getPassword(), dbMember.getPassword()))
                .willReturn(false);

        // when-then
        assertThrows(NotMatchedPasswordException.class, () -> sessionLoginService.login(loginRequest));

        verify(memberRepository, times(1)).findById(loginRequest.getId());
        verify(encryptService, times(1)).isMatch(loginRequest.getPassword(), dbMember.getPassword());
        verify(httpSession, never()).setAttribute(SessionLoginService.LOGIN_ID, loginRequest.getId());
    }

    @DisplayName("로그아웃 시, 세션에 저장된 값을 삭제한다.")
    @Test
    void logout() {
        // when
        sessionLoginService.logout();

        // then
        verify(httpSession, times(1)).removeAttribute(SessionLoginService.LOGIN_ID);
    }
}