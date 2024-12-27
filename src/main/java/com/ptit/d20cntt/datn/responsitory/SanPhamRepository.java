package com.ptit.d20cntt.datn.responsitory;

import com.ptit.d20cntt.datn.custom.SanPhamCustom;
import com.ptit.d20cntt.datn.entity.SanPham;
import com.ptit.d20cntt.datn.enumation.TrangThai;
import com.ptit.d20cntt.datn.request.SanPhamRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham,Long> {
    @Query("""
                SELECT sp FROM SanPham sp
                WHERE 
                   sp.trangThai = :trangThai
            """)
    List<SanPhamCustom> getAllByTrangThai(
            @Param("trangThai") TrangThai trangThai);

    @Query(value = "select p from SanPham p", nativeQuery = false)
    List<SanPhamCustom> getPageSanPhamCusTom();

    @Query(value = "select p from SanPham p where p.id=?1", nativeQuery = false)
    Optional<SanPhamRequest> findById1(Long id);

    boolean existsByMa(String ma);

    boolean existsByTen(String ten);

    boolean existsByTenAndIdNot(String ten, Long id);

    @Query(value = "select * from san_pham where san_pham.ten like :tenSanPham", nativeQuery = true)
    Optional<SanPham> findSanPhamByTen(@Param("tenSanPham") String tenSanPham);
}
