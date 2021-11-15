package com.study.yesmarket.product.mapper;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegistRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product registRequestToEntity(RegistRequest registRequest);

    GetProductResponse productToGetProductResponse(Product product);
}
