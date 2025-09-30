package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class NhanKhau {
    @Id
    String cccd;

    @ManyToOne
    @JoinColumn(name = "cccd_chu_ho")
    HoGiaDinh hoGiaDinh;

    @Column(name = "ho_va_ten", nullable = false)
    String hoVaTen;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh")
    GioiTinh gioiTinh;

    @Column(name = "ngay_sinh")
    LocalDate ngaySinh;

    @Column(name = "dan_toc")
    String danToc;

    @Column(name = "ton_giao")
    String tonGiao;

    @Column(name = "quoc_tich")
    String quocTich;

    @Column(name = "dia_chi")
    String diaChi;

    String sdt;
    String email;

    @Column(name = "quan_he")
    String quanHe;

    @Column(name = "trang_thai")
    String trangThai;
}
