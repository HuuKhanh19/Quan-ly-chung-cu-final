package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.Min;
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
public class ChiTietDongGopCreationRequest {
    @NotNull(message = "ID khoản đóng góp không được để trống")
    Integer idKhoanDongGop;

    @NotNull(message = "ID căn hộ không được để trống")
    Integer idCanHo;

    @NotNull(message = "Số tiền đóng góp không được để trống")
    @Min(value = 1000, message = "Số tiền đóng góp tối thiểu 1.000 đồng")
    Integer soTienDongGop;

    @NotNull(message = "Ngày đóng góp không được để trống")
    LocalDate ngayDongGop;

    String hinhThucThanhToan = "Tiền mặt";

    String nguoiNhan;

    String ghiChu;
}