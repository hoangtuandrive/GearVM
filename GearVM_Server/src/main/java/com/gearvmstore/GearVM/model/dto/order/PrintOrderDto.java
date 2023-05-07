package com.gearvmstore.GearVM.model.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gearvmstore.GearVM.model.response.OrderItemResponseModel;
import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})


@Data
@Setter
@NoArgsConstructor
public class PrintOrderDto {
   private Long  chiTietHoaDon_MaChiTietHD;
    private Long chiTietHoaDon_MaHD;
    private Long chiTietHoaDon_IDSanPham;
    private  int chiTietHoaDon_SoLuong;
    private double chiTietHoaDon_ThanhTien;
    private Long hoaDon_MaHD;
    private Date hoaDon_NgayTaoHD;
    private double hoaDon_TongTien;
    private Long khachHang_MaKH;
    private String khachHang_TenKhachHang;
    private String khachHang_SoDienThoai;
    private Long nhanVien_MaNhanVien;
    private String nhanVien_TenNhanVien;
    private  Long sanPham_IDSanPham;
    private String sanPham_TenSP;
    private double sanPham_GiaBan;
    private String sanPham_Loai;

    public PrintOrderDto(Long chiTietHoaDon_MaChiTietHD, Long chiTietHoaDon_MaHD, Long chiTietHoaDon_IDSanPham, int chiTietHoaDon_SoLuong, double chiTietHoaDon_ThanhTien, Long hoaDon_MaHD, Date hoaDon_NgayTaoHD, double hoaDon_TongTien, Long khachHang_MaKH, String khachHang_TenKhachHang, String khachHang_SoDienThoai, Long nhanVien_MaNhanVien, String nhanVien_TenNhanVien, Long sanPham_IDSanPham, String sanPham_TenSP, double sanPham_GiaBan, String sanPham_Loai) {
        this.chiTietHoaDon_MaChiTietHD = chiTietHoaDon_MaChiTietHD;
        this.chiTietHoaDon_MaHD = chiTietHoaDon_MaHD;
        this.chiTietHoaDon_IDSanPham = chiTietHoaDon_IDSanPham;
        this.chiTietHoaDon_SoLuong = chiTietHoaDon_SoLuong;
        this.chiTietHoaDon_ThanhTien = chiTietHoaDon_ThanhTien;
        this.hoaDon_MaHD = hoaDon_MaHD;
        this.hoaDon_NgayTaoHD = hoaDon_NgayTaoHD;
        this.hoaDon_TongTien = hoaDon_TongTien;
        this.khachHang_MaKH = khachHang_MaKH;
        this.khachHang_TenKhachHang = khachHang_TenKhachHang;
        this.khachHang_SoDienThoai = khachHang_SoDienThoai;
        this.nhanVien_MaNhanVien = nhanVien_MaNhanVien;
        this.nhanVien_TenNhanVien = nhanVien_TenNhanVien;
        this.sanPham_IDSanPham = sanPham_IDSanPham;
        this.sanPham_TenSP = sanPham_TenSP;
        this.sanPham_GiaBan = sanPham_GiaBan;
        this.sanPham_Loai = sanPham_Loai;
    }
   
}
