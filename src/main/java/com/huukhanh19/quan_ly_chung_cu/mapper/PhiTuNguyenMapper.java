package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiTuNguyenDetailResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhiTuNguyenMapper {

    @Mapping(target = "idKhoanDongGop", source = "idKhoanDongGop")
    @Mapping(target = "tenKhoanDongGop", source = "tenKhoanDongGop")
    @Mapping(target = "ngayThu", source = "ngayBatDauThu")
    @Mapping(target = "hanThu", source = "hanDongGop")
    @Mapping(target = "tongTienThuDuoc", source = "tongTienThuDuoc")
    @Mapping(target = "soCanHoDongGop", source = "soCanHoDongGop")
    @Mapping(target = "danhSachCanHoDaDongGop", ignore = true)
    PhiTuNguyenDetailResponse toDetailResponse(KhoanDongGop khoanDongGop);
}