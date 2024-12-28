package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.MauSac;
import com.ptit.d20cntt.datn.request.MauSacRequest;
import com.ptit.d20cntt.datn.responsitory.MauSacRepository;
import com.ptit.d20cntt.datn.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAllMauSac() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac add(MauSacRequest mauSacRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        MauSac mauSac = new MauSac();
        mauSac.setMa(mauSacRequest.getMa());
        mauSac.setNgaySua(currentDateTime);
        mauSac.setNgayTao(currentDateTime);
        mauSac.setTen(mauSacRequest.getTen());

        MauSac mauSacAddLater = mauSacRepository.save(mauSac);
        String maMS = "MS" +  String.format("%03d", mauSacAddLater.getId());
        mauSacAddLater.setMa(maMS);
        return mauSacRepository.save(mauSacAddLater);
    }

    @Override
    public String delete(Long id) {
        String note = "";
        try {
            mauSacRepository.deleteById(id);
            note = "Thành Công!";
        } catch (DataIntegrityViolationException e) {
            note = "Cannot delete MauSac with id " + id + " because it is referenced by others table";
            throw new RuntimeException("Cannot delete MauSac with id " + id + " because it is referenced by HoaDon");
        }
        return note;
    }

    @Override
    public MauSac getOne(Long id) {
        Optional<MauSac> mauSac = this.mauSacRepository.findById(id);
        return mauSac.get();
    }

    @Override
    public MauSac findById(Long id) {
        Optional<MauSac> mauSac = mauSacRepository.findById(id);
        if (mauSac.isPresent()) {
            return mauSac.get();
        }
        return null;
    }

    @Override
    public MauSac update(MauSac mauSac) {
        mauSac.setNgayTao(this.mauSacRepository.findById(mauSac.getId()).get().getNgayTao());
        LocalDateTime currentDateTime = LocalDateTime.now();
        mauSac.setNgaySua(currentDateTime);
        return this.mauSacRepository.save(mauSac);
    }


    // danh sách các màu sắc
    @Override
    public List<MauSac> findByProductAndSize(Long productId, Long size) {
        List<MauSac> mauSacs = mauSacRepository.findByProductIdAndSize(productId, size);
        return mauSacs;
    }


}
