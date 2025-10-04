package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeDoTuoiResponse {
    long duoiViThanhNien; // Dưới 15 tuổi
    long trongDoTuoiLaoDong; // 15 - 64 tuổi
    long trenTuoiLaoDong; // Từ 65 tuổi trở lên
    long tongSo;
}
