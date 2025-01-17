package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.*;
import com.ptit.d20cntt.datn.entity.LoaiVai;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.ChiTietSanPhamRequest;
import com.ptit.d20cntt.datn.repository.ChiTietSanPhamRepository;
import com.ptit.d20cntt.datn.repository.LoaiVaiRepository;
import com.ptit.d20cntt.datn.repository.KichThuocRepository;
import com.ptit.d20cntt.datn.repository.MauSacRepository;
import com.ptit.d20cntt.datn.repository.SanPhamRepository;
import com.ptit.d20cntt.datn.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    private final ChiTietSanPhamRepository repository;
    private final LoaiVaiRepository loaiVaiRepository;
    private final KichThuocRepository kichThuocRepository;
    private final MauSacRepository mauSacRepository;
    private final SanPhamRepository sanPhamRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    public ChiTietSanPhamServiceImpl(ChiTietSanPhamRepository repository, LoaiVaiRepository loaiVaiRepository, KichThuocRepository kichThuocRepository, MauSacRepository mauSacRepository, SanPhamRepository sanPhamRepository) {
        this.repository = repository;
        this.loaiVaiRepository = loaiVaiRepository;
        this.kichThuocRepository = kichThuocRepository;
        this.mauSacRepository = mauSacRepository;
        this.sanPhamRepository = sanPhamRepository;
    }




//chi tiết sản phẩm view
    @Override
    public List<ChiTietSanPham> getAll() {
        List<ChiTietSanPham> sortedList =chiTietSanPhamRepository.findAll().stream()
                .sorted(Comparator.comparing(ChiTietSanPham::getId).reversed())
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public ChiTietSanPham getById(Long id) {
        Optional<ChiTietSanPham> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }


    @Override
    public List<ChiTietSanPham> getAllChiTietSanPham() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public ChiTietSanPham add(ChiTietSanPhamRequest chiTietSanPhamRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setGiaBan(chiTietSanPhamRequest.getGiaBan());
        chiTietSanPham.setGiaMacDinh(chiTietSanPhamRequest.getGiaMacDinh());
        chiTietSanPham.setSoLuongTon(chiTietSanPhamRequest.getSoLuongTon());
        SanPham sanPham = sanPhamRepository.findById(chiTietSanPhamRequest.getSanPham()).orElse(null);
        chiTietSanPham.setSanPham(sanPham);
        LoaiVai loaiVai = loaiVaiRepository.findById(chiTietSanPhamRequest.getLoaiVai()).orElse(null);
        chiTietSanPham.setLoaiVai(loaiVai);
        KichThuoc kichThuoc = kichThuocRepository.findById(chiTietSanPhamRequest.getKichThuoc()).orElse(null);
        chiTietSanPham.setKichThuoc(kichThuoc);
        MauSac mauSac = mauSacRepository.findById(chiTietSanPhamRequest.getMauSac()).orElse(null);
        chiTietSanPham.setMauSac(mauSac);
        chiTietSanPham.setNgaySua(currentDateTime);
        chiTietSanPham.setNgayTao(LocalDate.now());

        chiTietSanPham.setTrangThai(TrangThai.DANG_HOAT_DONG);
        return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            chiTietSanPhamRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete ChiTietSanPham with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete San Pham with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public ChiTietSanPham getOne(Long id) {
        Optional<ChiTietSanPham> chiTietSanPham = this.chiTietSanPhamRepository.findById(id);
        return chiTietSanPham.get();
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham chiTietSanPham) {
        chiTietSanPham.setNgayTao(this.chiTietSanPhamRepository.findById(chiTietSanPham.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        chiTietSanPham.setNgaySua(currentDateTime);
        return this.chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void thayDoiTrangThai(Long id) {
//
//        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamRepository.findById(id).get();
//
//        if (chiTietSanPham.getTrangThai() == 0) {
//            chiTietSanPham.setTrangThai(1);
//        } else {
//            chiTietSanPham.setTrangThai(0);
//        }
//        this.chiTietSanPhamRepository.save(chiTietSanPham);


    }

    @Override
    public ChiTietSanPham findByColorAndSize(Long colorId, Long sizeId, Long productId) {

        return chiTietSanPhamRepository.findByMauSacAndKichThuoc(colorId, sizeId, productId).get(0);
    }


}
