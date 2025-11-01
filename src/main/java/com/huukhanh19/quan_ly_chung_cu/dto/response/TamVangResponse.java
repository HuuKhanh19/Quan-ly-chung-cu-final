package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.enums.LoaiDangKy;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TamVangResponse {
    String cccd;
    String hoVaTen;
    Integer idCanHo;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;
    String lyDo;
    String trangThai;
    LoaiDangKy loaiDangKy;
}
