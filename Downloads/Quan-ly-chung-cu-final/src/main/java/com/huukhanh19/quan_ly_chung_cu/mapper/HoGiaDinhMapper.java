package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HoGiaDinhMapper {
    @Mapping(source = "nhanKhauMoi.cccd", target = "cccdChuHo")
    @Mapping(source = "nhanKhauMoi.hoVaTen", target = "hoTenChuHo")
    @Mapping(source = "nhanKhauMoi.ngaySinh", target = "ngaySinh")
    @Mapping(source = "nhanKhauMoi.gioiTinh", target = "gioiTinh")
    @Mapping(source = "nhanKhauMoi.danToc", target = "danToc")
    @Mapping(source = "nhanKhauMoi.tonGiao", target = "tonGiao")
    @Mapping(source = "nhanKhauMoi.quocTich", target = "quocTich")
    @Mapping(source = "nhanKhauMoi.diaChi", target = "diaChi")
    @Mapping(source = "nhanKhauMoi.sdt", target = "sdt")
    @Mapping(source = "nhanKhauMoi.email", target = "email")
    @Mapping(source = "hoGiaDinhCu.canHo", target = "canHo")
    @Mapping(source = "hoGiaDinhCu.soThanhVien", target = "soThanhVien")
    @Mapping(source = "hoGiaDinhCu.tang", target = "tang")
    @Mapping(source = "hoGiaDinhCu.soXeMay", target = "soXeMay")
    @Mapping(source = "hoGiaDinhCu.soOto", target = "soOto")
    @Mapping(source = "hoGiaDinhCu.trangThai", target = "trangThai")
    HoGiaDinh createNewHoGiaDinh(HoGiaDinh hoGiaDinhCu, NhanKhau nhanKhauMoi);
}
