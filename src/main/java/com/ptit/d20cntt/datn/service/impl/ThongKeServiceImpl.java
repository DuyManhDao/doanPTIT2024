package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.GioHangChiTiet;
import com.ptit.d20cntt.datn.repository.GioHangChiTietRepository;
import com.ptit.d20cntt.datn.repository.HoaDonChiTietRepo;
import com.ptit.d20cntt.datn.repository.HoaDonRepo;
import com.ptit.d20cntt.datn.service.ThongKeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ThongKeServiceImpl implements ThongKeService {
    private final GioHangChiTietRepository gioHangChiTietRepository;
    private final HoaDonRepo hoaDonRepo;
    private final HoaDonChiTietRepo hoaDonChiTietRepo;

    public ThongKeServiceImpl(GioHangChiTietRepository gioHangChiTietRepository, HoaDonRepo hoaDonRepo, HoaDonChiTietRepo hoaDonChiTietRepo) {
        this.gioHangChiTietRepository = gioHangChiTietRepository;
        this.hoaDonRepo = hoaDonRepo;
        this.hoaDonChiTietRepo = hoaDonChiTietRepo;
    }

    @Override
    public Long doanhThu(LocalDate from, LocalDate to) {
        return hoaDonRepo.doanhThu(from,to);
    }

    @Override
    public Long soDonHuy(LocalDate from, LocalDate to) {
        return hoaDonRepo.soDonHuy( from,  to);
    }

    @Override
    public Long soSanPhamHoanTra(LocalDate from, LocalDate to) {
        return gioHangChiTietRepository.sanPhamHoanTra(from,to);
    }

    @Override
    public List<GioHangChiTiet> getAllSoSanPhamHoanTraHoanLaiKho(LocalDate from, LocalDate to) {
        return gioHangChiTietRepository.findByHoaDonHoanTraHoanLaiKho(from,to);
    }




    @Override
    public List<Object[]> soLuongLoaiHoaDon(LocalDate from, LocalDate to) {
        return hoaDonRepo.thongKeHoaDon(from,to);
    }

    @Override
    public List<Object[]> hoaDonChiTiet(LocalDate from, LocalDate to) {
        List<Object[]> hoaDonChiTietResults = hoaDonChiTietRepo.countHoaDonChiTiet( from,  to);
        List<Object[]> gioHangChiTietResults = gioHangChiTietRepository.countAllGhct( from,  to);

        List<Object[]> list = new ArrayList<>();
        list.addAll(hoaDonChiTietResults);
        list.addAll(gioHangChiTietResults);

        return list;
    }

    @Override
    public List<Object[]> thongKeDoanhThu(LocalDate from, LocalDate to) {
        return hoaDonRepo.thongKeDoanhTHu( from,  to);
    }

}
