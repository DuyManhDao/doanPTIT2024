package com.ptit.d20cntt.datn.repository;

import com.ptit.d20cntt.datn.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MauSacRepository extends JpaRepository<MauSac,Long> {


    @Query(value = "SELECT ms.* FROM mau_sac ms " +
            "JOIN chi_tiet_san_pham ctsp ON ms.id = ctsp.id_mau_sac " +
            "WHERE ctsp.id_san_pham = :productId AND ctsp.id_kich_thuoc = :sizeId",
            nativeQuery = true)
    List<MauSac> findByProductIdAndSize(@Param("productId") Long productId, @Param("sizeId") Long sizeId);

}

