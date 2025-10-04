package com.huukhanh19.quan_ly_chung_cu.dto.response;

import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CanHoResponse {
    Integer idCanHo;
    String soNha;
    String loaiCanHo;
    BigDecimal dienTich;
    String diaChi;
}
