package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ThoiGianThuPhiRepository extends JpaRepository<ThoiGianThuPhi, Integer> {
    @Query("SELECT t FROM ThoiGianThuPhi t ORDER BY t.id DESC")
    List<ThoiGianThuPhi> findTop6ByOrderByIdDesc();
}
