package com.ptit.d20cntt.datn.custom;

import com.ptit.d20cntt.datn.entity.AnhBia;
import com.ptit.d20cntt.datn.entity.DongSanPham;
import com.ptit.d20cntt.datn.entity.NhaCungCap;
import com.ptit.d20cntt.datn.entity.ThuongHieu;

import java.util.List;

public interface SanPhamCustom {
    Long getId();

    String getMa();

    String getTen();

    String getMoTa();

    Integer getTrangThai();

    ThuongHieu getThuongHieu();

    NhaCungCap getNhaCungCap();
   DongSanPham getDongSanPham();

    List<AnhBia> listAnh();

    String getAnhChinh();

}
