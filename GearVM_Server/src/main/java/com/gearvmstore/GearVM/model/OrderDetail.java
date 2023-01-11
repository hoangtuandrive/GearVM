package com.gearvmstore.GearVM.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Setter
@ToString
@Entity
@IdClass(OrderDetailPK.class)
public class OrderDetail{
	@Id
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Order orderId;
	@Id
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product productId;
	private int quantity;
	@Column(columnDefinition = "double")
	private double price;
//	@Column(columnDefinition = "money")
//	private double thanhTien;

	public OrderDetail() {
		super();
	}

//	public ChiTietHoaDon(HoaDon maHoaDon, SanPham maSP, int soLuong, double giaTien) {
//		super();
//		this.maHoaDon = maHoaDon;
//		this.maSP = maSP;
//		this.soLuong = soLuong;
//		this.giaTien = giaTien;
//		this.thanhTien = giaTien * soLuong;
//	}
//
//	public ChiTietHoaDon(HoaDon maHoaDon, SanPham maSP, int soLuong, double giaTien, double thanhTien) {
//		super();
//		this.maHoaDon = maHoaDon;
//		this.maSP = maSP;
//		this.soLuong = soLuong;
//		this.giaTien = giaTien;
//		this.thanhTien = giaTien * soLuong;
//	}
//	public ChiTietHoaDon(SanPham maSP, int soLuong) {
//		this.maSP = maSP;
//		this.soLuong = soLuong;
//	}


}
