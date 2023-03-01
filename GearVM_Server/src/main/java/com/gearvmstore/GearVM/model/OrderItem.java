package com.gearvmstore.GearVM.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
    private int quantity;
    @Column(columnDefinition = "double")
    private double price;
//	@Column(columnDefinition = "money")
//	private double thanhTien;

    public OrderItem() {
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
