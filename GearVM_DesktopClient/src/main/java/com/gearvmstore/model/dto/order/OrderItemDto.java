package com.gearvmstore.model.dto.order;

import lombok.Data;

@Data
public class OrderItemDto {
    private double price;
    private int quantity;
    private Long productId;
}
