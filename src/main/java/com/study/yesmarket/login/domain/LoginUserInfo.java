package com.study.yesmarket.login.domain;

import com.study.yesmarket.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LoginUserInfo extends BaseEntity {

    @Id
    @Column(name = "memberId")
    private String id;

    private String password;

    @Builder
    public LoginUserInfo(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
