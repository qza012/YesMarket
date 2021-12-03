package com.study.yesmarket.member.service;

import com.study.yesmarket.member.dto.MemberDto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    void login(LoginRequest loginRequest);

    void logout();
}
