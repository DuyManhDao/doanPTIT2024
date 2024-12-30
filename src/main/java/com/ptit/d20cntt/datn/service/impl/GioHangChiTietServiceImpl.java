package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.GioHang;
import com.ptit.d20cntt.datn.entity.GioHangChiTiet;
import com.ptit.d20cntt.datn.repository.GioHangChiTietRepository;
import com.ptit.d20cntt.datn.repository.GioHangRepository;
import com.ptit.d20cntt.datn.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public List<GioHangChiTiet> getAll(Long idKhachHang) {
        GioHang gioHang = gioHangRepository.getByKhachHangId(idKhachHang);
        if(gioHang != null) {
            return gioHangChiTietRepository.findAllByGioHang(gioHang.getId());
        } else return null;
    }

    @Override
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public List<GioHangChiTiet> findGioHangChiTietById(Long id) {
        return gioHangChiTietRepository.findByHoaDon(id);
    }

    @Override
    public Optional<GioHangChiTiet> getOne(Long id) {

        return gioHangChiTietRepository.findById(id);
    }

    @Override
    public GioHangChiTiet save(GioHangChiTiet gioHangChiTiet) {

        return gioHangChiTietRepository.save(gioHangChiTiet);
    }
}
