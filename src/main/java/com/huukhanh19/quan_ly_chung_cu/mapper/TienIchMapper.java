package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TienIchCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TienIchResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import com.huukhanh19.quan_ly_chung_cu.entity.TienIch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TienIchMapper {

    @Mapping(target = "id", source = "monthlyFeeId")
    @Mapping(target = "canHo", source = "canHo")
    @Mapping(target = "thoiGianThuPhi", source = "thoiGianThuPhi")
    @Mapping(target = "tienDien", source = "request.tienDien")
    @Mapping(target = "tienNuoc", source = "request.tienNuoc")
    @Mapping(target = "tienInternet", source = "request.tienInternet")
    @Mapping(target = "tongTienIch", source = "tongTienIch")
    TienIch toEntity(TienIchCreationRequest request,
                     MonthlyFeeId monthlyFeeId,
                     CanHo canHo,
                     ThoiGianThuPhi thoiGianThuPhi,
                     Integer tongTienIch);

    @Mapping(target = "idCanHo", source = "id.idCanHo")
    @Mapping(target = "idThoiGianThu", source = "id.idThoiGianThu")
    TienIchResponse toResponse(TienIch tienIch);
}
