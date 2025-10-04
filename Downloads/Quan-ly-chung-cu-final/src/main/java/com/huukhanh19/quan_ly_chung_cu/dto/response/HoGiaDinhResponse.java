package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoGiaDinhResponse {
    String cccdChuHo;
    Integer idCanHo;
    Integer soThanhVien;
    String hoTenChuHo;
    GioiTinh gioiTinh;
    LocalDate ngaySinh;
    String danToc;
    String tonGiao;
    String quocTich;
    String diaChi;
    String sdt;
    String email;
    Integer soXeMay;
    Integer soOto;
    String trangThai;
}
