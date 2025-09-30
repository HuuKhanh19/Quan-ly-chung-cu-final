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
public class KhoanDongGop {
    @EmbeddedId
    private KhoanDongGopId id;

    @ManyToOne
    @MapsId("cccdChuHo") // Ánh xạ tới thuộc tính cccdChuHo trong KhoanDongGopId
    @JoinColumn(name = "cccd_chu_ho")
    HoGiaDinh hoGiaDinh;

    @Column(name = "quy_vnn")
    Integer quyVnn = 0;

    @Column(name = "quy_vbd")
    Integer quyVbd = 0;

    @Column(name = "quy_tdp")
    Integer quyTdp = 0;

    @Column(name = "quy_vtt")
    Integer quyVtt = 0;

    @Column(name = "quy_vndtt")
    Integer quyVndtt = 0;

    @Column(name = "quy_tn")
    Integer quyTn = 0;

    @Column(name = "quy_kh")
    Integer quyKh = 0;

    @Column(name = "quy_nct")
    Integer quyNct = 0;

    @Column(name = "tong_thu")
    Integer tongThu;
}
