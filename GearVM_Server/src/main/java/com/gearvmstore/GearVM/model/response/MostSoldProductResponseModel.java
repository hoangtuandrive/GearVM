package com.gearvmstore.GearVM.model.response;

import lombok.Data;

@Data
public class MostSoldProductResponseModel {
    private Long productId;
    private String productName;
    private String productImageUri;
    private double productPrice;
    private Long totalSold;

    public MostSoldProductResponseModel() {
    }

//    public MostSoldProductResponseModel(Long productId, String productName, Long totalSold) {
//        this.productName = productName;
//        this.totalSold = totalSold;
//    }

    public MostSoldProductResponseModel(Long productId, String productName, String productImageUri, double productPrice, Long totalSold) {
        this.productId = productId;
        this.productName = productName;
        this.productImageUri = productImageUri;
        this.productPrice = productPrice;
        this.totalSold = totalSold;
    }
}
