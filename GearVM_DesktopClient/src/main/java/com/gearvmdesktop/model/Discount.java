package com.gearvmdesktop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne(mappedBy = "discount")
    @JsonIgnore
    private Order order;

    public Discount() {
    }
}
