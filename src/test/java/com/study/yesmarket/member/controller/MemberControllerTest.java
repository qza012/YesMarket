package com.study.yesmarket.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @WebMvcTest : 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 라이브러리
 *
 */

@WebMvcTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

}