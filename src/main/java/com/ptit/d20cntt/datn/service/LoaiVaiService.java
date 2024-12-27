package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.LoaiVai;
import com.ptit.d20cntt.datn.request.LoaiVaiRequest;

import java.util.List;

public interface LoaiVaiService {
	List<LoaiVai> getAllLoaiVai();

	LoaiVai add(LoaiVaiRequest loaiVaiRequest);

	String delete(Long id);

	LoaiVai getOne(Long id);

	LoaiVai findById(Long id);

	LoaiVai update(LoaiVai loaiVai);
}
