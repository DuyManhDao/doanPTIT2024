package com.ptit.d20cntt.datn.responsitory;

import com.ptit.d20cntt.datn.entity.DongSanPham;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DongSanPhamRepository extends JpaRepository<DongSanPham,Long> {


    @Query("""
                SELECT dsp FROM DongSanPham dsp
                WHERE 
                   dsp.trangThai = :trangThai
            """)
    List<DongSanPham> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai
    );

    boolean existsByMa(String ma);

    boolean existsByTen(String ten);


    boolean existsByTenAndIdNot(String ten, Long id);
}
