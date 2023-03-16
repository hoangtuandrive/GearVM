package com.gearvmstore.model.response;

import lombok.Data;

@Data
public class CustomerResponseModel {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
}
