package com.study.yesmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @EnableJpaAuditing : entity를 DB에 넣을 때, 수정 일자나 생성 일자를 자동으로 넣어주도록 설정.
 *                      enity에서 해당 필드에 @CreateDate나 @LastModifiedDate를 사용하여 설정한다.
 */

@SpringBootApplication
@EnableJpaAuditing
public class YesMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(YesMarketApplication.class, args);
    }

}
