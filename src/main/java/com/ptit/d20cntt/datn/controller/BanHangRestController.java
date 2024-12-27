package com.ptit.d20cntt.datn.controller;

import com.ptit.d20cntt.datn.responsitory.HoaDonChiTietRepository;
import com.ptit.d20cntt.datn.service.BanHangService;
import com.ptit.d20cntt.datn.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BanHangRestController {

    @Autowired
    private BanHangService banHangService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    //Validate các trường hợp của sản phẩm ,giỏ hàng,thanh toán
    @RequestMapping(value = "/admin/ban-hang/check-thanh-toan", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer checkThanhToan(@RequestParam("id") String idHoaDonCho) {
        if (idHoaDonCho.equals("")) {
            return 1;
        } else if (banHangService.getHoaDonChiTietByIdHoaDon(Long.valueOf(idHoaDonCho)).isEmpty()) {
            return 2;
        } else {
            return 0;
        }
    }

    //Validate phải chọn đơn hàng mới hủy được đơn
    @RequestMapping(value = "/admin/ban-hang/check-huy-don", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer checkHuyDon(@RequestParam("id") String idHoaDonCho) {
        if (idHoaDonCho.equals("")) {
            return 1;
        } else {
            return 0;
        }
    }
//validate số lượng tồn sản phẩm
    @RequestMapping(value = "/admin/ban-hang/them-san-pham/check-so-luong/{soLuongTon}", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer checkSoLuongsp(@RequestParam("soLuong") String soLuong,
                                  @PathVariable("soLuongTon") Integer soLuongTon) {
        try {
            if (soLuong.equals("")) {
                return 1;
            } else if (Integer.parseInt(soLuong) <= 0) {
                return 2;
            } else if (Integer.parseInt(soLuong) > soLuongTon) {
                return 3;
            } else {
                return 0;
            }
        } catch (NumberFormatException numberFormatException) {
            return 4;
        }
    }



}
