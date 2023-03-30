package com.gearvmdesktop.model.response;


import com.gearvmdesktop.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetOrderListResponse {
    private Long id;
    private EmployeeResponseModel employee;
    private CustomerResponseModel customer;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;
    private double totalPrice;
    private OrderStatus orderStatus;
}
