package com.huukhanh19.quan_ly_chung_cu.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeThoiGianRequest {
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
}
