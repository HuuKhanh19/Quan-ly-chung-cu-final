package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeThuPhiResponse {
    // Thông tin kỳ thu
    LocalDate ngayThu;
    LocalDate hanThu;

    // Thống kê tổng quan căn hộ
    Integer tongCanHo;
    Integer soCanHoDaNop;
    Integer soCanHoChuaNop;

    // Thống kê tiền
    Integer tongPhiPhaiThu;
    Integer tongTienDaThu;
    Integer tongConThieu;

    // Thống kê phân loại theo loại phí
    Integer tongPhiChungCu;
    Integer tongTienIch;
    Integer tongGuiXe;

    // Chi tiết từng căn hộ
    List<ThongKeCanHoResponse> danhSachCanHo;
}