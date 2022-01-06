package com.study.yesmarket.member.domain;


import com.study.yesmarket.cart.domain.Cart;
import com.study.yesmarket.common.domain.BaseEntity;
import com.study.yesmarket.member.dto.MemberDto.JoinRequest;
import com.study.yesmarket.order.domain.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    // CascadeType.All : 부모가 지워질 때, 자식도 같이 지워져야 함.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Order> orders;

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