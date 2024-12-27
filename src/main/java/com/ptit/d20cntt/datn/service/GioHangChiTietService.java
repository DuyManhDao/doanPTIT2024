package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.GioHangChiTiet;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietService {
	List<GioHangChiTiet> getAll();

	List<GioHangChiTiet> getAll(Long idKhachHang);

	List<GioHangChiTiet> findGioHangChiTietById(Long id);

	Optional<GioHangChiTiet> getOne(Long id);

	GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);
}
