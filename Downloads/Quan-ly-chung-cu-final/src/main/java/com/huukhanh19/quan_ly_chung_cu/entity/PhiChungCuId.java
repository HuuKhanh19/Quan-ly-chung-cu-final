package com.huukhanh19.quan_ly_chung_cu.entity;

import com.huukhanh19.quan_ly_chung_cu.enums.Role;
import jakarta.persistence.*;
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
public class PhiChungCuId implements Serializable {
    Integer idCanHo;
    String hanThu;
}
