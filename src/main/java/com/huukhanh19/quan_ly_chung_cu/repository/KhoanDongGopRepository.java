package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiDongGop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoanDongGopRepository extends JpaRepository<KhoanDongGop, Integer> {

    // Lấy danh sách khoản đóng góp theo trạng thái
    List<KhoanDongGop> findByTrangThai(TrangThaiDongGop trangThai);

    // Lấy danh sách khoản đóng góp đang thu
    List<KhoanDongGop> findByTrangThaiOrderByNgayTaoDesc(TrangThaiDongGop trangThai);
}