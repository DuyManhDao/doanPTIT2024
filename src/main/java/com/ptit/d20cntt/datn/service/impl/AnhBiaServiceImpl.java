package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.AnhBia;
import com.ptit.d20cntt.datn.entity.SanPham;
import com.ptit.d20cntt.datn.repository.AnhBiaRepository;
import com.ptit.d20cntt.datn.service.AnhBiaService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnhBiaServiceImpl implements AnhBiaService {
	//view add, update delete ảnh service
	private final AnhBiaRepository repository;

	public AnhBiaServiceImpl(AnhBiaRepository repository) {
		this.repository = repository;
	}

	@Override
	public AnhBia save(AnhBia anhSanPham) {
		return repository.save(anhSanPham);
	}

	@Override
	public AnhBia update(AnhBia anhSanPham) {
		AnhBia anhSanPham1 = repository.findById(anhSanPham.getId()).orElse(null);
		if (anhSanPham1 != null) {
			anhSanPham.setUrl(anhSanPham1.getUrl());
			return repository.save(anhSanPham);
		}
		return null;
	}

	@Override
	public List<AnhBia> getAnh(SanPham sanPham) {
		{
			return repository.findBySanPham(sanPham);
		}
	}

	@Transactional
	@Override
	public void deleteByIdSp(Long id) {
		List<AnhBia> anhSanPhams = repository.findBySanPhamId(id);
		repository.deleteInBatch(anhSanPhams);
	}
}
