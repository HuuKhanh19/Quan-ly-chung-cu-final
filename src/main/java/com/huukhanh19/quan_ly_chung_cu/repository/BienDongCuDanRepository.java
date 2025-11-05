package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.BienDongCuDan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienDongCuDanRepository extends JpaRepository<BienDongCuDan, Integer> {

    // Lấy 10 biến động gần nhất
    @Query("SELECT b FROM BienDongCuDan b ORDER BY b.ngayTao DESC")
    List<BienDongCuDan> findTop10ByOrderByNgayTaoDesc();
}