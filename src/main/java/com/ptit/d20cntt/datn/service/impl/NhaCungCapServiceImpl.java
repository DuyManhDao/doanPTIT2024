package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.NhaCungCap;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.NhaCungCapRequest;
import com.ptit.d20cntt.datn.repository.NhaCungCapRepository;
import com.ptit.d20cntt.datn.service.NhaCungCapService;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class NhaCungCapServiceImpl implements NhaCungCapService {
    private final NhaCungCapRepository repository;

    public NhaCungCapServiceImpl(NhaCungCapRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<NhaCungCap> getList() {
        return repository.findAll();
    }

    @Override
    public List<NhaCungCap> getByTrangThai(TrangThai trangThai) {
        return repository.getAllByTrangThai(trangThai);
    }

    @Override
    public NhaCungCap save(NhaCungCapRequest request) {
    NhaCungCap nhacungcap = new NhaCungCap();
        nhacungcap.setMa(request.getMa());
        nhacungcap.setTen(request.getTen());
        nhacungcap.setNgayTao(LocalDate.now());
        nhacungcap.setNgaySua(LocalDate.now());
        nhacungcap.setTrangThai(TrangThai.DANG_HOAT_DONG);
    NhaCungCap nhaCungCaplater = repository.save(nhacungcap);
        String mancc = "NCC" +  String.format("%03d", nhaCungCaplater.getId());
        nhaCungCaplater.setMa(mancc);
        return repository.save(nhaCungCaplater);
    }

    @Override
    public NhaCungCap update(NhaCungCapRequest request) {
        NhaCungCap nhaCungCap = repository.findById(request.getId()).orElse(null);
        if (nhaCungCap != null) {
            nhaCungCap.setMa(request.getMa());
            nhaCungCap.setTen(request.getTen());
            nhaCungCap.setNgaySua(LocalDate.now());
            nhaCungCap.setTrangThai(request.getTrangThai());
            return repository.save(nhaCungCap);
        }
        return null;
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public NhaCungCap findById(Long id) {
        Optional<NhaCungCap> nhaCungCap = repository.findById(id);
        if (nhaCungCap.isPresent()) {
            return nhaCungCap.get();
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


}
