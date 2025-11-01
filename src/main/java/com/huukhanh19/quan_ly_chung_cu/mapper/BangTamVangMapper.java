package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TamVangRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.UserCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.UserUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TamVangResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.BangTamVang;
import com.huukhanh19.quan_ly_chung_cu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BangTamVangMapper {
    BangTamVang toBangTamVang(TamVangRequest request);

    @Mapping(source = "canHo.idCanHo", target = "idCanHo")
    TamVangResponse toTamVangResponse(BangTamVang bangTamVang);

}
