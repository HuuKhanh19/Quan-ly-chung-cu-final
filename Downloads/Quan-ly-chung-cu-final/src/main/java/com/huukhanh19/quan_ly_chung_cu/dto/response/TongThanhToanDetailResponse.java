package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TongThanhToanDetailResponse {
    // Thông tin căn hộ
    Integer idCanHo;

    // Chi tiết phí chung cư
    Integer tongPhiChungCu;
    Integer phiDichVu;
    Integer phiQuanLy;

    // Chi tiết tiện ích
    Integer tongTienIch;
    Integer tienDien;
    Integer tienNuoc;
    Integer tienInternet;

    // Chi tiết gửi xe
    Integer tongGuiXe;
    Integer tienXeMay;
    Integer tienXeOto;

    // Tổng thanh toán
    Integer tongPhi;
    Integer soTienDaNop;
    Integer soDu;
    String trangThai;
}