package com.ptit.d20cntt.datn.repository;

import com.ptit.d20cntt.datn.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang,Long> {

    GioHang getByKhachHangId(Long id);
}
