package com.huukhanh19.quan_ly_chung_cu.dto.request;

import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanKhauUpdateRequest {
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
    String cccdChuHo;
}
