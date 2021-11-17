package com.study.yesmarket.product.mapper;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegisterRequest;
import com.study.yesmarket.product.dto.ProductDto.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productId", ignore = true)
    Product registerRequestToEntity(RegisterRequest registerRequest);

    RegisterResponse productToRegisterResponse(Product product);

    GetProductResponse productToGetProductResponse(Product product);
}
