package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.GioHangChiTiet;

import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {

	Long doanhThu(LocalDate from, LocalDate to);

	Long soDonHuy(LocalDate from, LocalDate to);

	Long soSanPhamHoanTra(LocalDate from, LocalDate to);

	List<GioHangChiTiet> getAllSoSanPhamHoanTraHoanLaiKho(LocalDate from, LocalDate to);

	List<Object[]> soLuongLoaiHoaDon(LocalDate from, LocalDate to);

	List<Object[]> hoaDonChiTiet(LocalDate from, LocalDate to);

	List<Object[]> thongKeDoanhThu(LocalDate from, LocalDate to);

}
