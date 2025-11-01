package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ThongKeMapper {

    @Mapping(target = "idCanHo", source = "id.idCanHo")
    @Mapping(target = "soNha", source = "canHo.soNha")
    ThongKeCanHoResponse toThongKeCanHoResponse(TongThanhToan tongThanhToan);

}