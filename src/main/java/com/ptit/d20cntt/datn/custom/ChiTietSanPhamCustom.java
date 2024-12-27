package com.ptit.d20cntt.datn.custom;

import com.ptit.d20cntt.datn.entity.AnhBia;
import com.ptit.d20cntt.datn.entity.KichThuoc;
import com.ptit.d20cntt.datn.entity.MauSac;
import com.ptit.d20cntt.datn.entity.SanPham;

import java.math.BigDecimal;
import java.util.List;

public interface ChiTietSanPhamCustom {

    Long getId();

    SanPham getSanPham();

    MauSac getMauSac();

    KichThuoc getKichThuoc();

    Integer getSoLuongTon();

    BigDecimal getGiaBan();

    Integer getTrangThai();
    List<AnhBia> listAnh();

    String getAnhChinh();

}
