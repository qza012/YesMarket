package com.study.yesmarket.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.yesmarket.member.dto.MemberDto;
import com.study.yesmarket.member.dto.MemberDto.DuplicateIdResponse;
import com.study.yesmarket.member.dto.MemberDto.DuplicateNicknameResponse;
import com.study.yesmarket.member.dto.MemberDto.JoinRequest;
import com.study.yesmarket.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @WebMvcTest : 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있게 해주는 어노테이션.
 *               JPA 생성과 관련된 기능은 없기 때문에, application에 @EnableJpaAuditing이 붙으면 에러가 뜬다.
 *               이 때, @MockBean(JpaMetamodelMappingContext.class)를 사용해서 mock으로 만들어주면 해결.
 */

@ActiveProfiles("test")
@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {

    @MockBean
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private JoinRequest memberJoinRequest;

    @BeforeEach
    public void createMember() {
        memberJoinRequest = JoinRequest.builder()
                .id("testId")
                .password("testPw")
                .phoneNumber("010-1234-5678")
                .email("test123@naver.com")
                .nickname("testNick")
                .build();
    }

    @DisplayName("회원 가입에 성공한다.")
    @Test
    public void joinMember() throws Exception{
        // when
        ResultActions perform = mockMvc.perform(post("/members")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(memberJoinRequest)));

        // then
        perform.andExpect(status().isCreated());

        verify(memberService, times(1)).join(any(JoinRequest.class));
    }

    @DisplayName("닉네임 중복 확인에 성공한다.")
    @Test
    public void duplicateNickname() throws Exception {
        // given
        DuplicateNicknameResponse responseDto = DuplicateNicknameResponse.builder()
                .duplicate(true)
                .build();

        given(memberService.isDuplicateNickname(any(String.class))).willReturn(responseDto);

        // when
        ResultActions perform = mockMvc.perform(get("/members/nicknames/{nickname}/duplicate", memberJoinRequest.getNickname())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        perform.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseDto)));

        verify(memberService, times(1)).isDuplicateNickname(any(String.class));
    }

    @DisplayName("아이디 중복 확인에 성공한다.")
    @Test
    public void duplicateId() throws Exception {
        // given
        DuplicateIdResponse responseDto = DuplicateIdResponse.builder()
                .duplicate(true)
                .build();

        given(memberService.isDuplicateId(any(String.class))).willReturn(responseDto);

        // when
        ResultActions perform = mockMvc.perform(get("/members/ids/{id}/duplicate", memberJoinRequest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        perform.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseDto)));

        verify(memberService, times(1)).isDuplicateId(any(String.class));
    }
}