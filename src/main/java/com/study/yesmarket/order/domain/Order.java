package com.study.yesmarket.order.domain;

import com.study.yesmarket.common.domain.BaseEntity;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ORDERS_ID")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    private Integer productCount;

    @Builder
    public Order(Integer orderId, Member member, Product product, int productCount) {
        this.orderId = orderId;
        this.member = member;
        this.product = product;
        this.productCount = productCount;
    }
}
