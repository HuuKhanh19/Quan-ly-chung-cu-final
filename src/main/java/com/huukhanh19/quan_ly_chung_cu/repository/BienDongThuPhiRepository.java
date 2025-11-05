package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.BienDongThuPhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienDongThuPhiRepository extends JpaRepository<BienDongThuPhi, Integer> {

    // Lấy 10 biến động gần nhất
    @Query("SELECT b FROM BienDongThuPhi b ORDER BY b.ngayTao DESC")
    List<BienDongThuPhi> findTop10ByOrderByNgayTaoDesc();
}