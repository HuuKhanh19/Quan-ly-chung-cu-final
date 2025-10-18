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
    Integer idThoiGianThu;
    String soNha;
    BigDecimal dienTich;

    // Chi tiết phí chung cư
    Integer phiDichVu;
    Integer phiQuanLy;
    Integer tongPhiChungCu;

    // Chi tiết tiện ích
    Integer tienDien;
    Integer tienNuoc;
    Integer tienInternet;
    Integer tongTienIch;

    // Chi tiết gửi xe
    Integer soXeMay;
    Integer soOto;
    Integer tienXeMay;
    Integer tienXeOto;
    Integer tongGuiXe;

    // Tổng thanh toán
    Integer tongPhi;
    Integer soTienDaNop;
    Integer soDu;
    String trangThai;
}