package com.gearvmstore.GearVM.model.response;

import lombok.Data;

@Data
public class MostSoldProductResponseModel {
    private String productName;
    private Long totalSold;

    public MostSoldProductResponseModel() {
    }

    public MostSoldProductResponseModel(String productName, Long totalSold) {
        this.productName = productName;
        this.totalSold = totalSold;
    }
}
