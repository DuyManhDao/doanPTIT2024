package com.ptit.d20cntt.datn.service.impl;

import com.ptit.d20cntt.datn.entity.DiaChi;
import com.ptit.d20cntt.datn.entity.KhachHang;
import com.ptit.d20cntt.datn.enumation.GioiTinh;
import com.ptit.d20cntt.datn.request.KhachHangRequest;
import com.ptit.d20cntt.datn.request.RegisterRequest;
import com.ptit.d20cntt.datn.responsitory.DiaChiRepository;
import com.ptit.d20cntt.datn.responsitory.KhachHangRepository;
import com.ptit.d20cntt.datn.sendmail.EmailService;
import com.ptit.d20cntt.datn.service.KhachHangService;
import com.ptit.d20cntt.datn.worker.AutoGenCodeRandom;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    DiaChiRepository diaChiRepository;
    @Autowired
    private EmailService emailService;


    @Override
    public List<KhachHang> getList() {
        return khachHangRepository.getListKhachHang();
    }

    @Override
    public boolean existsBySdt(String sdt) {
        return khachHangRepository.existsBySdt(sdt);
    }

    @Override
    public boolean existsByEmail(String email) {
        return khachHangRepository.existsByEmail(email);
    }

    @Override
    public boolean existsBySdtAndIdNot(String sdt, Long id) {
        return khachHangRepository.existsBySdtAndIdNot(sdt, id);
    }

    //đăng kí khách hàng gửi mật khẩu random về mail
    @Override
    public KhachHang registration(RegisterRequest request) {

        KhachHang khachHang = khachHangRepository.save(RegisterRequest.convertToEntity(request));
        String matKhau = AutoGenCodeRandom.genUUID();
        khachHang.setMatKhau(matKhau);
        khachHangRepository.save(khachHang);

        emailService.sendNewAccountKHEmail(khachHang.getEmail(), khachHang.getEmail(), matKhau);

        return khachHang;
    }

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang add(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        khachHang.setTen(khachHangRequest.getTen());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setGioiTinh(GioiTinh.valueOf(khachHangRequest.getGioiTinh()));
        khachHang.setSdt(khachHangRequest.getSdt());
        String matKhau = UUID.randomUUID().toString().substring(0, 10);
        khachHang.setMatKhau(matKhau);
        khachHang.setTrangThai((byte) 1);
        khachHang.setTichDiem(BigDecimal.ZERO);
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        khachHang.setNgayTao(LocalDate.now());
        KhachHang khachHangMa = khachHangRepository.save(khachHang);
        String ma = "KH" + khachHangMa.getId().toString();
        khachHangMa.setMa(ma);

        return khachHangRepository.save(khachHang);
    }

    @Override
    public String delete(Long id) {
        Optional<KhachHang> khachHangOptional = khachHangRepository.findById(id);
        if (khachHangOptional.isPresent()) {
            khachHangRepository.deleteById(id);
            return "Thành Công!";
        } else {
            return "Không thể xóa khách hàng với id = " + id + ". Không tìm thấy khách hàng.";
        }
    }

    @Override
    public boolean checkSdtDuplicate(String sdt) {
        return khachHangRepository.existsKhachHangBySdt(sdt);
    }

    @Override
    public boolean checkEmailDuplicate(String email) {
        return khachHangRepository.existsKhachHangByEmail(email);
    }

    @Override
    public KhachHang getById(Long id) {
        Optional<KhachHang> optional = khachHangRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public KhachHang getOne(Long id) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(id);
        return khachHang.orElse(null);
    }

    @Override
    public KhachHang update(KhachHangRequest khachHangRequest) {
        if (khachHangRequest == null) {
            return null;
        }
        KhachHang existingKhachHang = khachHangRepository.getReferenceById(khachHangRequest.getId());
        if (existingKhachHang == null) {
            return null;
        }
        existingKhachHang.setMa(khachHangRequest.getMa());
        existingKhachHang.setTen(khachHangRequest.getTen());
        existingKhachHang.setSdt(khachHangRequest.getSdt());
        existingKhachHang.setGioiTinh(GioiTinh.valueOf(khachHangRequest.getGioiTinh()));
        existingKhachHang.setNgaySua(LocalDate.now());
        existingKhachHang.setEmail(khachHangRequest.getEmail());
        existingKhachHang.setTrangThai(khachHangRequest.getTrangThai());
        try {
            khachHangRepository.save(existingKhachHang);
            return existingKhachHang;
        } catch (Exception e) {
            return null;
        }
    }

    //view địa chỉ của từng khách hàng
    @Override
    public List<DiaChi> getDiaChiByIdKhachHang(Long idKhachHang) {
        List<DiaChi> list = new ArrayList<>();
        for (DiaChi dc : diaChiRepository.findAll()) {
            if (dc.getKhachHang().getId() == idKhachHang) {
                list.add(dc);
            }
        }
        return list;

    }

    @Override
    public DiaChi getByIdDiaChi(Long idDiaChi) {
        return diaChiRepository.findById(idDiaChi).orElse(null);

    }

    //đổi mật khẩu
    @Override
    public boolean changeUserPassword(Long idKh, String oldPassword, String newPassword) {
        KhachHang khachHang = khachHangRepository.findById(idKh).orElse(null);

        khachHang.setMatKhau(newPassword);
        khachHangRepository.save(khachHang);
        return true;

    }

    //quên mật khẩu
    public boolean forgotpassword(String email) {

        KhachHang khachHang = khachHangRepository.findByEmail(email).orElse(null);
        if (khachHang != null) {

            String newPassword = generateRandomPassword(10);

            // Cập nhật mật khẩu mới vào cơ sở dữ liệu
            khachHang.setMatKhau(newPassword); // Lưu mật khẩu không mã hóa


            khachHangRepository.save(khachHang);

            // Gửi email với mật khẩu mới
            emailService.sendPasswordEmail(email, newPassword);

            return true;
        }
        return false;
    }


    private String generateRandomPassword(int length) {
        return RandomStringUtils.random(length, true, true); // Sinh chuỗi ngẫu nhiên bao gồm chữ cái và số
    }
}



