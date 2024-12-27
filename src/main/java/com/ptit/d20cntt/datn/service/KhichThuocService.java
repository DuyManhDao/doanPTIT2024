package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.KichThuoc;
import com.ptit.d20cntt.datn.request.KichThuocRequest;

import java.util.List;

public interface KhichThuocService {
	List<KichThuoc> getAllKichThuoc();

	KichThuoc add(KichThuocRequest kichThuocRequest);

	String delete(Long id);

	KichThuoc getOne(Long id);

	KichThuoc findById(Long id);

	KichThuoc update(KichThuoc kichThuoc);

	List<KichThuoc> findByProductId(Long productId);
}
