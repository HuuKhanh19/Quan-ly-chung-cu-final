package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhiBatBuocDetailResponse {
    Integer idCanHo;

    // Phí chung cư
    Integer tongPhiChungCu;
    Integer phiDichVu;
    Integer phiQuanLy;

    // Tiện ích
    Integer tongTienIch;
    Integer tienDien;
    Integer tienNuoc;
    Integer tienInternet;

    // Gửi xe
    Integer tongGuiXe;
    Integer tienXeMay;
    Integer tienXeOto;

    // Tổng thanh toán
    Integer tongPhi;
    Integer soTienDaNop;
    Integer soDu;
    String trangThai;
}