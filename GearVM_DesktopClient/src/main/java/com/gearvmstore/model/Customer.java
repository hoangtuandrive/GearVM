package com.gearvmstore.model;

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
    @Column(columnDefinition = "nvarchar(100)")
    private String address;
    @Column(columnDefinition = "nchar(50)")
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String password;
    private boolean isCart;

    @OneToMany(mappedBy = "customerId")
    @JsonIgnore
    @ToString.Exclude
    private List<Order> orderList;

    public Customer() {
    }
//
//
//	public Customer(String maKH, String tenKH, boolean gioiTinh, String sDT, String cMND, Date ngaySinh, String diaChi,
//					String email) {
//		super();
//		this.maKH = maKH;
//		this.tenKH = tenKH;
//		this.gioiTinh = gioiTinh;
//		SDT = sDT;
//		CMND = cMND;
//		this.ngaySinh = ngaySinh;
//		this.diaChi = diaChi;
//		this.email = email;
//	}
//
//	public Customer(String maKH, String tenKH, boolean gioiTinh, String sDT, String cMND, Date ngaySinh, String diaChi,
//					String email, boolean gioHang) {
//		this.maKH = maKH;
//		this.tenKH = tenKH;
//		this.gioiTinh = gioiTinh;
//		SDT = sDT;
//		CMND = cMND;
//		this.ngaySinh = ngaySinh;
//		this.diaChi = diaChi;
//		this.email = email;
//		this.gioHang = gioHang;
//	}
//
//	public Customer(String maKH, String tenKH , String sDT) {
//		this.maKH = maKH;
//		this.tenKH = tenKH;
//		SDT = sDT;
//	}
//
//	public Customer(String maKH, String sDT) {
//		super();
//		this.maKH = maKH;
//		SDT = sDT;
//	}
//
//	public Customer(String maKH) {
//		super();
//		this.maKH = maKH;
//	}
}
