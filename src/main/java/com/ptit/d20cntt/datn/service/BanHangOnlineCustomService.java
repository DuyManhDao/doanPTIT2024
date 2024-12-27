package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.custom.ChiTietSanPhamCustomerCt;
import com.ptit.d20cntt.datn.dto.AnhSpCustom;
import com.ptit.d20cntt.datn.dto.ChiTietSanPhamDTO;
import com.ptit.d20cntt.datn.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BanHangOnlineCustomService {
    Page<ChiTietSanPham> pageAllInShop(Integer pageNo, Integer size);

    Integer nextPage(Integer pageNo);

    List<ChiTietSanPhamCustomerCt> list3Custom();



    List<ChiTietSanPhamCustomerCt> list4Random();

    List<AnhSpCustom> listAnhDetail(Long id);

    ChiTietSanPham getById(Long id);



    public ChiTietSanPhamDTO convertToDTO(ChiTietSanPham chiTietSanPham);

    public List<ChiTietSanPhamDTO> convertToDTOList(List<ChiTietSanPham> chiTietSanPhamList);

    Page<ChiTietSanPhamDTO> findAllByCondition(List<String> tenThuongHieu, List<String> tenDongSanPham, List<String> tenKichThuoc, List<String> tenDeGiay, List<String> tenMauSac, Double minGia, Double maxGia, int page, int pageSize, String sortField, String tenSanPham);
}
