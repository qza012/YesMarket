package com.study.yesmarket.order.service;

import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.member.domain.MemberRepository;
import com.study.yesmarket.member.exception.NotMatchedIdException;
import com.study.yesmarket.order.dto.OrderDto.RegisterRequest;
import com.study.yesmarket.order.domain.Order;
import com.study.yesmarket.order.domain.OrderRepository;
import com.study.yesmarket.order.dto.OrderDto.RegisterResponse;
import com.study.yesmarket.order.mapper.OrderMapper;
import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.domain.ProductRepository;
import com.study.yesmarket.product.exception.NotFindProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public RegisterResponse registerOrder(String memberId, RegisterRequest registerRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotMatchedIdException::new);

        Product product = productRepository.findById(registerRequest.getProductId())
                .orElseThrow(NotFindProductException::new);

        Order order = Order.builder()
                .member(member)
                .product(product)
                .productCount(registerRequest.getProductCount())
                .build();

        Order result = orderRepository.save(order);

        return orderMapper.orderToRegisterResponse(result);
    }

    @Transactional
    public void deleteOrder(String memberId, int productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotMatchedIdException::new);

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFindProductException::new);

        orderRepository.deleteByMemberAndProduct(member, product);
    }
}
