package com.huukhanh19.quan_ly_chung_cu.dto.request;

import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanKhauCreationRequest {
    @Size(min = 12, max = 12, message = "CCCD_INVALID")
    String cccd;

    String cccdChuHo;
    String hoVaTen;
    GioiTinh gioiTinh;
    LocalDate ngaySinh;
    String danToc;
    String tonGiao;
    String quocTich;
    String diaChi;
    String sdt;
    String email;
    String quanHe;
    String trangThai;
}
