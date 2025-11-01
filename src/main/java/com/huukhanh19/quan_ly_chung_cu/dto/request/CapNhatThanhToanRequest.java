package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CapNhatThanhToanRequest {
    @NotEmpty(message = "Danh sách căn hộ không được để trống")
    List<Integer> danhSachIdCanHo;
}