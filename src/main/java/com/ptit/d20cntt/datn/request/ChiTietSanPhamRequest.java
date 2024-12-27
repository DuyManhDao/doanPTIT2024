package com.ptit.d20cntt.datn.request;

import com.ptit.d20cntt.datn.entity.AnhBia;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ChiTietSanPhamRequest {

        private Long id;

        private BigDecimal giaBan;

        private BigDecimal giaMacDinh;

        private LocalDateTime ngaySua;

        private LocalDateTime ngayTao;

        private int soLuongTon;

        private int trangThai;

        private Long loaiVai;

        private Long kichThuoc;

        private Long mauSac;

        private Long sanPham;


        private List<AnhBia> listAnh = new ArrayList<>();

        private String anhChinh;
}
