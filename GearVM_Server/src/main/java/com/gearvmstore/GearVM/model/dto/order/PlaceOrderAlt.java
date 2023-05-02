package com.gearvmstore.GearVM.model.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderAlt {
    private double totalPrice;
    private ShippingDetailDto shippingDetailDto;
    private List<OrderItemDto> orderItemDtos;
    private String code;
}
