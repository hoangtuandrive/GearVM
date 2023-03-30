package com.gearvmdesktop.model.response;

import lombok.Data;

@Data
public class ProductResponseModel {
    private Long id;
    private String name;
    private String imageUri;
    private String type;
    private String brand;
    private double price;
    private int quantity;
}
