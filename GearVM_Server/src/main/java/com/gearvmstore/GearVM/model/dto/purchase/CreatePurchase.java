package com.gearvmstore.GearVM.model.dto.purchase;

import lombok.Data;

@Data
public class CreatePurchase {
    private Long productId;
    private Long employeeId;
    private double price;
    private int quantity;
}
