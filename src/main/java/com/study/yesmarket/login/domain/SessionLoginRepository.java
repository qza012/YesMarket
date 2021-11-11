package com.study.yesmarket.login.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionLoginRepository extends JpaRepository<LoginUserInfo, String> {
}
