package com.ptit.d20cntt.datn.repository;

import com.ptit.d20cntt.datn.entity.ThuongHieu;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Long> {

    @Query("""
                SELECT th FROM ThuongHieu th
                WHERE 
                   th.trangThai = :trangThai
            """)
    List<ThuongHieu> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai);

    boolean existsByMa(String ma);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);
}
