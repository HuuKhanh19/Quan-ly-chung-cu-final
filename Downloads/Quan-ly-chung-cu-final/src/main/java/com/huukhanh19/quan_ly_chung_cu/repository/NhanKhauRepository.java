package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
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

    // Đếm số người sinh sau một ngày nhất định (ví dụ: trẻ em)
    @Query("SELECT COUNT(nk) FROM NhanKhau nk WHERE nk.ngaySinh > :startDate")
    long countByNgaySinhAfter(@Param("startDate") LocalDate startDate);

    // Đếm số người sinh trong một khoảng thời gian (ví dụ: tuổi lao động)
    @Query("SELECT COUNT(nk) FROM NhanKhau nk WHERE nk.ngaySinh BETWEEN :startDate AND :endDate")
    long countByNgaySinhBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Đếm số người sinh trước một ngày nhất định (ví dụ: người cao tuổi)
    @Query("SELECT COUNT(nk) FROM NhanKhau nk WHERE nk.ngaySinh <= :endDate")
    long countByNgaySinhBefore(@Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(nk) FROM NhanKhau nk WHERE nk.ngayTao BETWEEN :startDate AND :endDate")
    long countByNgayTaoBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
