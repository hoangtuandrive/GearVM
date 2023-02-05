package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;
    @Column(columnDefinition = "nvarchar(100)")
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Column(columnDefinition = "nchar(10)", name = "phone_number")
    private String phoneNumber;
    private double salary;
    @Column(columnDefinition = "nchar(20)", name = "national_id")
    private String nationalId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", name = "date_of_birth")
    private Date dateOfBirth;
    @Column(columnDefinition = "nvarchar(100)")
    private String address;
    @Column(columnDefinition = "nchar(20)")
    private String email;
    private boolean workStatus;
    @Column(columnDefinition = "LONGTEXT")
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public Employee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
