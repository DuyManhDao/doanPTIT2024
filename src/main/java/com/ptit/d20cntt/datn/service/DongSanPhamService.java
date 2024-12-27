package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.DongSanPham;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.DongSanPhamRequest;

import java.util.List;

public interface DongSanPhamService {
	List<DongSanPham> getList();

	List<DongSanPham> getByTrangThai(TrangThai trangThai);

	DongSanPham save(DongSanPhamRequest request);

	DongSanPham update(DongSanPhamRequest request);

	void remove(Long id);

	DongSanPham findById(Long id);

	boolean existByMa(String ma);

	boolean existsByTen(String ten);

	boolean existsByTenAndIdNot(String ten, Long id);
}
