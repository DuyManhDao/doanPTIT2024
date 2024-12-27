package com.ptit.d20cntt.datn.custom;

import java.math.BigDecimal;


public interface ChiTietSanPhamCustomerCt {

    Long getId();

    String getTenSp();

    String getTenLoaiVai();

    String getTenDongSp();

    BigDecimal getGia_mac_dinh();

    BigDecimal getGia_ban();

    String getAnh_chinh();

    String getTenKich_thuoc();

    String getTenMau_sac();


}
