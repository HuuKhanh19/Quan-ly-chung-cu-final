package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTheoThangResponse {
    Integer nam;
    Long tongChuyenDenTrongNam;
    Long tongChuyenDiTrongNam;
    Long danSoDauNam;
    Long danSoCuoiNam;
    List<ThongKeCuDanThangResponse> chiTietTheoThang;
}