package com.study.yesmarket.order.mapper;

import com.study.yesmarket.order.domain.Order;
import com.study.yesmarket.order.dto.OrderDto.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "productId", expression="java(order.getProduct().getProductId())")
    @Mapping(target = "memberId", expression="java(order.getMember().getMemberId())")
    RegisterResponse orderToRegisterResponse(Order order);
}
