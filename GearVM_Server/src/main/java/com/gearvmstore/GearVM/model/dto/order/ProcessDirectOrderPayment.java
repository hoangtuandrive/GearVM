package com.gearvmstore.GearVM.model.dto.order;

import com.gearvmstore.GearVM.model.PaymentMethod;
import lombok.Data;

@Data
public class ProcessDirectOrderPayment {
    private Long orderId;
    private Long employeeId;
    private String shippingName;
    private String shippingPhone;
    private String shippingAddress;
    private PaymentMethod paymentMethod;
}

