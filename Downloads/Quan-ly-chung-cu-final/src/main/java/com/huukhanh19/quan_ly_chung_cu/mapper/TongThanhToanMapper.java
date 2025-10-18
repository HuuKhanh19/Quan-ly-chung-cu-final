package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.TongThanhToanDetailResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TongThanhToanMapper {

    @Mapping(target = "idCanHo", source = "tongThanhToan.id.idCanHo")

    // Phí chung cư
    @Mapping(target = "phiDichVu", source = "phiChungCu", qualifiedByName = "getPhiDichVu")
    @Mapping(target = "phiQuanLy", source = "phiChungCu", qualifiedByName = "getPhiQuanLy")
    @Mapping(target = "tongPhiChungCu", source = "tongThanhToan.tongPhiChungCu")

    // Tiện ích
    @Mapping(target = "tienDien", source = "tienIch", qualifiedByName = "getTienDien")
    @Mapping(target = "tienNuoc", source = "tienIch", qualifiedByName = "getTienNuoc")
    @Mapping(target = "tienInternet", source = "tienIch", qualifiedByName = "getTienInternet")
    @Mapping(target = "tongTienIch", source = "tongThanhToan.tongTienIch")

    // Gửi xe
    @Mapping(target = "tienXeMay", source = "phiGuiXe", qualifiedByName = "getTienXeMay")
    @Mapping(target = "tienXeOto", source = "phiGuiXe", qualifiedByName = "getTienXeOto")
    @Mapping(target = "tongGuiXe", source = "tongThanhToan.tongGuiXe")

    // Tổng
    @Mapping(target = "tongPhi", source = "tongThanhToan.tongPhi")
    @Mapping(target = "soTienDaNop", source = "tongThanhToan.soTienDaNop")
    @Mapping(target = "soDu", source = "tongThanhToan.soDu")
    @Mapping(target = "trangThai", source = "tongThanhToan.trangThai")
    TongThanhToanDetailResponse toDetailResponse(TongThanhToan tongThanhToan,
                                                 PhiChungCu phiChungCu,
                                                 TienIch tienIch,
                                                 PhiGuiXe phiGuiXe);

    // Helper methods cho PhiChungCu
    @Named("getPhiDichVu")
    default Integer getPhiDichVu(PhiChungCu phiChungCu) {
        return phiChungCu != null ? phiChungCu.getPhiDichVu() : 0;
    }

    @Named("getPhiQuanLy")
    default Integer getPhiQuanLy(PhiChungCu phiChungCu) {
        return phiChungCu != null ? phiChungCu.getPhiQuanLy() : 0;
    }

    // Helper methods cho TienIch
    @Named("getTienDien")
    default Integer getTienDien(TienIch tienIch) {
        return tienIch != null ? tienIch.getTienDien() : 0;
    }

    @Named("getTienNuoc")
    default Integer getTienNuoc(TienIch tienIch) {
        return tienIch != null ? tienIch.getTienNuoc() : 0;
    }

    @Named("getTienInternet")
    default Integer getTienInternet(TienIch tienIch) {
        return tienIch != null ? tienIch.getTienInternet() : 0;
    }

    // Helper methods cho PhiGuiXe
    @Named("getTienXeMay")
    default Integer getTienXeMay(PhiGuiXe phiGuiXe) {
        return phiGuiXe != null ? phiGuiXe.getTienXeMay() : 0;
    }

    @Named("getTienXeOto")
    default Integer getTienXeOto(PhiGuiXe phiGuiXe) {
        return phiGuiXe != null ? phiGuiXe.getTienXeOto() : 0;
    }

    // Helper methods cho số xe
    @Named("getSoXeMay")
    default Integer getSoXeMay(TongThanhToan tongThanhToan) {
        HoGiaDinh hoGiaDinh = tongThanhToan.getCanHo().getHoGiaDinh();
        return (hoGiaDinh != null && hoGiaDinh.getSoXeMay() != null) ? hoGiaDinh.getSoXeMay() : 0;
    }

    @Named("getSoOto")
    default Integer getSoOto(TongThanhToan tongThanhToan) {
        HoGiaDinh hoGiaDinh = tongThanhToan.getCanHo().getHoGiaDinh();
        return (hoGiaDinh != null && hoGiaDinh.getSoOto() != null) ? hoGiaDinh.getSoOto() : 0;
    }
}