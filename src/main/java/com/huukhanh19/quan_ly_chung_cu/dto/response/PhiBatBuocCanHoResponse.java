package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiThanhToan;
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
public class PhiBatBuocCanHoResponse {
    Integer idThoiGianThu;
    LocalDate ngayThu;
    LocalDate hanThu;

    // Chi tiết các khoản phí
    Integer phiChungCu;
    Integer tienIch;
    Integer guiXe;
    Integer tongPhi;

    // Trạng thái thanh toán
    Integer soTienDaNop;
    Integer soDu;
    TrangThaiThanhToan trangThai;
}