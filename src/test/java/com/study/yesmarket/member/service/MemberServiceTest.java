package com.study.yesmarket.member.service;

import com.study.yesmarket.common.service.encript.EncryptService;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.dto.MemberDto;
import com.study.yesmarket.member.dto.MemberDto.DuplicateIdResponse;
import com.study.yesmarket.member.dto.MemberDto.DuplicateNicknameResponse;
import com.study.yesmarket.member.exception.DuplicateIdException;
import com.study.yesmarket.member.exception.DuplicateNicknameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private EncryptService encryptService;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private MemberDto.JoinRequest memberJoinRequest;

    @BeforeEach
    public void createMember() {
        memberJoinRequest = MemberDto.JoinRequest.builder()
                .id("testId")
                .password("testPw")
                .phoneNumber("010-1234-5678")
                .email("test123@naver.com")
                .nickname("testNick")
                .build();
    }

    @Test
    @DisplayName("필수 사항이 모두 입력됐다면, 회원가입에 성공한다.")
    public void joinMember() {
        // when
        memberService.join(memberJoinRequest);

        // then
        verify(encryptService, times(1)).encrypt(any(String.class));
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    @DisplayName("중복된 아이디인지 확인에 성공한다.")
    public void duplicateId() {
        // given
        given(memberRepository.existsById(any(String.class))).willReturn(true);

        // when
        DuplicateIdResponse actual = memberService.isDuplicateId(memberJoinRequest.getId());

        // then
        assertThat(actual.isDuplicate()).isEqualTo(true);

        verify(memberRepository, times(1)).existsById(any(String.class));
    }

    @Test
    @DisplayName("회원가입 시, 아이디가 중복됐다면 아이디 중복 예외가 발생한다.")
    public void joinMember_With_Duplicate_Id() {
        // given
        given(memberRepository.existsById(any(String.class))).willReturn(true);

        // then
        assertThrows(DuplicateIdException.class, () -> memberService.join(memberJoinRequest));
    }

    @Test
    @DisplayName("중복된 닉네임인지 확인에 성공한다.")
    public void duplicateNickName() {
        // given
        given(memberRepository.existsByNickname(any(String.class))).willReturn(true);

        // when
        DuplicateNicknameResponse actual = memberService.isDuplicateNickname(memberJoinRequest.getNickname());

        // then
        assertThat(actual.isDuplicate()).isEqualTo(true);

        verify(memberRepository, times(1)).existsByNickname(any(String.class));
    }

    @Test
    @DisplayName("회원가입 시, 닉에임이 중복됐다면 닉네임 중복 예외가 발생한다.")
    public void joinMember_With_Duplicate_Nickname() {
        // given
        given(memberRepository.existsByNickname(any(String.class))).willReturn(true);

        // then
        assertThrows(DuplicateNicknameException.class, () -> memberService.join(memberJoinRequest));
    }
}
