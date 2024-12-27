package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.DiaChi;
import com.ptit.d20cntt.datn.entity.KhachHang;
import com.ptit.d20cntt.datn.request.KhachHangRequest;
import com.ptit.d20cntt.datn.request.RegisterRequest;

import java.util.List;


public interface KhachHangService {
    //    List<KhachHang> getByTrangThai(TrangThai trangThai);
    List<KhachHang> getList();
    boolean existsBySdt(String sdt);

    boolean existsByEmail(String email);

    boolean existsBySdtAndIdNot(String sdt,Long id);
    KhachHang registration(RegisterRequest khachHang);
    List<KhachHang> getAll();

    KhachHang add(KhachHangRequest khachHangRequest);

    String delete(Long id);

    boolean checkSdtDuplicate(String sdt);


    boolean checkEmailDuplicate(String email);
    KhachHang getById(Long id);

    KhachHang getOne(Long id);

    KhachHang update(KhachHangRequest khachHangRequest);

    List<DiaChi> getDiaChiByIdKhachHang(Long idKhachHang);

    DiaChi getByIdDiaChi(Long idDiaChi);
    boolean changeUserPassword(Long idKh,String oldPassword, String newPassword);
    boolean forgotpassword(String newPassword);
}
