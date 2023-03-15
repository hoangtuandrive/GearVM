package com.gearvmstore.GearVM.model.response;

import com.gearvmstore.GearVM.model.Product;
import lombok.Data;

@Data
public class OrderItemResponseModel {
    private Long id;
    private ProductResponseModel productId;
    private int quantity;
    private double price;
}
