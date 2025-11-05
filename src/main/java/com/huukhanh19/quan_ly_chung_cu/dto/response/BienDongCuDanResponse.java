package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.enums.LoaiBienDong;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BienDongCuDanResponse {
    Integer id;
    String loai; // "Tạm trú", "Tạm vắng", "Thêm nhân khẩu"...
    String text; // "Nguyễn Văn C (Căn hộ 101) vừa đăng ký tạm trú."
    LocalDateTime ngayTao;
}