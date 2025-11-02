package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeSauThangResponse {
    Integer tongTienThuTatCa; // Tổng 6 tháng
    List<ThongKeThangResponse> danhSachThang; // Chi tiết từng tháng
}