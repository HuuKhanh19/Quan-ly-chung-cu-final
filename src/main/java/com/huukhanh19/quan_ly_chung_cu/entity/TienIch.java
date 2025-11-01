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
public class TienIch {
    @EmbeddedId
    MonthlyFeeId id;

    @MapsId("idCanHo")
    @ManyToOne
    @JoinColumn(name = "id_can_ho")
    @JsonBackReference("canho-tienich")
    CanHo canHo;

    @MapsId("idThoiGianThu")
    @ManyToOne
    @JoinColumn(name = "id_thoi_gian_thu")
    ThoiGianThuPhi thoiGianThuPhi;

    @Column(name = "tien_dien")
    Integer tienDien;

    @Column(name = "tien_nuoc")
    Integer tienNuoc;

    @Column(name = "tien_internet")
    Integer tienInternet;

    @Column(name = "tong_tien_ich")
    Integer tongTienIch;
}
