package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.KichThuoc;
import com.ptit.d20cntt.datn.request.KichThuocRequest;
import com.ptit.d20cntt.datn.repository.KichThuocRepository;
import com.ptit.d20cntt.datn.service.KhichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KichThuocServiceImpl implements KhichThuocService {
    @Autowired
    private KichThuocRepository kichThuocRepository;

    @Override
    public List<KichThuoc> getAllKichThuoc() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc add(KichThuocRequest kichThuocRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setMa(kichThuocRequest.getMa());
        kichThuoc.setNgaySua(currentDateTime);
        kichThuoc.setNgayTao(currentDateTime);
        kichThuoc.setTen(kichThuocRequest.getTen());

        // Check if a KichThuoc with the same 'ma' already exists
        Optional<KichThuoc> existingKichThuoc = kichThuocRepository.findByMa(kichThuocRequest.getMa());
        if (existingKichThuoc.isPresent()) {
            throw new DataIntegrityViolationException("Duplicate entry for key 'kich_thuoc.UK_qtq2dqyq8ut9lu7xir4c01y0u'");
        }

        KichThuoc kichThuocAddLater = kichThuocRepository.save(kichThuoc);
        String maKT = "KT" + String.format("%03d", kichThuocAddLater.getId());
        kichThuocAddLater.setMa(maKT);

        return kichThuocRepository.save(kichThuocAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            kichThuocRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete KichThuoc with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete KichThuoc with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public KichThuoc getOne(Long id) {
        Optional<KichThuoc> kichThuoc = this.kichThuocRepository.findById(id);
        return kichThuoc.get();
    }

    @Override
    public KichThuoc findById(Long id) {
        Optional<KichThuoc> kichThuoc = kichThuocRepository.findById(id);
        if (kichThuoc.isPresent()) {
            return kichThuoc.get();
        }
        return null;
    }

    @Override
    public KichThuoc update(KichThuoc kichThuoc) {
        kichThuoc.setNgayTao(this.kichThuocRepository.findById(kichThuoc.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        kichThuoc.setNgaySua(currentDateTime);
        return this.kichThuocRepository.save(kichThuoc);
    }

    @Override
    public List<KichThuoc> findByProductId(Long productId) {

        //        lấy toàn bộ kích thước
        List<KichThuoc> kichThuocs=  kichThuocRepository.findByProductId(productId);

        return kichThuocs;
    }
}
