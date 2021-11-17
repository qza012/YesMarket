package com.study.yesmarket.product.service;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.domain.ProductRepository;
import com.study.yesmarket.product.dto.ProductDto.*;
import com.study.yesmarket.product.exception.NotFindProductException;
import com.study.yesmarket.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Transactional
    public RegisterResponse registerProduct(RegisterRequest registerRequest) {
        Product request = productMapper.registerRequestToEntity(registerRequest);

        Product product = productRepository.save(request);

        return productMapper.productToRegisterResponse(product);
    }

    @Transactional(readOnly = true)
    public GetProductResponse getProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFindProductException::new);

        return productMapper.productToGetProductResponse(product);
    }

    @Transactional
    public UpdateProductResponse updateProduct(int productId, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFindProductException::new);

        product.update(updateProductRequest);

        return productMapper.productToUpdateProductResponse(product);
    }

    @Transactional
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

}
