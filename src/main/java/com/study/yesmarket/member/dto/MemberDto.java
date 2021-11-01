package com.study.yesmarket.member.dto;

import com.study.yesmarket.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class MemberDto {

    @Getter
    public static class JoinRequest {

        @NotBlank(message = "아이디를 입력해주세요")
        private String id;

        @NotBlank(message = "비밀번호를 입력해주세요")
        private String password;

        @NotBlank(message = "핸드폰 번호를 입력해주세요.")
        private String phoneNumber;

        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "닉네임을 입력해주세요.")
        private String nickname;

        @Builder
        public JoinRequest(String id, String password, String phoneNumber, String email, String nickname) {
            this.id = id;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.nickname = nickname;
        }

        public Member toEntity() {
            return Member.builder()
                    .memberId(this.id)
                    .password(this.password)
                    .phoneNumber(this.phoneNumber)
                    .email(this.email)
                    .nickname(this.nickname)
                    .build();
        }
    }
}
