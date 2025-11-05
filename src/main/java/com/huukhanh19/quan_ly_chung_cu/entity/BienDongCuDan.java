package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.LoaiBienDong;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "bien_dong_cu_dan")
public class BienDongCuDan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_bien_dong", nullable = false)
    LoaiBienDong loaiBienDong;

    @Column(name = "id_can_ho", nullable = false)
    Integer idCanHo;

    @Column(name = "cccd_nhan_khau")
    String cccdNhanKhau;

    @Column(name = "ho_va_ten")
    String hoVaTen;

    @Column(name = "noi_dung", columnDefinition = "TEXT")
    String noiDung;

    @Column(name = "ngay_tao")
    LocalDateTime ngayTao;

    @Column(name = "nguoi_tao")
    String nguoiTao;
}