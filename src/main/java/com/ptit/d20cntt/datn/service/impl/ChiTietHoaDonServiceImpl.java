package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.HoaDonChiTiet;
import com.ptit.d20cntt.datn.repository.HoaDonChiTietRepo;
import com.ptit.d20cntt.datn.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietHoaDonServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepo hoaDonChiTietRepo;
//hoa don online
    @Override
    public List<HoaDonChiTiet> getCtspById(Long id) {
        return hoaDonChiTietRepo.getHoaDonChiTietByIdHoaDon(id);
    }
}