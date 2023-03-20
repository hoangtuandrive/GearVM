package com.gearvmstore.model.dto.order;

import com.gearvmstore.model.OrderStatus;
import com.gearvmstore.model.response.EmployeeResponseModel;
import lombok.Data;

@Data
public class UpdateOrderStatusAndEmployee {
    private OrderStatus orderStatus;
    private EmployeeResponseModel employeeId;
}
