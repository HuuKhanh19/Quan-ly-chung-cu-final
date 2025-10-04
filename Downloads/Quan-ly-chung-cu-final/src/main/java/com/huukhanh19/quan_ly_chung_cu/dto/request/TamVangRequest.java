package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TamVangRequest {
    String cccd; // CCCD của người đăng ký

    Integer idCanHo; // Căn hộ mà người này đang ở

    @FutureOrPresent(message = "NGAY_BAT_DAU_INVALID")
    LocalDate ngayBatDau;

    @FutureOrPresent(message = "NGAY_KET_THUC_INVALID")
    LocalDate ngayKetThuc;

    String lyDo;
}
