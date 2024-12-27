package com.ptit.d20cntt.datn.responsitory;

import com.ptit.d20cntt.datn.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {

    @Query(value ="select * from dia_chi where id_khach_hang= :idKhachHang",nativeQuery = true )
    List<DiaChi> getAllTheoKhachHang(@Param("idKhachHang") Long id);
}
