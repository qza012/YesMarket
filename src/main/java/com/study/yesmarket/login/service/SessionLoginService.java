package com.study.yesmarket.login.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.login.domain.LoginUserInfo;
import com.study.yesmarket.login.domain.SessionLoginRepository;
import com.study.yesmarket.login.dto.LoginDto.LoginRequest;
import com.study.yesmarket.login.exception.NotMatchedIdException;
import com.study.yesmarket.login.exception.NotMatchedPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class SessionLoginService implements LoginService{

    static final String LOGIN_ID = "LoginId";

    @Qualifier("BCrypt")
    private final EncryptService encryptService;

    private final SessionLoginRepository sessionLoginRepository;

    private final HttpSession httpSession;

    @Override
    public void login(LoginRequest loginRequest) {
        LoginUserInfo loginUserInfo = sessionLoginRepository.findById(loginRequest.getId())
                .orElseThrow(NotMatchedIdException::new);

        if (!encryptService.isMatch(loginRequest.getPassword(), loginUserInfo.getPassword())) {
            throw new NotMatchedPasswordException();
        }

        httpSession.setAttribute(LOGIN_ID, loginRequest.getId());
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(LOGIN_ID);
    }
}
