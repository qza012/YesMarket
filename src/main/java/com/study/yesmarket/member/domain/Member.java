package com.study.yesmarket.member.domain;


import com.study.yesmarket.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "member")
@Entity
public class Member extends BaseEntity {

    @Id
    private String member_id;

    private String password;

    private String phone_number;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

}