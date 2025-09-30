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
public class TienIch {
    @EmbeddedId
    private TienIchId id;

    @ManyToOne
    @MapsId("idCanHo")
    @JoinColumn(name = "id_can_ho")
    CanHo canHo;

    @Column(name = "so_dien")
    Integer soDien;

    @Column(name = "so_nuoc")
    Integer soNuoc;

    Integer internet;

    @Column(name = "tong_tien_ich")
    Integer tongTienIch;
}
