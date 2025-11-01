package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.KhoanDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KhoanDongGopMapper {

    KhoanDongGopResponse toResponse(KhoanDongGop khoanDongGop);
}