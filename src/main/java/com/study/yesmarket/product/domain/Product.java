package com.study.yesmarket.product.domain;

import com.study.yesmarket.common.domain.BaseEntity;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product extends BaseEntity {

    /***
     * @GeneratedValue : 값을 자동으로 생성해줌.
     * 생성 전략
     * 1. AUTO (default): 특정 db에 맞게 자동으로 선택
     * 2. IDENTITY : db의 identity 컬럼을 사용 (MySql)
     * 3. SEQUENCE : db의 시퀀스 컬럼을 사용 (Oracle)
     * 4. TABLE : 키를 저장해두는 테이블 사용
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int productId;

    private String name;

    private int price;

    private int stock;

    @Builder
    public Product(int productId, String name, int price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void update(UpdateProductRequest updateProductRequest) {
        this.name = updateProductRequest.getName();
        this.price = updateProductRequest.getPrice();
        this.stock = updateProductRequest.getStock();
    }
}