package com.gearvmdesktop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity(name = "orderTbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "shipping_detail_id")
    private ShippingDetail shippingDetail;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdDate;
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedDate;
    @Column(columnDefinition = "double")
    private double totalPrice;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @JsonIgnore
    private List<OrderItem> orderItems;

    public Order() {
    }
}
