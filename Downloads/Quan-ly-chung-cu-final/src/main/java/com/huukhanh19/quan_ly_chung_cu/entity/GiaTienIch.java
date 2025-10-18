package com.huukhanh19.quan_ly_chung_cu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class GiaTienIch {
    @Id
    @Column(name = "id_thoi_gian_thu")
    private Integer id;

    // Phía 'GiaTienIch' là phía "sở hữu", dùng @MapsId và @JoinColumn
    @OneToOne
    @MapsId // Báo cho JPA biết rằng khóa chính 'id' cũng là mối quan hệ
    @JoinColumn(name = "id_thoi_gian_thu")
    private ThoiGianThuPhi thoiGianThuPhi;

    @Column(name = "gia_dien", nullable = false)
    private BigDecimal giaDien;

    @Column(name = "gia_nuoc", nullable = false)
    private BigDecimal giaNuoc;

    @Column(name = "tien_internet", nullable = false)
    private Integer tienInternet;
}
