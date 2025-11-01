package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.CanHoDaDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CanHoDaDongGopMapper {

    @Mapping(target = "idCanHo", source = "canHo.idCanHo")
    @Mapping(target = "soTienDongGop", source = "soTienDongGop")
    @Mapping(target = "ngayNop", source = "ngayDongGop")
    CanHoDaDongGopResponse toResponse(ChiTietDongGop chiTietDongGop);
}