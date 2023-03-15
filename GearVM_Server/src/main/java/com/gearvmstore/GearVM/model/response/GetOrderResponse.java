package com.gearvmstore.GearVM.model.response;

import com.gearvmstore.GearVM.model.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetOrderResponse {
    private Long id;
    private EmployeeResponseModel employeeId;
    private CustomerResponseModel customerId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private double totalPrice;
    private OrderStatus orderStatus;
    private List<OrderItemResponseModel> orderItems;
    private Discount discount;
}
