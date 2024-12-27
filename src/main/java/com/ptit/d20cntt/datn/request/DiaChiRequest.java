package com.ptit.d20cntt.datn.request;


import com.ptit.d20cntt.datn.enumation.TrangThai;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiaChiRequest {

    private Long id;

    private String tenNguoiNhan;

    private String tinhThanhPho;

    private String huyenQuan;

    private String xaPhuong;

    private String diaChi;

    private String sdt;

    private String ghiChu;

    private TrangThai trangThai;

    private Long khachHang;
}