package com.ptit.d20cntt.datn.request;

import com.ptit.d20cntt.datn.enumation.TrangThai;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DongSanPhamRequest {
    private Long id;

    private String ma;
    @NotBlank(message = "Tên không được để trống!")
    private String ten;

    private LocalDate ngayTao;

    private LocalDate ngaySua;

    private TrangThai trangThai;
}
