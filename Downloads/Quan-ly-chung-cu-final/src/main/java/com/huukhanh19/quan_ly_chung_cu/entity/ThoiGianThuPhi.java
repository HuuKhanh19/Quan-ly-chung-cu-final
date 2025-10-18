package com.huukhanh19.quan_ly_chung_cu.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "thoi_gian_thu_phi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThoiGianThuPhi {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "ngay_thu", nullable = false)
    private LocalDate ngayThu;

    @Column(name = "han_thu", nullable = false)
    private LocalDate hanThu;
}