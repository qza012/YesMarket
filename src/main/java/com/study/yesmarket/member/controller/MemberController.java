package com.study.yesmarket.member.controller;

import com.study.yesmarket.login.dto.LoginDto;
import com.study.yesmarket.member.service.LoginService;
import com.study.yesmarket.member.dto.MemberDto.DuplicateIdResponse;
import com.study.yesmarket.member.dto.MemberDto.DuplicateNicknameResponse;
import com.study.yesmarket.member.dto.MemberDto.JoinRequest;
import com.study.yesmarket.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    @Qualifier("sessionLogin")
    private final LoginService loginService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> join(@Valid @RequestBody JoinRequest joinRequest) {
        memberService.join(joinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/nicknames/{nickname}/duplicate")
    public ResponseEntity<DuplicateNicknameResponse> isDuplicateNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(memberService.isDuplicateNickname(nickname));
    }

    @GetMapping("/ids/{id}/duplicate")
    public ResponseEntity<DuplicateIdResponse> isDuplicateId(@PathVariable String id) {
        return ResponseEntity.ok(memberService.isDuplicateId(id));
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginDto.LoginRequest loginRequest) {
        loginService.login(loginRequest);
    }

    @DeleteMapping("/logout")
    public void logout() {
        loginService.logout();
    }
}
