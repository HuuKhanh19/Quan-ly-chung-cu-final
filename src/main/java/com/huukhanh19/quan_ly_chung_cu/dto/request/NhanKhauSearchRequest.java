package com.huukhanh19.quan_ly_chung_cu.dto.request;

import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanKhauSearchRequest {
    String cccd;
    String cccdChuHo;
    String hoVaTen;
    GioiTinh gioiTinh;
}
