package com.gearvmdesktop.model.response;

import com.gearvmdesktop.model.Discount;
import com.gearvmdesktop.model.OrderStatus;
import com.gearvmdesktop.model.Payment;
import com.gearvmdesktop.model.ShippingDetail;
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
}
