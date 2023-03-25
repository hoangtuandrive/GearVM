package com.gearvmstore.GearVM.model.dto.order;

import com.gearvmstore.GearVM.model.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatus {
    private OrderStatus orderStatus;
}
