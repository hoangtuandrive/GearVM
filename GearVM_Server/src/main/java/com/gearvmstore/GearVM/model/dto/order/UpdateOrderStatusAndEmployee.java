package com.gearvmstore.GearVM.model.dto.order;

import com.gearvmstore.GearVM.model.OrderStatus;
import com.gearvmstore.GearVM.model.response.EmployeeResponseModel;
import lombok.Data;

@Data
public class UpdateOrderStatusAndEmployee {
    private OrderStatus orderStatus;
    private EmployeeResponseModel employee;
}
