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
public class LichSuThayDoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cccd_nhan_khau")
    private String cccdNhanKhau;

    @Column(name = "thong_tin_thay_doi")
    private String thongTinThayDoi;

    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi;

    @Column(name = "nguoi_thuc_hien")
    private String nguoiThucHien;
}
