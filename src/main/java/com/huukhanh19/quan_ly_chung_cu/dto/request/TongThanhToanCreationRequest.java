package com.huukhanh19.quan_ly_chung_cu.dto.request;

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
public class TongThanhToanCreationRequest {
    @NotNull(message = "ID thời gian thu không được để trống")
    Integer idThoiGianThu;
}