package com.gearvmstore.GearVM.model.dto;

import com.gearvmstore.GearVM.model.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterDTO {
    private String name;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String password;
}
