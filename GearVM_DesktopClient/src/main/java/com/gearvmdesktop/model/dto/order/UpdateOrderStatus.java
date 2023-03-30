package com.gearvmdesktop.model.dto.order;


import com.gearvmdesktop.model.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatus {
    private OrderStatus orderStatus;
}
