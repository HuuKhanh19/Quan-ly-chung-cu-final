package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.request.UserCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.CanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CanHoMapper {
    CanHoResponse toCanHoResponse(CanHo canHo);
}
