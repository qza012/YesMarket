package com.study.yesmarket.product.mapper;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegisterRequest;
import com.study.yesmarket.product.dto.ProductDto.RegisterResponse;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/***
 * componentModel = "spring" : mapper를 bean으로 등록하기 위함.
 */

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "productId", ignore = true)
    Product registerRequestToEntity(RegisterRequest registerRequest);

    RegisterResponse productToRegisterResponse(Product product);

    GetProductResponse productToGetProductResponse(Product product);

    UpdateProductResponse productToUpdateProductResponse(Product product);
}
