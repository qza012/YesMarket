package com.study.yesmarket.order.controller;

import com.study.yesmarket.common.interceptor.ForLoginMember;
import com.study.yesmarket.order.controller.dto.OrderDto.RegisterRequest;
import com.study.yesmarket.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @ForLoginMember
    @PostMapping("/{memberId}")
    public ResponseEntity<Void> registerOrder(@PathVariable String memberId, @Valid @RequestBody RegisterRequest registerRequest) {
        orderService.registerOrder(memberId, registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ForLoginMember
    @DeleteMapping("/{memberId}/products/{productId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String memberId, @PathVariable int productId) {
        orderService.deleteOrder(memberId, productId);
        return ResponseEntity.noContent().build();
    }
}
