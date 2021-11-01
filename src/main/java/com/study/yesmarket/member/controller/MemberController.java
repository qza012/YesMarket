package com.study.yesmarket.member.controller;

import com.study.yesmarket.member.dto.MemberDto;
import com.study.yesmarket.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> join(@Valid @RequestBody MemberDto.JoinRequest joinRequest) {
        memberService.join(joinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/nicknames/{nickname}/duplicate")
    public ResponseEntity<Boolean> isDuplicateNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(memberService.isDuplicateNickname(nickname));
    }

    @GetMapping("/ids/{id}/duplicate")
    public ResponseEntity<Boolean> isDuplicateId(@PathVariable String id) {
        return ResponseEntity.ok(memberService.isDuplicateId(id));
    }
}
