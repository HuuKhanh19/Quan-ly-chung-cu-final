package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class HoGiaDinh {
    @Id
    @Column(name = "cccd_chu_ho")
    String cccdChuHo;

    @OneToOne
    @JoinColumn(name = "id_can_ho", unique = true) // 'unique = true' là bắt buộc cho OneToOne
    CanHo canHo;

    @Column(name = "so_thanh_vien")
    Integer soThanhVien;

    @Column(name = "ho_ten_chu_ho", nullable = false)
    String hoTenChuHo;

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

    @Column(name = "so_xe_may")
    Integer soXeMay;

    @Column(name = "so_oto")
    Integer soOto;

    @Column(name = "trang_thai")
    String trangThai;

    // Một hộ gia đình có nhiều nhân khẩu
    @OneToMany(mappedBy = "hoGiaDinh")
    Set<NhanKhau> nhanKhaus;
}
