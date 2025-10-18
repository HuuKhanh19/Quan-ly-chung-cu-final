package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhiChungCuCreationRequest {
    @NotNull(message = "ID thời gian thu không được để trống")
    Integer idThoiGianThu;

    @NotNull(message = "Phí dịch vụ không được để trống")
    @Min(value = 2500, message = "Phí dịch vụ tối thiểu 2.500 đồng/m2")
    @Max(value = 16500, message = "Phí dịch vụ tối đa 16.500 đồng/m2")
    Integer phiDichVuPerM2 = 7000; // Mặc định 7.000 đồng/m2

    @NotNull(message = "Phí quản lý không được để trống")
    Integer phiQuanLyPerM2 = 7000; // Mặc định 7.000 đồng/m2 cho BlueMoon
}
