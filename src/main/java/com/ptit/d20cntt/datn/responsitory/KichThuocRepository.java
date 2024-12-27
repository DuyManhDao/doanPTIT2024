package com.ptit.d20cntt.datn.responsitory;

import com.ptit.d20cntt.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KichThuocRepository extends JpaRepository<KichThuoc,Long> {


    @Query(value = "SELECT kt.* FROM kich_thuoc kt " +
            "JOIN chi_tiet_san_pham ctsp ON kt.id = ctsp.id_kich_thuoc " +
            "WHERE ctsp.id_san_pham = :productId",
            nativeQuery = true)
    List<KichThuoc> findByProductId(@Param("productId") Long productId);

}
