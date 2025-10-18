package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CanHoRepository extends JpaRepository<CanHo, Integer> {
    @Query("SELECT c FROM CanHo c WHERE c.hoGiaDinh IS NOT NULL")
    List<CanHo> findAllCanHoCoNguoiO();
}
