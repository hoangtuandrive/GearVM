package com.gearvmstore.GearVM.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String phoneNumber;
    private String password;
}
