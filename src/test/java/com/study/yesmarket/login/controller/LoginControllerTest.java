package com.study.yesmarket.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.yesmarket.login.dto.LoginDto.LoginRequest;
import com.study.yesmarket.login.service.LoginService;
import com.study.yesmarket.login.service.SessionLoginService;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest({
        LoginController.class,
        SessionLoginService.class
})
@MockBean(JpaMetamodelMappingContext.class)
class LoginControllerTest {

    @MockBean
    private LoginService loginService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("아이디와 비밀번호가 일치하면, 로그인에 성공한다.")
    @Test
    void login() throws Exception{
        // given
        LoginRequest loginRequest = LoginRequest.builder()
                .id("test123")
                .password("password123")
                .build();

        // when
        ResultActions perform = mockMvc.perform(post("/members/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(loginRequest)));

        // then
        perform.andExpect(status().isOk());

        verify(loginService, times(1)).login(loginRequest);

    }

    @DisplayName("로그아웃에 성공한다.")
    @Test
    void logout() throws Exception{
        // when
        ResultActions perform = mockMvc.perform(delete("/members/logout")
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        perform.andExpect(status().isOk());

        verify(loginService, times(1)).logout();
    }
}