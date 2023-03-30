package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity(name = "shipping_detail")
public class ShippingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_detail_id")
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    @OneToOne(mappedBy = "shippingDetail")
    @JsonIgnore
    private Order order;

    public ShippingDetail() {
    }
}
