package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.NhaCungCap;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.NhaCungCapRequest;

import java.util.List;

public interface NhaCungCapService {
    List<NhaCungCap> getList();

    List<NhaCungCap> getByTrangThai(TrangThai trangThai);

    NhaCungCap save(NhaCungCapRequest request);

    NhaCungCap update(NhaCungCapRequest request);

    void remove(Long id);

    NhaCungCap findById(Long id);


    boolean existByMa(String ma);

    boolean existsByTen(String ten);


    boolean existsByTenAndIdNot(String ten, Long id);


}
