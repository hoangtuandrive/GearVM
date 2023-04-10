package com.gearvmstore.GearVM.model.dto.user;

import com.gearvmstore.GearVM.model.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String password;
}
