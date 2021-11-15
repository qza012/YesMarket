package com.study.yesmarket.product.domain;

import com.study.yesmarket.common.domain.BaseEntity;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product extends BaseEntity {

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