package com.gearvmstore.model.response;

import com.gearvmstore.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetOrderListResponse {
    private Long id;
    private EmployeeResponseModel employeeId;
    private CustomerResponseModel customerId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;
    private double totalPrice;
    private OrderStatus orderStatus;
}
