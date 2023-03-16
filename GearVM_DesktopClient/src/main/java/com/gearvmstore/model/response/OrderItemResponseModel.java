package com.gearvmstore.model.response;

import lombok.Data;

@Data
public class OrderItemResponseModel {
    private Long id;
    private ProductResponseModel productId;
    private int quantity;
    private double price;
}
