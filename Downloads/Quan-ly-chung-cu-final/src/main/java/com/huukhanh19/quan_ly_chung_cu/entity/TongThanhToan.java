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
public class TongThanhToan {
    @EmbeddedId
    TongThanhToanId id;

    @ManyToOne
    @MapsId("idCanHo")
    @JoinColumn(name = "id_can_ho")
    CanHo canHo;

    @Column(name = "tong_phi_chung_cu")
    Integer tongPhiChungCu;

    @Column(name = "tong_tien_ich")
    Integer tongTienIch;

    @Column(name = "tong_gui_xe")
    Integer tongGuiXe;

    @Column(name = "tong_phi")
    Integer tongPhi;

    @Column(name = "so_tien_da_nop")
    Integer soTienDaNop = 0;

    @Column(name = "so_du")
    Integer soDu = 0;

    @Column(name = "thoi_gian_thu")
    LocalDate thoiGianThu;

    @Column(name = "trang_thai")
    String trangThai;
}
