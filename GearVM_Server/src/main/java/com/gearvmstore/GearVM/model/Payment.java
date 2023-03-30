package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;
    private String paymentDescription;
    private String paymentLink;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @OneToOne(mappedBy = "payment")
    @JsonIgnore
    private Order order;

    public Payment() {
    }
}
