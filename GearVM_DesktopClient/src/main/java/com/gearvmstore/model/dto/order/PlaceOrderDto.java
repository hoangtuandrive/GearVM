package com.gearvmstore.model.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderDto {
    private double totalPrice;
    private List<OrderItemDto> orderItems;
}
