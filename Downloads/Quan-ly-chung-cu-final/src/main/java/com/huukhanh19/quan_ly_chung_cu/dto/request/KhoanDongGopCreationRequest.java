package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KhoanDongGopCreationRequest {
    @NotBlank(message = "Tên khoản đóng góp không được để trống")
    String tenKhoanDongGop;

    @NotNull(message = "Ngày bắt đầu thu không được để trống")
    LocalDate ngayBatDauThu;

    @NotNull(message = "Hạn đóng góp không được để trống")
    LocalDate hanDongGop;
}