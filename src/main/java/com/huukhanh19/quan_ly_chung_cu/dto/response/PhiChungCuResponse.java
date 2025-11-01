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
public class PhiChungCuResponse {
    Integer idCanHo;
    Integer idThoiGianThu;
    String soNha;
    BigDecimal dienTich;
    Integer phiDichVu;
    Integer phiQuanLy;
    Integer tongPhiChungCu;
}