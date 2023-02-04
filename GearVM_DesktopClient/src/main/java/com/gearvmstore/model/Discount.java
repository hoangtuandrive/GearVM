package com.gearvmstore.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    private boolean isUsed;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productId;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    public Discount() {
    }
}
