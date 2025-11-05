package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.LoaiBienDongThuPhi;
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
@Table(name = "bien_dong_thu_phi")
public class BienDongThuPhi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_bien_dong", nullable = false)
    LoaiBienDongThuPhi loaiBienDong;

    @Column(name = "id_can_ho", nullable = false)
    Integer idCanHo;

    @Column(name = "id_thoi_gian_thu")
    Integer idThoiGianThu;

    @Column(name = "id_khoan_dong_gop")
    Integer idKhoanDongGop;

    @Column(name = "so_tien", nullable = false)
    Integer soTien;

    @Column(name = "noi_dung", columnDefinition = "TEXT")
    String noiDung;

    @Column(name = "ngay_tao")
    LocalDateTime ngayTao;

    @Column(name = "nguoi_tao")
    String nguoiTao;
}