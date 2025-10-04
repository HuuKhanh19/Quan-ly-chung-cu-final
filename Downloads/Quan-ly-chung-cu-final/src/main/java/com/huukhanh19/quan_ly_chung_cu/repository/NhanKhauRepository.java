package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanKhauRepository extends JpaRepository<NhanKhau, String> {
    boolean existsByCccd(String cccd);
    List<NhanKhau> findAllByHoGiaDinh_CccdChuHo(String cccdChuHo);
    /**
     * Tìm theo tên (tìm kiếm gần đúng)
     */
    List<NhanKhau> findByHoVaTenContainingIgnoreCase(String hoVaTen);

    /**
     * Tìm theo quan hệ
     */
    List<NhanKhau> findByQuanHe(String quanHe);

    /**
     * Tìm theo trạng thái
     */
    List<NhanKhau> findByTrangThai(String trangThai);

    /**
     * Đếm số nhân khẩu theo hộ gia đình
     */
    long countByHoGiaDinh_CccdChuHo(String cccdChuHo);

    /**
     * Kiểm tra SĐT đã tồn tại chưa (trừ CCCD hiện tại)
     */
    boolean existsBySdtAndCccdNot(String sdt, String cccd);

    /**
     * Kiểm tra Email đã tồn tại chưa (trừ CCCD hiện tại)
     */
    boolean existsByEmailAndCccdNot(String email, String cccd);

    long countByGioiTinh(GioiTinh gioiTinh);
}
