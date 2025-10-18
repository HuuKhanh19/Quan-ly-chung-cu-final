package com.huukhanh19.quan_ly_chung_cu.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhiChungCuCreationRequest {
    @NotNull(message = "ID Căn hộ không được để trống")
    private Integer idCanHo;
}
