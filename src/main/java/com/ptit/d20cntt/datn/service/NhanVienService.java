package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.entity.NhanVien;
import com.ptit.d20cntt.datn.request.NhanVienRequest;

import java.util.List;

public interface NhanVienService {
	List<NhanVien> getAll();

	NhanVien add(NhanVienRequest nhanVienRequset);

	String delete(Long id);

	boolean checkPhoneDuplicate(String phone);

	boolean checkPhoneDuplicate(String phone, long id);

	boolean checkCccdDuplicate(String cccd);

	boolean checkCccdDuplicate(String cccd, long id);

	boolean checkEmailDuplicate(String mail);

	boolean checkEmailDuplicate(String mail, long id);

	NhanVien getOne(long id);

	NhanVien update(NhanVien nhanVien);

	void thayDoiTrangThai(long id);

}
