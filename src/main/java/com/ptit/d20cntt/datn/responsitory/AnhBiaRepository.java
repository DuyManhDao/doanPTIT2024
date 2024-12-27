package com.ptit.d20cntt.datn.responsitory;

import com.ptit.d20cntt.datn.entity.AnhBia;
import com.ptit.d20cntt.datn.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnhBiaRepository extends JpaRepository<AnhBia,Long> {
    List<AnhBia> findBySanPham(SanPham sanPham);

    List<AnhBia> findBySanPhamId(Long id);
}
