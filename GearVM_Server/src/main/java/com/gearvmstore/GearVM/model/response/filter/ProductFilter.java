package com.gearvmstore.GearVM.model.response.filter;

import lombok.Data;

@Data
public class ProductFilter {
    private Long id;
    private String name;
    private String brand;
    private String type;
    private String imageUri;
    private String description;
    private double price;
    private int quantity;
}
