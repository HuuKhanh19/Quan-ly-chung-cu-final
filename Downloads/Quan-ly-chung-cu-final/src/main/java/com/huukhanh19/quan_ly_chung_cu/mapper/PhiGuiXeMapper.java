package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiGuiXeResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiGuiXe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PhiGuiXeMapper {

    @Mapping(target = "idCanHo", source = "id.idCanHo")
    @Mapping(target = "idThoiGianThu", source = "id.idThoiGianThu")
    @Mapping(target = "soNha", source = "canHo.soNha")
    @Mapping(target = "soXeMay", source = "phiGuiXe", qualifiedByName = "getSoXeMay")
    @Mapping(target = "soOto", source = "phiGuiXe", qualifiedByName = "getSoOto")
    PhiGuiXeResponse toResponse(PhiGuiXe phiGuiXe);

    @Named("getSoXeMay")
    default Integer getSoXeMay(PhiGuiXe phiGuiXe) {
        if (phiGuiXe.getCanHo() != null &&
                phiGuiXe.getCanHo().getHoGiaDinh() != null &&
                phiGuiXe.getCanHo().getHoGiaDinh().getSoXeMay() != null) {
            return phiGuiXe.getCanHo().getHoGiaDinh().getSoXeMay();
        }
        return 0;
    }

    @Named("getSoOto")
    default Integer getSoOto(PhiGuiXe phiGuiXe) {
        if (phiGuiXe.getCanHo() != null &&
                phiGuiXe.getCanHo().getHoGiaDinh() != null &&
                phiGuiXe.getCanHo().getHoGiaDinh().getSoOto() != null) {
            return phiGuiXe.getCanHo().getHoGiaDinh().getSoOto();
        }
        return 0;
    }
}