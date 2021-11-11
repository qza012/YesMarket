package com.study.yesmarket.login.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @Getter
    @EqualsAndHashCode
    public static class LoginRequest {

        @NotBlank(message = "아이디를 입력해주세요")
        private String id;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @Builder
        public LoginRequest(String id, String password) {
            this.id = id;
            this.password = password;
        }
    }

}
