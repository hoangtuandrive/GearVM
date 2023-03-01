package com.gearvmstore.GearVM.model.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderDTO {
    private double totalPrice;
    private List<OrderItemDto> orderItems;
}
