package com.ptit.d20cntt.datn.controller;

import com.ptit.d20cntt.datn.entity.ChiTietSanPham;
import com.ptit.d20cntt.datn.request.ChiTietSanPhamRequest;
import com.ptit.d20cntt.datn.service.ChiTietSanPhamService;
import com.ptit.d20cntt.datn.service.LoaiVaiService;
import com.ptit.d20cntt.datn.service.KhichThuocService;
import com.ptit.d20cntt.datn.service.MauSacService;
import com.ptit.d20cntt.datn.service.SanPhamService;
import com.ptit.d20cntt.datn.worker.SpingSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//chi tiết sản phẩm trong admin

@Controller
@RequestMapping("/admin/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    private LoaiVaiService loaiVaiService;
    private KhichThuocService kichThuocService;
    private MauSacService mauSacService;
    private SanPhamService sanPhamService;

    public ChiTietSanPhamController(LoaiVaiService loaiVaiService, KhichThuocService kichThuocService, MauSacService mauSacService,
                                    SanPhamService sanPhamService) {
        this.loaiVaiService = loaiVaiService;
        this.kichThuocService = kichThuocService;
        this.mauSacService = mauSacService;
        this.sanPhamService = sanPhamService;
    }
    private SpingSecurity spingsecurity = new SpingSecurity();
    @GetMapping()
    public String getAll(Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.getAllChiTietSanPham());
        return "admin-template/chi_tiet_san_pham/chi_tiet_san_pham";
    }


    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        model.addAttribute("chiTietSanPhamRequest", new ChiTietSanPhamRequest());
        model.addAttribute("listLoaiVai", loaiVaiService.getAllLoaiVai());
        model.addAttribute("listKichThuoc", kichThuocService.getAllKichThuoc());
        model.addAttribute("listMauSac", mauSacService.getAllMauSac());
        model.addAttribute("listSanPham", sanPhamService.getAll());
        return "admin-template/chi_tiet_san_pham/them_chi_tiet_san_pham";
    }


    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") long id, Model model) {
        Long idNhanVien = spingsecurity.getCurrentNhanVienId();
        if (idNhanVien == null){
            return "redirect:/login";
        }

        model.addAttribute("tenNhanVien",spingsecurity.getCurrentNhanVienTen());

        ChiTietSanPham chiTietSanPham = this.chiTietSanPhamService.getOne(id);
        model.addAttribute("chiTietSanPhamUpdate", chiTietSanPham);
        model.addAttribute("listLoaiVai", loaiVaiService.getAllLoaiVai());
        model.addAttribute("listKichThuoc", kichThuocService.getAllKichThuoc());
        model.addAttribute("listMauSac", mauSacService.getAllMauSac());
        model.addAttribute("listSanPham", sanPhamService.getAll());
        return "admin-template/chi_tiet_san_pham/sua_chi_tiet_san_pham";
    }

    @PostMapping("/themChiTietSanPham")
    public String addChiTietSanPham(@ModelAttribute("chiTietSanPhamRequest") ChiTietSanPhamRequest chiTietSanPhamRequest) {
        chiTietSanPhamService.add(chiTietSanPhamRequest);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @PostMapping("/suaChiTietSanPham")
    public String update(@ModelAttribute("chiTietSanPhamRequest") ChiTietSanPham chiTietSanPhamRequest) {
        this.chiTietSanPhamService.update(chiTietSanPhamRequest);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @GetMapping("/thay-doi-trang-thai/{id}")
    public String thaytt(@PathVariable("id") Long id) {
        chiTietSanPhamService.thayDoiTrangThai(id);
        return "redirect:/admin/chi-tiet-san-pham";
    }

    @GetMapping("/xoaChiTietSanPham/{id}")
    public String deleteChiTietSanPham(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        String note = chiTietSanPhamService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/chi-tiet-san-pham";
    }


}
