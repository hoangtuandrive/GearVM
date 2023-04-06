package com.gearvmstore.GearVM.model.response;

import lombok.Data;

@Data
public class GetPendingDirectOrderListResponse {
    private Long id;
    private String customerName;
    private String customerPhoneNumber;
}
