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
    @NotBlank(message = "CCCD_CHU_HO_MOI_MUST_NOT_BE_BLANK")
    private String cccdChuHoMoi; // CCCD của người sẽ làm chủ hộ mới

    @NotNull(message = "ID_CAN_HO_MOI_MUST_NOT_BE_BLANK")
    private Integer idCanHoMoi; // ID của căn hộ mới mà hộ này sẽ chuyển đến

    // Danh sách CCCD của các thành viên khác sẽ chuyển đi cùng chủ hộ mới
    private List<String> danhSachCccdThanhVienChuyenDi;
}