package com.gearvmstore.GearVM.model.dto.order;

import lombok.Data;

@Data
public class ShippingDetailDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private double shippingCost;
}
