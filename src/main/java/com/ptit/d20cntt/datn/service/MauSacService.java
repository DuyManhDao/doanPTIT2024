package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.MauSac;
import com.ptit.d20cntt.datn.request.MauSacRequest;

import java.util.List;

public interface MauSacService {
	List<MauSac> getAllMauSac();

	MauSac add(MauSacRequest mauSacRequest);

	String delete(Long id);

	MauSac getOne(Long id);

	MauSac findById(Long id);

	MauSac update(MauSac mauSac);

	List<MauSac> findByProductAndSize(Long productId, Long size);
}
