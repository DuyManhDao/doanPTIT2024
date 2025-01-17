package com.ptit.d20cntt.datn.repository;

import com.ptit.d20cntt.datn.custom.ChiTietSanPhamCustomerCt;
import com.ptit.d20cntt.datn.dto.AnhSpCustom;
import com.ptit.d20cntt.datn.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BanHangOnlineRepository extends JpaRepository<ChiTietSanPham, Long> {


//random sp
    Page<ChiTietSanPham> findAll(Specification<ChiTietSanPham> spec, Pageable pageable);

    @Query(value = "select * from chi_tiet_san_pham where chi_tiet_san_pham.trang_thai = 0", nativeQuery = true)
    Page<ChiTietSanPham> pageAllInShop(Pageable pageable);




    @Query(value = "select san_pham.ten as tenSp, san_pham.anh_chinh, chi_tiet_san_pham.id, chi_tiet_san_pham.gia_ban, chi_tiet_san_pham.gia_mac_dinh, loai_vai.ten as tenLoai_vaii,\n" +
            " dong_san_pham.ten as tenDongSp, kich_thuoc.ten as tenKich_thuoc, mau_sac.ten as tenMau_sac\n" +
            "from chi_tiet_san_pham\n" +
            "join san_pham on chi_tiet_san_pham.id_san_pham = san_pham.id \n" +
            "join loai_vai on chi_tiet_san_pham.id_loai_vai = loai_vai.id \n" +
            "join dong_san_pham on san_pham.id_dong_san_pham = dong_san_pham.id\n" +
            "join kich_thuoc on chi_tiet_san_pham.id_kich_thuoc = kich_thuoc.id\n" +
            "join mau_sac on chi_tiet_san_pham.id_mau_sac = mau_sac.id\n" +
            "where dong_san_pham.ten = 'Giày custom' and chi_tiet_san_pham.trang_thai = 0 and chi_tiet_san_pham.so_luong_ton>0 limit 3", nativeQuery = true)
    List<ChiTietSanPhamCustomerCt> list3Custom();


    @Query(value = "select san_pham.ten as tenSp, san_pham.anh_chinh, chi_tiet_san_pham.id, chi_tiet_san_pham.gia_ban, chi_tiet_san_pham.gia_mac_dinh, loai_vai.ten as tenLoai_vaii,\n" +
            " dong_san_pham.ten as tenDongSp, kich_thuoc.ten as tenKich_thuoc, mau_sac.ten as tenMau_sac\n" +
            "from chi_tiet_san_pham\n" +
            "join san_pham on chi_tiet_san_pham.id_san_pham = san_pham.id \n" +
            "join loai_vai on chi_tiet_san_pham.id_loai_vai = loai_vai.id \n" +
            "join dong_san_pham on san_pham.id_dong_san_pham = dong_san_pham.id\n" +
            "join kich_thuoc on chi_tiet_san_pham.id_kich_thuoc = kich_thuoc.id\n" +
            "join mau_sac on chi_tiet_san_pham.id_mau_sac = mau_sac.id\n" +
            "where chi_tiet_san_pham.trang_thai = 0 and chi_tiet_san_pham.so_luong_ton>0 order by rand() limit 4 ;", nativeQuery = true)
    List<ChiTietSanPhamCustomerCt> list4Random();

    @Query(value = "select anh.url, chi_tiet_san_pham.id from chi_tiet_san_pham\n" +
            "join san_pham on san_pham.id = chi_tiet_san_pham.id_san_pham\n" +
            "join anh on anh.id_san_pham = san_pham.id\n" +
            "where chi_tiet_san_pham.id = :id", nativeQuery = true)
    List<AnhSpCustom> listAnhDetail(Long id);
}


