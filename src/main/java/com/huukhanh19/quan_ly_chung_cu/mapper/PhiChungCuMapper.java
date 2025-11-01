package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiChungCuResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiChungCu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhiChungCuMapper {

    @Mapping(target = "idCanHo", source = "id.idCanHo")
    @Mapping(target = "idThoiGianThu", source = "id.idThoiGianThu")
    @Mapping(target = "soNha", source = "canHo.soNha")
    @Mapping(target = "dienTich", source = "canHo.dienTich")
    PhiChungCuResponse toResponse(PhiChungCu phiChungCu);
}