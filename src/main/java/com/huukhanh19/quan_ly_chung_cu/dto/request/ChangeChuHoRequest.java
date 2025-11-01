package com.huukhanh19.quan_ly_chung_cu.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeChuHoRequest {
    String cccdNhanKhauMoi; // CCCD của thành viên sẽ làm chủ hộ mới
}
