package com.gearvmstore.GearVM.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Customer{
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
	@Column(columnDefinition = "date", name = "data_of_birth")
	private Date dateOfBirth;
	@Column(columnDefinition = "nvarchar(100)")
	private String address;
	@Column(columnDefinition = "nchar(20)")
	private String email;
	@Column(columnDefinition = "nchar(20)")
	private String password;
	private String salt;
	private boolean isCart;
	
	@OneToMany(mappedBy = "customerId")
	@ToString.Exclude
	private List<Order> orderList;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
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
