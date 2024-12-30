package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.LoaiVai;
import com.ptit.d20cntt.datn.request.LoaiVaiRequest;
import com.ptit.d20cntt.datn.repository.LoaiVaiRepository;
import com.ptit.d20cntt.datn.service.LoaiVaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoaiVaiServiceImpl implements LoaiVaiService {
    @Autowired
    private LoaiVaiRepository loaiVaiRepository;

    @Override
    public List<LoaiVai> getAllLoaiVai() {
        return loaiVaiRepository.findAll();
    }

    @Override
    public LoaiVai add(LoaiVaiRequest loaiVaiRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LoaiVai loaiVai = new LoaiVai();
        loaiVai.setMa(loaiVaiRequest.getMa());
        loaiVai.setNgaySua(currentDateTime);
        loaiVai.setNgayTao(currentDateTime);
        loaiVai.setTen(loaiVaiRequest.getTen());

        LoaiVai loaiVaiAddLater = loaiVaiRepository.save(loaiVai);
        String maLV = "LV" + String.format("%03d", loaiVaiAddLater.getId());
        loaiVaiAddLater.setMa(maLV);
        return loaiVaiRepository.save(loaiVaiAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            loaiVaiRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete LoaiVai with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete LoaiVai with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public LoaiVai getOne(Long id) {
        Optional<LoaiVai> loaiVai = this.loaiVaiRepository.findById(id);
        return loaiVai.get();
    }

    @Override
    public LoaiVai findById(Long id) {
        Optional<LoaiVai> loaiVai = loaiVaiRepository.findById(id);
        if (loaiVai.isPresent()) {
            return loaiVai.get();
        }
        return null;
    }

    @Override
    public LoaiVai update(LoaiVai loaiVai) {
        loaiVai.setNgayTao(this.loaiVaiRepository.findById(loaiVai.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        loaiVai.setNgaySua(currentDateTime);
        return this.loaiVaiRepository.save(loaiVai);
    }
}
