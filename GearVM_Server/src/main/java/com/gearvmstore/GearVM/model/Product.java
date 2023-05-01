package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@ToString
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(columnDefinition = "nvarchar(100)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String brand;
    @Column(columnDefinition = "nvarchar(255)")
    private String type;
    @Column(columnDefinition = "nvarchar(255)")
    private String imageUri;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private double price;
    private int quantity;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @ToString.Exclude
    private List<OrderItem> orderItems;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @ToString.Exclude
    private List<Purchase> purchases;

    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
