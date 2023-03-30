package com.gearvmdesktop.model.dto.user;


import com.gearvmdesktop.model.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String password;
}
