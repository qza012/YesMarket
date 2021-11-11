package com.study.yesmarket.login.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.login.domain.LoginUserInfo;
import com.study.yesmarket.login.domain.SessionLoginRepository;
import com.study.yesmarket.login.dto.LoginDto.LoginRequest;
import com.study.yesmarket.login.exception.NotMatchedIdException;
import com.study.yesmarket.login.exception.NotMatchedPasswordException;
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
    private SessionLoginRepository sessionLoginRepository;

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
        LoginUserInfo dbUserInfo = LoginUserInfo.builder()
                .id(loginRequest.getId())
                .password(loginRequest.getPassword())
                .build();

        given(sessionLoginRepository.findById(loginRequest.getId()))
                .willReturn(Optional.ofNullable(dbUserInfo));
        given(encryptService.isMatch(loginRequest.getPassword(), dbUserInfo.getPassword()))
                .willReturn(true);

        // when
        sessionLoginService.login(loginRequest);

        // then
        verify(sessionLoginRepository, times(1)).findById(loginRequest.getId());
        verify(encryptService, times(1)).isMatch(loginRequest.getPassword(), dbUserInfo.getPassword());
        verify(httpSession, times(1)).setAttribute(SessionLoginService.LOGIN_ID, loginRequest.getId());
    }

    @DisplayName("아이디가 존재하지 않는다면, 'NotMatchedIdException' 에러가 발생한다.")
    @Test
    void login_With_Not_Exist_Id() {
        // given
        given(sessionLoginRepository.findById(loginRequest.getId()))
                .willReturn(Optional.empty());

        // when-then
        assertThrows(NotMatchedIdException.class, () -> sessionLoginService.login(loginRequest));

        verify(sessionLoginRepository, times(1)).findById(loginRequest.getId());
        verify(httpSession, never()).setAttribute(SessionLoginService.LOGIN_ID, loginRequest.getId());
    }

    @DisplayName("아이디는 일치하지만 비밀번호가 틀렸다면, 'NotMatchedPasswordException' 에러가 발생한다 ")
    @Test
    void login_With_Illegal_Password() {
        // given
        LoginUserInfo dbUserInfo = LoginUserInfo.builder()
                .id(loginRequest.getId())
                .password("mockPassword")
                .build();

        given(sessionLoginRepository.findById(loginRequest.getId()))
                .willReturn(Optional.of(dbUserInfo));
        given(encryptService.isMatch(loginRequest.getPassword(), dbUserInfo.getPassword()))
                .willReturn(false);

        // when-then
        assertThrows(NotMatchedPasswordException.class, () -> sessionLoginService.login(loginRequest));

        verify(sessionLoginRepository, times(1)).findById(loginRequest.getId());
        verify(encryptService, times(1)).isMatch(loginRequest.getPassword(), dbUserInfo.getPassword());
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