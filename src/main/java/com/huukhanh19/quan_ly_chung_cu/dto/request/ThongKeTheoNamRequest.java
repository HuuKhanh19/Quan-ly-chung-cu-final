package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ThongKeTheoNamRequest {
    @NotNull(message = "Năm không được để trống")
    @Min(value = 2020, message = "Năm phải từ 2020 trở lên")
    @Max(value = 2100, message = "Năm không hợp lệ")
    Integer nam;
}