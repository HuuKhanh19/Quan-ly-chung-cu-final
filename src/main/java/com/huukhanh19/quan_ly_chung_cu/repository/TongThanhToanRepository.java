package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TongThanhToanRepository extends JpaRepository<TongThanhToan, MonthlyFeeId> {

    // Tìm tất cả TongThanhToan theo idThoiGianThu
    @Query("SELECT t FROM TongThanhToan t WHERE t.id.idThoiGianThu = :idThoiGianThu")
    List<TongThanhToan> findByIdThoiGianThu(@Param("idThoiGianThu") Integer idThoiGianThu);

    // Tìm tất cả TongThanhToan theo idCanHo
    @Query("SELECT t FROM TongThanhToan t WHERE t.id.idCanHo = :idCanHo")
    List<TongThanhToan> findByIdCanHo(@Param("idCanHo") Integer idCanHo);

    // Tìm TongThanhToan theo trạng thái
    @Query("SELECT t FROM TongThanhToan t WHERE t.trangThai = :trangThai")
    List<TongThanhToan> findByTrangThai(@Param("trangThai") String trangThai);

    // Tìm tất cả TongThanhToan chưa thanh toán (soDu > 0)
    @Query("SELECT t FROM TongThanhToan t WHERE t.soDu > 0")
    List<TongThanhToan> findAllChuaThanhToan();

    // Tìm tất cả TongThanhToan đã thanh toán (soDu = 0)
    @Query("SELECT t FROM TongThanhToan t WHERE t.soDu = 0")
    List<TongThanhToan> findAllDaThanhToan();

    // Tính tổng số tiền đã nộp theo thời gian thu
    @Query("SELECT SUM(t.soTienDaNop) FROM TongThanhToan t WHERE t.id.idThoiGianThu = :idThoiGianThu")
    Long sumSoTienDaNopByIdThoiGianThu(@Param("idThoiGianThu") Integer idThoiGianThu);

    // Tính tổng số dư theo thời gian thu
    @Query("SELECT SUM(t.soDu) FROM TongThanhToan t WHERE t.id.idThoiGianThu = :idThoiGianThu")
    Long sumSoDuByIdThoiGianThu(@Param("idThoiGianThu") Integer idThoiGianThu);
}