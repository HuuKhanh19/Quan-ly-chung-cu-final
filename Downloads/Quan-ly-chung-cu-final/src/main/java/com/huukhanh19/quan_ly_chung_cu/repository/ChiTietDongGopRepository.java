package com.huukhanh19.quan_ly_chung_cu.repository;

import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietDongGopRepository extends JpaRepository<ChiTietDongGop, Integer> {

    // Tìm chi tiết đóng góp của 1 căn hộ trong 1 khoản đóng góp
    @Query("SELECT c FROM ChiTietDongGop c WHERE c.khoanDongGop.idKhoanDongGop = :idKhoanDongGop AND c.canHo.idCanHo = :idCanHo")
    Optional<ChiTietDongGop> findByKhoanDongGopAndCanHo(
            @Param("idKhoanDongGop") Integer idKhoanDongGop,
            @Param("idCanHo") Integer idCanHo);

    // Lấy danh sách chi tiết đóng góp của 1 khoản đóng góp
    @Query("SELECT c FROM ChiTietDongGop c WHERE c.khoanDongGop.idKhoanDongGop = :idKhoanDongGop ORDER BY c.ngayDongGop DESC")
    List<ChiTietDongGop> findByIdKhoanDongGop(@Param("idKhoanDongGop") Integer idKhoanDongGop);

    // Lấy danh sách đóng góp của 1 căn hộ
    @Query("SELECT c FROM ChiTietDongGop c WHERE c.canHo.idCanHo = :idCanHo ORDER BY c.ngayDongGop DESC")
    List<ChiTietDongGop> findByIdCanHo(@Param("idCanHo") Integer idCanHo);
}