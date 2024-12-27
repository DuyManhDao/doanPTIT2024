package com.ptit.d20cntt.datn.service;

import com.ptit.d20cntt.datn.dto.GioHangWrapper;
import com.ptit.d20cntt.datn.dto.OrderDataDTO;
import com.ptit.d20cntt.datn.entity.GioHangChiTiet;
import com.ptit.d20cntt.datn.entity.HoaDon;

import java.math.BigDecimal;
import java.util.List;

public interface BanHangOnlineCtService {
    GioHangChiTiet themVaoGioHang(Long khachHangId, Long chiTietSanPhamId, Integer soLuong);

    void xoaKhoiGioHang(Long id);

    void datHang(List<GioHangChiTiet> listGioHangChiTiet, String ten, String diaChi, String sdt, String ghiChu);

    HoaDon datHangItems(GioHangWrapper gioHangWrapper, Long idKachHang, String ten, String diaChi, String sdt, String ghiChu, BigDecimal shippingFee, BigDecimal tongTien, BigDecimal totalAmount, BigDecimal tienGiamGia, Long selectedVoucherId, BigDecimal diemTichLuy, String useAll);

    List<GioHangChiTiet> updateGioHangChiTiet(Long idGioHangChiTiet, Integer soLuong);

    List<GioHangChiTiet> findAllById(List<String> listIdString);

    GioHangWrapper findAllItemsById(List<String> listIdString);

    void saveOrderData(OrderDataDTO orderDataDTO);

    OrderDataDTO getOrderData();

    void saveGioHangWrapper(GioHangWrapper gioHangWrapper);

    GioHangWrapper getGioHangWrapper();
}
