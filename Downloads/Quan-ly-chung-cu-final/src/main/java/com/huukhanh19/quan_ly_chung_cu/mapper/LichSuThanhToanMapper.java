package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.DongGopCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiBatBuocCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LichSuThanhToanMapper {

    @Mapping(target = "idThoiGianThu", source = "thoiGianThuPhi.id")
    @Mapping(target = "ngayThu", source = "thoiGianThuPhi.ngayThu")
    @Mapping(target = "hanThu", source = "thoiGianThuPhi.hanThu")
    @Mapping(target = "phiChungCu", source = "tongPhiChungCu")
    @Mapping(target = "tienIch", source = "tongTienIch")
    @Mapping(target = "guiXe", source = "tongGuiXe")
    PhiBatBuocCanHoResponse toPhiBatBuocCanHoResponse(TongThanhToan tongThanhToan);

    @Mapping(target = "idKhoanDongGop", source = "khoanDongGop.idKhoanDongGop")
    @Mapping(target = "tenKhoanDongGop", source = "khoanDongGop.tenKhoanDongGop")
    @Mapping(target = "ngayBatDauThu", source = "khoanDongGop.ngayBatDauThu")
    @Mapping(target = "hanDongGop", source = "khoanDongGop.hanDongGop")
    DongGopCanHoResponse toDongGopCanHoResponse(ChiTietDongGop chiTietDongGop);
}