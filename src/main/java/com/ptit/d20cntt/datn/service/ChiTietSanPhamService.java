package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.ChiTietSanPham;
import com.ptit.d20cntt.datn.request.ChiTietSanPhamRequest;

import java.util.List;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAll();

    ChiTietSanPham getById(Long id);

    List<ChiTietSanPham> getAllChiTietSanPham();

    ChiTietSanPham add(ChiTietSanPhamRequest chiTietSanPhamRequest);

    String delete(Long id);

    ChiTietSanPham getOne(Long id);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void thayDoiTrangThai(Long id);

    ChiTietSanPham findByColorAndSize(Long colorId, Long sizeId, Long productId);

}
