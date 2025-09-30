package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanKhauRepository extends JpaRepository<NhanKhau, String> {
    boolean existsByCccd(String cccd);
    List<NhanKhau> findAllByHoGiaDinh_CccdChuHo(String cccdChuHo);
}
