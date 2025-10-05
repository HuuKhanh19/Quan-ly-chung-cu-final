package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanKhauResponse {
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
    LocalDate ngayTao;
}
