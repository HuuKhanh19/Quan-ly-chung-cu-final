package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhiGuiXeCreationRequest {
    @NotNull(message = "ID thời gian thu không được để trống")
    Integer idThoiGianThu;

    @NotNull(message = "Tiền gửi xe máy không được để trống")
    @Min(value = 0, message = "Tiền gửi xe máy phải lớn hơn hoặc bằng 0")
    Integer tienXeMayPerXe = 70000; // Mặc định 70.000 đồng/xe máy/tháng

    @NotNull(message = "Tiền gửi ô tô không được để trống")
    @Min(value = 0, message = "Tiền gửi ô tô phải lớn hơn hoặc bằng 0")
    Integer tienXeOtoPerXe = 1200000; // Mặc định 1.200.000 đồng/ô tô/tháng
}