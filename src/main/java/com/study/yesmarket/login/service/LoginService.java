package com.study.yesmarket.login.service;

import com.study.yesmarket.login.dto.LoginDto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    void login(LoginRequest loginRequest);

    void logout();
}