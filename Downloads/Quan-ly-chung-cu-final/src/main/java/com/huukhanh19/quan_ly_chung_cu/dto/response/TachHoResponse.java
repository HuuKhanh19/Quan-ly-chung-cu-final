package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TachHoResponse {
    String cccdChuHoCu;
    String hoTenChuHoCu;
    Integer soThanhVienConLai;

    String cccdChuHoMoi;
    String hoTenChuHoMoi;
    String canHoMoi;
    Integer tangMoi;
    Integer soThanhVienMoi;

    String message;
}