package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ChiTietDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChiTietDongGopMapper {

    @Mapping(target = "idKhoanDongGop", source = "khoanDongGop.idKhoanDongGop")
    @Mapping(target = "tenKhoanDongGop", source = "khoanDongGop.tenKhoanDongGop")
    @Mapping(target = "idCanHo", source = "canHo.idCanHo")
    @Mapping(target = "soNha", source = "canHo.soNha")
    ChiTietDongGopResponse toResponse(ChiTietDongGop chiTietDongGop);
}