package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.BangTamVang;
import com.huukhanh19.quan_ly_chung_cu.enums.LoaiDangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BangTamVangRepository extends JpaRepository<BangTamVang, String> {
    long countByLoaiDangKy(LoaiDangKy loaiDangKy);

    // Đếm số người tạm vắng (chuyển đi) trong khoảng thời gian
    @Query("SELECT COUNT(btv) FROM BangTamVang btv " +
            "WHERE btv.loaiDangKy = 'TAM_VANG' " +
            "AND btv.ngayBatDau BETWEEN :startDate AND :endDate")
    long countTamVangBetween(@Param("startDate") LocalDate startDate,
                             @Param("endDate") LocalDate endDate);

    // Đếm số người tạm trú (chuyển đến) trong khoảng thời gian
    @Query("SELECT COUNT(btv) FROM BangTamVang btv " +
            "WHERE btv.loaiDangKy = 'TAM_TRU' " +
            "AND btv.ngayBatDau BETWEEN :startDate AND :endDate")
    long countTamTruBetween(@Param("startDate") LocalDate startDate,
                            @Param("endDate") LocalDate endDate);
}