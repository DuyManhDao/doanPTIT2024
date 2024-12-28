package com.ptit.d20cntt.datn.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoaiVaiRequest {

    private Long id;

    private String ma;

    private LocalDateTime ngaySua;

    private LocalDateTime ngayTao;

    @NotEmpty(message = "Tên không được để trống!")
    private String ten;
}