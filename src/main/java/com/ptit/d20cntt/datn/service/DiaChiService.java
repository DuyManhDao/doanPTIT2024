package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.DiaChi;
import com.ptit.d20cntt.datn.request.DiaChiRequest;

import java.util.List;

public interface DiaChiService {
	List<DiaChi> getAll();

	List<DiaChi> getAllTheoKhachHang(Long id);

	DiaChi getById(Long id);

	DiaChi add(DiaChiRequest diaChiRequest, Long idKhachHang, String thanhPho, String quanHuyen, String phuongXa);

	DiaChi update(DiaChiRequest diaChiRequest, String thanhPho, String quanHuyen, String phuongXa);

	void remove(Long id);
}
