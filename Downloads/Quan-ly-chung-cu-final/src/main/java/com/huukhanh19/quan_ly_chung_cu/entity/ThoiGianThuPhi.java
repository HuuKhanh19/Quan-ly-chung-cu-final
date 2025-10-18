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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ngay_thu", nullable = false)
    private LocalDate ngayThu;

    @Column(name = "han_thu", nullable = false)
    private LocalDate hanThu;

    // Phía 'ThoiGianThuPhi' là phía "bị sở hữu", dùng mappedBy
    @OneToOne(mappedBy = "thoiGianThuPhi", cascade = CascadeType.ALL, orphanRemoval = true)
    private GiaTienIch giaTienIch;
}