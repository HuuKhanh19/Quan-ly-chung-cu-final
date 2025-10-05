package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.LichSuThayDoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuThayDoiRepository extends JpaRepository<LichSuThayDoi, Integer> {
    // Tìm tất cả lịch sử thay đổi của một nhân khẩu
    List<LichSuThayDoi> findAllByCccdNhanKhau(String cccdNhanKhau);
}