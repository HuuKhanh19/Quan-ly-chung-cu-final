package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.request.UserCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiChungCuResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiChungCu;
import com.huukhanh19.quan_ly_chung_cu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhiChungCuMapper {
    @Mapping(source = "canHo.idCanHo", target = "idCanHo")
    @Mapping(source = "id.hanThu", target = "hanThu")
    PhiChungCuResponse toPhiChungCuResponse(PhiChungCu phiChungCu);
}