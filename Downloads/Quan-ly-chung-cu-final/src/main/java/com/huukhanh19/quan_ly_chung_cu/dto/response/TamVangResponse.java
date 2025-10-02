package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TamVangResponse {
    private String cccd;
    private String hoVaTen;
    private Integer idCanHo;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String lyDo;
    private String trangThai;
}
