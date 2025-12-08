package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeCuDanThangResponse {
    Integer thang;           // 1-12
    String thangDisplay;     // "Tháng 1", "Tháng 2",...
    Long soNguoiChuyenDen;   // Số người chuyển đến trong tháng
    Long soNguoiChuyenDi;    // Số người chuyển đi trong tháng
    Long tongDanSo;          // Tổng dân số cuối tháng
}