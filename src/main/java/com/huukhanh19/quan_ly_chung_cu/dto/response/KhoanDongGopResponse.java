package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiDongGop;
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
public class KhoanDongGopResponse {
    Integer idKhoanDongGop;
    String tenKhoanDongGop;
    LocalDate ngayTao;
    LocalDate ngayBatDauThu;
    LocalDate hanDongGop;
    TrangThaiDongGop trangThai;
    Integer tongTienThuDuoc;
    Integer soCanHoDongGop;
}