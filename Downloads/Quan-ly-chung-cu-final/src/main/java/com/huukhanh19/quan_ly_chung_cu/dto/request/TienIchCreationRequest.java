package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TienIchCreationRequest {
    @NotNull(message = "ID Căn hộ không được để trống")
    Integer idCanHo;

    @NotNull(message = "ID Kỳ thu phí không được để trống")
    Integer idThoiGianThu;

    @NotNull(message = "Tiền điện không được để trống")
    Integer tienDien;

    @NotNull(message = "Tiền nước không được để trống")
    Integer tienNuoc;

    @NotNull(message = "Tiền internet không được để trống")
    Integer tienInternet;
}
