package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.Role;
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
public class BangTamVang {
    @Id
    String cccd;

    @ManyToOne
    @JoinColumn(name = "id_can_ho")
    CanHo canHo;

    @Column(name = "ho_va_ten")
    String hoVaTen;

    @Column(name = "ngay_bat_dau")
    LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    LocalDate ngayKetThuc;

    @Column(name = "ly_do")
    String lyDo;

    @Column(name = "trang_thai")
    String trangThai;

}
