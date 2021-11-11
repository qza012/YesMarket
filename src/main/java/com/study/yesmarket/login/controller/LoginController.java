package com.study.yesmarket.login.controller;

import com.study.yesmarket.login.dto.LoginDto.LoginRequest;
import com.study.yesmarket.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class LoginController {

    @Qualifier("sessionLogin")
    private final LoginService loginService;

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }

    @DeleteMapping("/logout")
    public void logout() {
        loginService.logout();
    }
}
