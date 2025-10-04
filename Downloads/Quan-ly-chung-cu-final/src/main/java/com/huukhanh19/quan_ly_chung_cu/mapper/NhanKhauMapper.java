package com.huukhanh19.quan_ly_chung_cu.mapper;

import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.UserCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.UserUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NhanKhauMapper {
    NhanKhau toNhanKhau(NhanKhauCreationRequest request);

    @Mapping(source = "hoGiaDinh.cccdChuHo", target = "cccdChuHo")
    NhanKhauResponse toNhanKhauResponse(NhanKhau nhanKhau);
    List<NhanKhauResponse> toNhanKhauResponseList(List<NhanKhau> nhanKhaus);

    @Mapping(target = "hoGiaDinh", ignore = true)
    void updateNhanKhau(@MappingTarget NhanKhau nhanKhau, NhanKhauUpdateRequest request);

    @Mapping(source = "hoGiaDinhCu.cccdChuHo", target = "cccd")
    @Mapping(source = "hoGiaDinhCu.hoTenChuHo", target = "hoVaTen")
    @Mapping(source = "hoGiaDinhCu.ngaySinh", target = "ngaySinh")
    @Mapping(source = "hoGiaDinhCu.gioiTinh", target = "gioiTinh")
    @Mapping(source = "hoGiaDinhCu.danToc", target = "danToc")
    @Mapping(source = "hoGiaDinhCu.tonGiao", target = "tonGiao")
    @Mapping(source = "hoGiaDinhCu.quocTich", target = "quocTich")
    @Mapping(source = "hoGiaDinhCu.diaChi", target = "diaChi")
    @Mapping(source = "hoGiaDinhCu.sdt", target = "sdt")
    @Mapping(source = "hoGiaDinhCu.email", target = "email")
    @Mapping(source = "hoGiaDinhCu.trangThai", target = "trangThai")
    @Mapping(source = "quanHeMoi", target = "quanHe")
    @Mapping(source = "hoGiaDinhMoi", target = "hoGiaDinh")
    NhanKhau createNhanKhauFromHoGiaDinh(HoGiaDinh hoGiaDinhCu, HoGiaDinh hoGiaDinhMoi, String quanHeMoi);
}
