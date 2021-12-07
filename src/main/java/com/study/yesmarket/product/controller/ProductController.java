package com.study.yesmarket.product.controller;

import com.study.yesmarket.common.interceptor.ForLoginMember;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegisterRequest;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductRequest;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductResponse;
import com.study.yesmarket.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @ForLoginMember
    @PostMapping
    public ResponseEntity<Void> registerProduct(@Valid @RequestBody RegisterRequest registerRequest) {
        productService.registerProduct(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable int productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @ForLoginMember
    @PutMapping("/{productId}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable int productId
            , @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        return ResponseEntity.ok(productService.updateProduct(productId, updateProductRequest));
    }

    @ForLoginMember
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
