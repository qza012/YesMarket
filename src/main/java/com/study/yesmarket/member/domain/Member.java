package com.study.yesmarket.member.domain;


import com.study.yesmarket.cart.domain.Cart;
import com.study.yesmarket.common.domain.BaseEntity;
import com.study.yesmarket.member.dto.MemberDto.JoinRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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

    @OneToOne(mappedBy = "member")
    private Cart cart;

    @Builder
    public Member(String memberId, String password, String phoneNumber, String email, String nickname) {
        this.memberId = memberId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
    }

    @Builder
    public Member(JoinRequest joinRequest, String encryptedPassword) {
        this.memberId = joinRequest.getId();
        this.password = encryptedPassword;
        this.phoneNumber = joinRequest.getPhoneNumber();
        this.email = joinRequest.getEmail();
        this.nickname = joinRequest.getNickname();
    }
}