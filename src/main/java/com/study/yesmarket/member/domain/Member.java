package com.study.yesmarket.member.domain;


import com.study.yesmarket.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class Member extends BaseEntity {

    @Id
    private String memberId;

    private String password;

    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    @Builder
    public Member(String memberId, String password, String phoneNumber, String email, String nickname) {
        this.memberId = memberId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
    }
}