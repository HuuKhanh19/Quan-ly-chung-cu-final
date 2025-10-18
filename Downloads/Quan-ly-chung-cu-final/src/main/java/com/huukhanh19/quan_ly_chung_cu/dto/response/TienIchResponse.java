package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TienIchResponse {
    Integer idCanHo;
    Integer idThoiGianThu;
    Integer tienDien;
    Integer tienNuoc;
    Integer tienInternet;
    Integer tongTienIch;
}
