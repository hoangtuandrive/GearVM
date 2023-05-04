package com.gearvmstore.GearVM.model.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrder {
    private double totalPrice;
    private List<OrderItemDto> orderItemDtos;
    private String code;
}
