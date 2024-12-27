package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.AnhBia;
import com.ptit.d20cntt.datn.entity.SanPham;

import java.util.List;

public interface AnhBiaService {
	AnhBia save(AnhBia anhSanPham);

	AnhBia update(AnhBia anhSanPham);

	List<AnhBia> getAnh(SanPham sanPham);

	void deleteByIdSp(Long id);
}
