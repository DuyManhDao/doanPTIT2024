package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.custom.SanPhamCustom;
import com.ptit.d20cntt.datn.entity.SanPham;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.SanPhamRequest;

import java.util.List;

public interface SanPhamService {

    List<SanPham> getList();

    List<SanPhamCustom> getAll();

    List<SanPhamCustom> getByTrangThai(TrangThai trangThai);

    SanPham save(SanPhamRequest request);

    SanPham update(SanPhamRequest request);

    SanPham findById(Long id);

    boolean existByMa(String ma);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);

    String delete(Long id);

}
