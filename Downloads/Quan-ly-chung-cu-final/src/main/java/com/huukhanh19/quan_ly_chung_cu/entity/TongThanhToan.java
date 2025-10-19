package com.huukhanh19.quan_ly_chung_cu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiThanhToan;
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
    MonthlyFeeId id;

    @MapsId("idCanHo")
    @ManyToOne
    @JoinColumn(name = "id_can_ho")
    @JsonBackReference("canho-tongthanhtoan")
    CanHo canHo;

    @MapsId("idThoiGianThu")
    @ManyToOne
    @JoinColumn(name = "id_thoi_gian_thu")
    ThoiGianThuPhi thoiGianThuPhi;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai")
    TrangThaiThanhToan trangThai;
}
