package com.study.yesmarket.member.controller;

import com.study.yesmarket.member.dto.MemberDto;
import com.study.yesmarket.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/users")
    public ResponseEntity<Void> join(@Valid @RequestBody MemberDto.JoinRequest joinRequest) {
        memberService.join(joinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
