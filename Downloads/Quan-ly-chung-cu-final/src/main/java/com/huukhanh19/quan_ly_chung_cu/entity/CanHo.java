package com.huukhanh19.quan_ly_chung_cu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CanHo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_can_ho")
    Integer idCanHo;

    @Column(name = "so_nha", nullable = false, unique = true)
    String soNha;

    @Column(name = "loai_can_ho", nullable = false)
    String loaiCanHo;

    @Column(name = "dien_tich", nullable = false)
    BigDecimal dienTich;

    @Column(name = "dia_chi", nullable = false)
    String diaChi;

    @OneToOne(mappedBy = "canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("canho-hogiadinh")
    private HoGiaDinh hoGiaDinh;

    // Một Căn Hộ có nhiều bản ghi Phí Chung Cư (mỗi tháng 1 bản ghi)
    @OneToMany(mappedBy = "canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("canho-phichungcu")
    private Set<PhiChungCu> danhSachPhiChungCu;

    // Một Căn Hộ có nhiều bản ghi Tiền Ích
    @OneToMany(mappedBy = "canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("canho-tienich")
    private Set<TienIch> danhSachTienIch;

    // Một Căn Hộ có nhiều bản ghi Phí Gửi Xe
    @OneToMany(mappedBy = "canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("canho-phiguixe")
    private Set<PhiGuiXe> danhSachPhiGuiXe;

    // Một Căn Hộ có nhiều bản ghi Tổng Thanh Toán
    @OneToMany(mappedBy = "canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("canho-tongthanhtoan")
    private Set<TongThanhToan> danhSachTongThanhToan;
}
