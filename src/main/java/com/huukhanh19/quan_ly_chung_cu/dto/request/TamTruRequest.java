package com.huukhanh19.quan_ly_chung_cu.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TamTruRequest {
    String cccd; // CCCD của người đến tạm trú
    String hoVaTen; // Họ tên của người đến tạm trú
    Integer idCanHo; // Căn hộ mà họ sẽ ở
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    String lyDo;
}
