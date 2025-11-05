package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BienDongThuPhiResponse {
    Integer id;
    String type; // "Thu phí", "Đóng góp"
    String text; // "Hộ gia đình căn hộ 102 vừa thanh toán phí tháng 10."
    LocalDateTime ngayTao;
}