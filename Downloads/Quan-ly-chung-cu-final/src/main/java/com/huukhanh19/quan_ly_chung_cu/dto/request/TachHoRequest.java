package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TachHoRequest {
    String cccdChuHoMoi;
    Integer idCanHoMoi;
    List<String> danhSachCccdThanhVienChuyenDi;
    Integer soXeMay;
    Integer soOto;
}