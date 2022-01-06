package com.study.yesmarket.cart.domain;

import com.study.yesmarket.common.domain.BaseEntity;
import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer cartId;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToMany
    @JoinTable(name = "CART_PRODUCT")
    private Set<Product> products = new HashSet<>();

    @Builder
    public Cart(int cartId) {
        this.cartId = cartId;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
