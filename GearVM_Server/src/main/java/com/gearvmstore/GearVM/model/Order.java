package com.gearvmstore.GearVM.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gearvmstore.GearVM.model.dto.order.PrintOrderDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import java.time.LocalDateTime;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NamedNativeQuery(
        name = "Order.findPrintOrderByOrderId_Named",
        query = "SELECT order_item.id AS chiTietHoaDon_MaChiTietHD, " +
                "order_item.order_id AS chiTietHoaDon_MaHD, " +
                "order_item.product_id AS chiTietHoaDon_IDSanPham, " +
                "order_item.quantity AS chiTietHoaDon_SoLuong, " +
                "order_item.quantity * order_item.price AS chiTietHoaDon_ThanhTien, " +
                "order_tbl.order_id AS hoaDon_MaHD, " +
                "order_tbl.created_date AS hoaDon_NgayTaoHD, " +
                "order_tbl.total_price AS hoaDon_TongTien, " +
                "customer.customer_id AS khachHang_MaKH, " +
                "customer.name AS khachHang_TenKhachHang, " +
                "customer.phone_number AS khachHang_SoDienThoai, " +
                "employee.employee_id AS nhanVien_MaNhanVien, " +
                "employee.name AS nhanVien_TenNhanVien, " +
                "product.product_id AS sanPham_IDSanPham, " +
                "product.name AS sanPham_TenSP, " +
                "product.price AS sanPham_GiaBan, " +
                "product.brand AS sanPham_Loai " +
                "FROM order_tbl " +
                "INNER JOIN order_item ON order_tbl.order_id = order_item.order_id " +
                "INNER JOIN customer ON order_tbl.customer_id = customer.customer_id " +
                "INNER JOIN employee ON order_tbl.employee_id = employee.employee_id " +
                "INNER JOIN product ON order_item.product_id = product.product_id " +
                "WHERE order_tbl.order_id = :orderId",
        resultSetMapping = "PrintOrderDtoMapping"
)
@SqlResultSetMapping(name = "PrintOrderDtoMapping", classes = {
        @ConstructorResult(targetClass = PrintOrderDto.class, columns = {
                @ColumnResult(name = "chiTietHoaDon_MaChiTietHD", type = Long.class),
                @ColumnResult(name = "chiTietHoaDon_MaHD", type = Long.class),
                @ColumnResult(name = "chiTietHoaDon_IDSanPham", type = Long.class),
                @ColumnResult(name = "chiTietHoaDon_SoLuong", type = Integer.class),
                @ColumnResult(name = "chiTietHoaDon_ThanhTien", type = Double.class),
                @ColumnResult(name = "hoaDon_MaHD", type = Long.class),
                @ColumnResult(name = "hoaDon_NgayTaoHD", type = Date.class),
                @ColumnResult(name = "hoaDon_TongTien", type = Double.class),
                @ColumnResult(name = "khachHang_MaKH", type = Long.class),
                @ColumnResult(name = "khachHang_TenKhachHang", type = String.class),
                @ColumnResult(name = "khachHang_SoDienThoai", type = String.class),
                @ColumnResult(name = "nhanVien_MaNhanVien", type = Long.class),
                @ColumnResult(name = "nhanVien_TenNhanVien", type = String.class),
                @ColumnResult(name = "sanPham_IDSanPham", type = Long.class),
                @ColumnResult(name = "sanPham_TenSP", type = String.class),
                @ColumnResult(name = "sanPham_GiaBan", type = Double.class),
                @ColumnResult(name = "sanPham_Loai", type = String.class)
        })
})
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
    @ColumnDefault("false")
    private boolean isDirect;
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @JsonIgnore
    private List<OrderItem> orderItems;

    public Order() {
    }

}
