package com.gearvmstore.GearVM.model.response;

import lombok.Data;

@Data
public class ProductResponseModel {
    private Long id;
    private String name;
    private String imageUri;
    private String type;
}
