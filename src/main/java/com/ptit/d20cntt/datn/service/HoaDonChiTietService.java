package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService {

    List<HoaDonChiTiet> getCtspById(Long id);
}
