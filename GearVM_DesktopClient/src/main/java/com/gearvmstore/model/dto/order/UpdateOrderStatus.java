package com.gearvmstore.model.dto.order;

import com.gearvmstore.model.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatus {
    private OrderStatus orderStatus;
}
