package com.gearvmstore.model;

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
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdDate;
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedDate;
    @Column(columnDefinition = "double")
    private double totalPrice;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderId")
    @ToString.Exclude
    @JsonIgnore
    private List<OrderItem> orderItems;
    @OneToOne(mappedBy = "orderId")
    private Discount discount;

    public Order() {
        super();
    }


//	public double tinhTongTien() {
//		double tongTien = 0;
//		for (OrderDetail ct : cthd) {
//			tongTien += ct.getThanhTien();
//		}
//		return tongTien;
//	}

//	public Order(String id, Employee maNV, Customer maKH, Date ngayLapHoaDon) {
//		this.id = id;
//		this.maNV = maNV;
//		this.maKH = maKH;
//		this.ngayLapHoaDon = ngayLapHoaDon;
//	}
//
//	public Order(String id, Employee maNV, Customer maKH, Date ngayLapHoaDon, List<OrderDetail> orderDetail) {
//		this.id = id;
//		this.maNV = maNV;
//		this.maKH = maKH;
//		this.ngayLapHoaDon = ngayLapHoaDon;
//		this.cthd = orderDetail;
//		this.tongTien = tinhTongTien();
//	}

//	public Order(String id) {
//		this.id = id;
//	}
//
//	public Order(String id, Customer maKH) {
//		this.id = id;
//		this.maKH = maKH;
//	}
//	public Order(String id, Date ngayLapHoaDon, double tongTien) {
//		super();
//		this.id = id;
//		this.ngayLapHoaDon = ngayLapHoaDon;
//		this.tongTien = tongTien;
//	}
}
