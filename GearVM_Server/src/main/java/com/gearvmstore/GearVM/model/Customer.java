package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(columnDefinition = "nvarchar(100)")
    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Column(columnDefinition = "nchar(10)", name = "phone_number")
    private String phoneNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(columnDefinition = "nchar(50)")
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String password;
    private boolean isCart;

    private String resetPasswordToken;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    @ToString.Exclude
    private List<Order> orderList;

    public Customer() {
    }
}
