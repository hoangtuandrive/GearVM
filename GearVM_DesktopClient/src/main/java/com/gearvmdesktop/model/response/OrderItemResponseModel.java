package com.gearvmdesktop.model.response;

import lombok.Data;

@Data
public class OrderItemResponseModel {
    private Long id;
    private ProductResponseModel product;
    private int quantity;
    private double price;
}
