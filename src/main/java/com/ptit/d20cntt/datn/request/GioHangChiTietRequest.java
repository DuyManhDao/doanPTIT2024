package com.ptit.d20cntt.datn.request;

import com.ptit.d20cntt.datn.entity.ChiTietSanPham;
import com.ptit.d20cntt.datn.entity.GioHang;
import com.ptit.d20cntt.datn.entity.HoaDon;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
@Getter
@Setter
public class GioHangChiTietRequest {
    private Long id;

    private ChiTietSanPham chiTietSanPham;

    private HoaDon hoaDon;

    private GioHang gioHang;

    private BigDecimal donGia;

    private Integer soLuong;

    private TrangThai trangThai;

    private String ghiChu;

    private MultipartFile hinhAnh; // Change from String to MultipartFile


    // Getters and setters
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public MultipartFile getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(MultipartFile hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}