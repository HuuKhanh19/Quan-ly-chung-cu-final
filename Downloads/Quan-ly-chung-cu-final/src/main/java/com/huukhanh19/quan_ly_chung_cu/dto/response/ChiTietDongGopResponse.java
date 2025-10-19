package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChiTietDongGopResponse {
    Integer idChiTiet;
    Integer idKhoanDongGop;
    String tenKhoanDongGop;
    Integer idCanHo;
    String soNha;
    Integer soTienDongGop;
    LocalDate ngayDongGop;
    String hinhThucThanhToan;
    String nguoiNhan;
    String ghiChu;
}