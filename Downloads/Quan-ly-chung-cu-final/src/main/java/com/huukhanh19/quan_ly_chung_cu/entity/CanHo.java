package com.huukhanh19.quan_ly_chung_cu.entity;

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

    // Một căn hộ có thể có nhiều hộ gia đình (trường hợp cho thuê lại)
    @OneToMany(mappedBy = "canHo")
    Set<HoGiaDinh> hoGiaDinhs;
}
