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
public class TongThanhToanResponse {
    Integer idCanHo;
    Integer idThoiGianThu;
    String soNha;
    Integer tongPhiChungCu;
    Integer tongTienIch;
    Integer tongGuiXe;
    Integer tongPhi;
    Integer soTienDaNop;
    Integer soDu;
    String trangThai;
}