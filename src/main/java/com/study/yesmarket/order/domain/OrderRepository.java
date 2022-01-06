package com.study.yesmarket.order.domain;

import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    void deleteByMemberAndProduct(Member member, Product product);
}
