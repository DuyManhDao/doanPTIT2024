package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.ThuongHieu;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.ThuongHieuRequest;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieu> getList();

    List<ThuongHieu> getByTrangThai(TrangThai trangThai);

    ThuongHieu save(ThuongHieuRequest request);

    ThuongHieu update(ThuongHieuRequest request);

    void remove(Long id);

    ThuongHieu findById(Long id);

    boolean existByMa(String ma);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);

    Integer transferPage(Integer pageNo);
}
