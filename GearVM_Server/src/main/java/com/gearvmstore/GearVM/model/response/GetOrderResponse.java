package com.gearvmstore.GearVM.model.response;

import com.gearvmstore.GearVM.model.Discount;
import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.Payment;
import com.gearvmstore.GearVM.model.ShippingDetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetOrderResponse {
    private Long id;
    private Payment payment;
    private EmployeeResponseModel employee;
    private CustomerResponseModel customer;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private double totalPrice;
    private OrderStatus orderStatus;
    private List<OrderItemResponseModel> orderItems;
    private Discount discount;
    private ShippingDetail shippingDetail;
    private boolean isDirect;
}
