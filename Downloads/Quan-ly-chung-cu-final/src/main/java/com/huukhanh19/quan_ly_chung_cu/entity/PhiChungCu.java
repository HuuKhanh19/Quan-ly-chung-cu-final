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
public class PhiChungCu {
    @EmbeddedId
    MonthlyFeeId id;

    @MapsId("idCanHo") // Ánh xạ tới thuộc tính idCanHo trong MonthlyFeeId
    @ManyToOne
    @JoinColumn(name = "id_can_ho")
    @JsonBackReference("canho-phichungcu")
    CanHo canHo;

    @MapsId("idThoiGianThu")
    @ManyToOne
    @JoinColumn(name = "id_thoi_gian_thu")
    private ThoiGianThuPhi thoiGianThuPhi;

    @Column(name = "phi_dich_vu")
    Integer phiDichVu;

    @Column(name = "phi_quan_ly")
    Integer phiQuanLy;

    @Column(name = "tong_phi_chung_cu")
    Integer tongPhiChungCu;
}
