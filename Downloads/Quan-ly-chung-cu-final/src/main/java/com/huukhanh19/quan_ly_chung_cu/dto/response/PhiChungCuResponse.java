package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhiChungCuResponse {
    Integer idCanHo;
    Integer phiDichVu;
    Integer phiQuanLy;
    Integer tongPhiChungCu;
    LocalDate hanThu;
    LocalDate thoiGianThu;
}
