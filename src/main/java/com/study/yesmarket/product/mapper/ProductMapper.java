package com.study.yesmarket.product.mapper;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegistRequest;
import com.study.yesmarket.product.dto.ProductDto.RegistResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productId", ignore = true)
    Product registRequestToEntity(RegistRequest registRequest);

    RegistResponse productToRegistResponse(Product product);

    GetProductResponse productToGetProductResponse(Product product);
}
