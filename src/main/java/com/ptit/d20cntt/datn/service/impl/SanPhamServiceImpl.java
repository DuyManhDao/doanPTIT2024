package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.custom.SanPhamCustom;
import com.ptit.d20cntt.datn.entity.DongSanPham;
import com.ptit.d20cntt.datn.entity.NhaCungCap;
import com.ptit.d20cntt.datn.entity.SanPham;
import com.ptit.d20cntt.datn.entity.ThuongHieu;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.SanPhamRequest;
import com.ptit.d20cntt.datn.responsitory.DongSanPhamRepository;
import com.ptit.d20cntt.datn.responsitory.NhaCungCapRepository;
import com.ptit.d20cntt.datn.responsitory.SanPhamRepository;
import com.ptit.d20cntt.datn.responsitory.ThuongHieuRepository;
import com.ptit.d20cntt.datn.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    private final SanPhamRepository repository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final ThuongHieuRepository thuongHieuRepository;
    private final DongSanPhamRepository dongSanPhamRepository;

    public SanPhamServiceImpl(SanPhamRepository repository, DongSanPhamRepository dongSanPhamRepository, NhaCungCapRepository nhaCungCapRepository, ThuongHieuRepository thuongHieuRepository, DongSanPhamRepository dongSanPhamRepository1) {
        this.repository = repository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.thuongHieuRepository = thuongHieuRepository;
        this.dongSanPhamRepository = dongSanPhamRepository;
    }

    @Autowired
    private SanPhamRepository sanPhamRepository;


    @Override
    public List<SanPham> getList() {
        return repository.findAll();
    }


    @Override
    public List<SanPhamCustom> getAll() {
        return repository.getPageSanPhamCusTom();
    }

    @Override
    public List<SanPhamCustom> getByTrangThai(TrangThai trangThai) {
        return sanPhamRepository.getAllByTrangThai(trangThai);
    }


    @Override
    public SanPham save(SanPhamRequest request) {
        SanPham sanPham = new SanPham();

        sanPham.setMa(request.getMa());
        sanPham.setTen(request.getTen());
        sanPham.setMoTa(request.getMoTa());
        sanPham.setListAnhSanPham(request.getListAnh());
        sanPham.setAnhChinh(request.getAnhChinh());
        NhaCungCap nhaCungCap = nhaCungCapRepository.findById(request.getNhaCungCap()).orElse(null);
        sanPham.setNhaCungCap(nhaCungCap);
        ThuongHieu thuongHieu = thuongHieuRepository.findById(request.getThuongHieu()).orElse(null);
        sanPham.setThuongHieu(thuongHieu);
        DongSanPham dongSanPham = dongSanPhamRepository.findById(request.getDongSanPham()).orElse(null);
        sanPham.setDongSanPham(dongSanPham);

        sanPham.setNgayTao(LocalDate.now());
        sanPham.setNgaySua(LocalDate.now());
        sanPham.setTrangThai(TrangThai.DANG_HOAT_DONG);
        return repository.save(sanPham);
    }


    @Override
    public SanPham update(SanPhamRequest request) {
        SanPham sanPham = repository.findById(request.getId()).orElse(null);
        if (sanPham != null) {
            sanPham.setMa(request.getMa());
            sanPham.setTen(request.getTen());
            sanPham.setMoTa(request.getMoTa());
            sanPham.setListAnhSanPham(request.getListAnh());
            sanPham.setAnhChinh(request.getAnhChinh());
            NhaCungCap nhaCungCap = nhaCungCapRepository.findById(request.getNhaCungCap()).orElse(null);
            sanPham.setNhaCungCap(nhaCungCap);
            ThuongHieu thuongHieu = thuongHieuRepository.findById(request.getThuongHieu()).orElse(null);
            sanPham.setThuongHieu(thuongHieu);
            DongSanPham dongSanPham = dongSanPhamRepository.findById(request.getDongSanPham()).orElse(null);
            sanPham.setDongSanPham(dongSanPham);
            sanPham.setNgaySua(LocalDate.now());
            sanPham.setTrangThai(request.getTrangThai());
            return repository.save(sanPham);
        }
        return null;
    }




    @Override
    public SanPham findById(Long id) {
        Optional<SanPham> sanPham = repository.findById(id);
        if (sanPham.isPresent()) {
            return sanPham.get();
        }
        return null;
    }

    @Override
    public boolean existByMa(String ma) {
        return repository.existsByMa(ma);
    }

    @Override
    public boolean existsByTen(String ten) {
        return repository.existsByTen(ten);
    }

    @Override
    public boolean existsByTenAndIdNot(String ten, Long id) {
        return repository.existsByTenAndIdNot(ten, id);

    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            sanPhamRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete SanPham with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete San Pham with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

}
