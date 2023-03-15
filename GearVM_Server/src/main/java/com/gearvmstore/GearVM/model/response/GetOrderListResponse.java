package com.gearvmstore.GearVM.model.response;

import com.gearvmstore.GearVM.model.Discount;
import com.gearvmstore.GearVM.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
