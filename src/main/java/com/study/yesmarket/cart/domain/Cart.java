package com.study.yesmarket.cart.domain;

import com.study.yesmarket.member.domain.Member;

import javax.persistence.*;

@Entity
public class Cart {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int cartId;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
