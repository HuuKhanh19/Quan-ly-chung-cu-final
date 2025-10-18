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
public class PhiGuiXe {
    @EmbeddedId
    MonthlyFeeId id;

    @MapsId("idCanHo")
    @ManyToOne
    @JoinColumn(name = "id_can_ho")
    CanHo canHo;

    @Column(name = "tien_xe_may")
    Integer tienXeMay;

    @Column(name = "tien_xe_oto")
    Integer tienXeOto;

    @Column(name = "tong_gui_xe")
    Integer tongGuiXe;

    @Column(name = "thoi_gian_thu")
    LocalDate thoiGianThu;
}
