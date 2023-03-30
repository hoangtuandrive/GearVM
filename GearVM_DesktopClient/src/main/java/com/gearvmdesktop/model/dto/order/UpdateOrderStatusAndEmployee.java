package com.gearvmdesktop.model.dto.order;


import com.gearvmdesktop.model.OrderStatus;
import com.gearvmdesktop.model.response.EmployeeResponseModel;
import lombok.Data;

@Data
public class UpdateOrderStatusAndEmployee {
    private OrderStatus orderStatus;
    private EmployeeResponseModel employee;
}
