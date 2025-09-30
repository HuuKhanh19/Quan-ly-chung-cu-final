package com.huukhanh19.quan_ly_chung_cu.entity;

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
    private PhiChungCuId id;

    @ManyToOne
    @MapsId("idCanHo") // Ánh xạ tới thuộc tính idCanHo trong PhiChungCuId
    @JoinColumn(name = "id_can_ho")
    CanHo canHo;

    @Column(name = "phi_dich_vu")
    Integer phiDichVu;

    @Column(name = "phi_quan_ly")
    Integer phiQuanLy;

    @Column(name = "tong_phi_chung_cu")
    Integer tongPhiChungCu;

    @Column(name = "thoi_gian_thu")
    LocalDate thoiGianThu;
}
