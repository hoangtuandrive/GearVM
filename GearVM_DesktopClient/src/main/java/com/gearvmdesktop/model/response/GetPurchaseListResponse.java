package com.gearvmdesktop.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetPurchaseListResponse {
    private Long id;
    private double price;
    private int quantity;
    private LocalDateTime createdDate;
    private ProductResponseModel productResponseModel;
    private EmployeeResponseModel employeeResponseModel;
}
