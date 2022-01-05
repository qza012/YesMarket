package com.study.yesmarket.order.controller;

import com.study.yesmarket.common.interceptor.ForLoginMember;
import com.study.yesmarket.order.controller.dto.OrderDto.AddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    @ForLoginMember
    @PostMapping("/{memberId}")
    public ResponseEntity<Void> addOrder(@PathVariable String memberId, @Valid @RequestBody AddRequest addRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ForLoginMember
    @DeleteMapping("/{memberId}/products/{productId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String memberId, @PathVariable int productId) {

        return ResponseEntity.noContent().build();
    }
}
