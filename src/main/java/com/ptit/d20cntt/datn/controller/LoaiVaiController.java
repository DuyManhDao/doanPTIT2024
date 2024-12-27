package com.ptit.d20cntt.datn.controller;

import com.ptit.d20cntt.datn.entity.LoaiVai;
import com.ptit.d20cntt.datn.request.LoaiVaiRequest;
import com.ptit.d20cntt.datn.service.LoaiVaiService;
import com.ptit.d20cntt.datn.worker.SpingSecurity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/loai-vai")
public class LoaiVaiController {

    @Autowired
    private LoaiVaiService loaiVaiService;
    private SpingSecurity spingsecurity = new SpingSecurity();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tenNhanVien", spingsecurity.getCurrentNhanVienTen());
        model.addAttribute("listLoaiVai", loaiVaiService.getAllLoaiVai());
        return "admin-template/loai_vai/loai_vai";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("loaiVaiRequest", new LoaiVaiRequest());
        return "admin-template/loai_vai/them_loai_vai";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable long id) {
        LoaiVai loaiVai = this.loaiVaiService.getOne(id);
        model.addAttribute("loaiVaiUpdate", loaiVai);
        return "admin-template/loai_vai/sua_loai_vai";
    }

    @PostMapping("/themLoaiVai")
    public String addLoaiVai(@Valid @ModelAttribute("loaiVaiRequest") LoaiVaiRequest loaiVaiRequest,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-template/loai_vai/them_loai_vai";
        }
        loaiVaiService.add(loaiVaiRequest);

        return "redirect:/admin/loai-vai";
    }

    @PostMapping("/suaLoaiVai")
    public String update(@ModelAttribute("loaiVaiRequest") LoaiVai loaiVaiRequest) {
        this.loaiVaiService.update(loaiVaiRequest);
        return "redirect:/admin/loai-vai";
    }

    @GetMapping("/xoaLoaiVai/{id}")
    public String deleteLoaiVai(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        String note = loaiVaiService.delete(id);
        redirectAttributes.addFlashAttribute("deleteMessage", note);
        return "redirect:/admin/loai-vai";
    }
}

