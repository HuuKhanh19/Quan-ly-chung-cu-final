package com.huukhanh19.quan_ly_chung_cu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "chi_tiet_dong_gop",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_dong_gop",
                columnNames = {"id_khoan_dong_gop", "id_can_ho"}
        ))
public class ChiTietDongGop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chi_tiet")
    Integer idChiTiet;

    @ManyToOne
    @JoinColumn(name = "id_khoan_dong_gop", nullable = false)
    @JsonBackReference("khoan-chitiet")
    KhoanDongGop khoanDongGop;

    @ManyToOne
    @JoinColumn(name = "id_can_ho", nullable = false)
    @JsonBackReference("canho-chitietdonggop")
    CanHo canHo;

    @Column(name = "so_tien_dong_gop", nullable = false)
    Integer soTienDongGop;

    @Column(name = "ngay_dong_gop", nullable = false)
    LocalDate ngayDongGop;

    @Column(name = "hinh_thuc_thanh_toan")
    String hinhThucThanhToan = "Tiền mặt";

    @Column(name = "nguoi_nhan")
    String nguoiNhan;

    @Column(name = "ghi_chu", columnDefinition = "TEXT")
    String ghiChu;
}