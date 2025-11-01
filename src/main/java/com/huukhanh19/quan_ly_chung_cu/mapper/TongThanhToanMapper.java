package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.TongThanhToanResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TongThanhToanMapper {

    @Mapping(target = "idCanHo", source = "id.idCanHo")
    @Mapping(target = "idThoiGianThu", source = "id.idThoiGianThu")
    @Mapping(target = "soNha", source = "canHo.soNha")
    TongThanhToanResponse toResponse(TongThanhToan tongThanhToan);
}