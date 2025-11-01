package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiDongGop;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "khoan_dong_gop")
public class KhoanDongGop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_khoan_dong_gop")
    Integer idKhoanDongGop;

    @Column(name = "ten_khoan_dong_gop", nullable = false)
    String tenKhoanDongGop;

    @Column(name = "ngay_tao", nullable = false)
    LocalDate ngayTao;

    @Column(name = "ngay_bat_dau_thu", nullable = false)
    LocalDate ngayBatDauThu;

    @Column(name = "han_dong_gop", nullable = false)
    LocalDate hanDongGop;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    TrangThaiDongGop trangThai;

    @Column(name = "tong_tien_thu_duoc")
    Integer tongTienThuDuoc = 0;

    @Column(name = "so_can_ho_dong_gop")
    Integer soCanHoDongGop = 0;

    // Quan hệ One-to-Many với ChiTietDongGop
    @OneToMany(mappedBy = "khoanDongGop", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ChiTietDongGop> danhSachChiTiet;
}