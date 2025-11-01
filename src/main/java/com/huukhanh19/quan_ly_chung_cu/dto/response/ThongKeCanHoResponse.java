package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiThanhToan;
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
public class ThongKeCanHoResponse {
    Integer idCanHo;
    String soNha;
    Integer tongPhi;
    Integer soTienDaNop;
    Integer soDu;
    TrangThaiThanhToan trangThai;
}