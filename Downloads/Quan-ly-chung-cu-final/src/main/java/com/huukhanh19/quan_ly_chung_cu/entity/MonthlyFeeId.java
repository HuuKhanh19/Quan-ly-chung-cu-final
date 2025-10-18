package com.huukhanh19.quan_ly_chung_cu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyFeeId implements Serializable {
    @Column(name = "id_can_ho")
    Integer idCanHo;

    @Column(name = "han_thu")
    LocalDate hanThu;
}