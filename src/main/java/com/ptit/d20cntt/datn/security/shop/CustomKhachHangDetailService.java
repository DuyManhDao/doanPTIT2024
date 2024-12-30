package com.ptit.d20cntt.datn.security.shop;

import com.ptit.d20cntt.datn.entity.KhachHang;
import com.ptit.d20cntt.datn.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//tìm kiếm khách hàng theo email (username) và chuyển đổi đối tượng KhachHang thành đối tượng UserDetails
// Spring Security có thể sử dụng để thực hiện xác thực và phân quyền.

@Service
public class CustomKhachHangDetailService implements UserDetailsService {

	@Autowired
	private KhachHangRepository khachHangRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		KhachHang khachHang = khachHangRepository.findByEmailAndIdNot(username, 1L).orElseThrow(() -> new UsernameNotFoundException("Invalid"));
		return new CustomKhachHangDetail(khachHang);
	}
}
