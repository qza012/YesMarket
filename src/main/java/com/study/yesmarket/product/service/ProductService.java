package com.study.yesmarket.product.service;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.domain.ProductRepository;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegistRequest;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductRequest;
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
    public void registProduct(RegistRequest registRequest) {
        productRepository.save(productMapper.registRequestToEntity(registRequest));
    }

    @Transactional(readOnly = true)
    public GetProductResponse getProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFindProductException::new);

        return productMapper.productToGetProductResponse(product);
    }

    @Transactional
    public void updateProduct(UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(updateProductRequest.getProductId())
                .orElseThrow(NotFindProductException::new);

        product.update(updateProductRequest);
    }

    @Transactional
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

}
