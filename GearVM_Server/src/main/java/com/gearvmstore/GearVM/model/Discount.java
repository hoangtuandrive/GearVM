package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;
    private int percentageDiscount;
    private double flatDiscount;

    private LocalDateTime expirationDate;
    private boolean isUsed;

    private String code;
    @OneToOne(mappedBy = "discount")
    @JsonIgnore
    private Order order;

    public Discount() {
    }
    public Discount(int percentageDiscount, double flatDiscount, LocalDateTime expirationDate, boolean isUsed, String code) {
        this.percentageDiscount = percentageDiscount;
        this.flatDiscount = flatDiscount;
        this.expirationDate = expirationDate;
        this.isUsed = isUsed;
        this.code = code;
    }


}
