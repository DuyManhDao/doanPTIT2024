package com.ptit.d20cntt.datn.security.admin;

import com.ptit.d20cntt.datn.entity.KhachHang;
import com.ptit.d20cntt.datn.entity.NhanVien;
import com.ptit.d20cntt.datn.repository.KhachHangRepository;
import com.ptit.d20cntt.datn.repository.NhanVienRepository;
import com.ptit.d20cntt.datn.security.shop.CustomKhachHangDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomNhanVienDetailService implements UserDetailsService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;
//tìm xem người dùng có tài khoản hay không, tự động chuyển đến đường trang với tài khoản tương ứng
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<NhanVien> nhanVien = nhanVienRepository.findByEmail(username);
        Optional<KhachHang> khachHang = khachHangRepository.findByEmailAndIdNot(username, 1L);

        if (khachHang.isPresent()) {
            return new CustomKhachHangDetail(khachHang.get());
        } else if (nhanVien.isPresent()) {
            return new CustomNhanVienDetail(nhanVien.get());
        } else {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + username);
        }
    }
}
