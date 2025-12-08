package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeQuyResponse {
    Integer quy;             // 1-4
    String quyDisplay;       // "Quý 1", "Quý 2",...
    Long soNguoiChuyenDen;   // Số người chuyển đến trong quý
    Long soNguoiChuyenDi;    // Số người chuyển đi trong quý
    Long tongDanSo;          // Tổng dân số cuối quý
}