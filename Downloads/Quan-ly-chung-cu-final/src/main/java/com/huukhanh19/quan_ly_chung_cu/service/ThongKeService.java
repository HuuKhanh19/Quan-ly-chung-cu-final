package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeDoTuoiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeGioiTinhResponse;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@PreAuthorize("hasRole('QUANLY')")
public class ThongKeService {
    NhanKhauRepository nhanKhauRepository;

    public ThongKeGioiTinhResponse thongKeTheoGioiTinh() {
        // 1. Gọi các phương thức đếm từ repository
        long soLuongNam = nhanKhauRepository.countByGioiTinh(GioiTinh.Nam);
        long soLuongNu = nhanKhauRepository.countByGioiTinh(GioiTinh.Nữ);
        long soLuongKhac = nhanKhauRepository.countByGioiTinh(GioiTinh.Khác);

        // 2. Tính tổng
        long tongSo = soLuongNam + soLuongNu + soLuongKhac;

        // 3. Xây dựng và trả về đối tượng response
        return ThongKeGioiTinhResponse.builder()
                .soLuongNam(soLuongNam)
                .soLuongNu(soLuongNu)
                .soLuongKhac(soLuongKhac)
                .tongSo(tongSo)
                .build();
    }

    public ThongKeDoTuoiResponse thongKeTheoDoTuoi() {
        LocalDate today = LocalDate.now();

        // 1. Tính toán các mốc ngày sinh
        LocalDate ngayBatDauLaoDong = today.minusYears(65); // Người sinh trước ngày này là trên 65 tuổi
        LocalDate ngayKetThucViThanhNien = today.minusYears(15); // Người sinh sau ngày này là dưới 15 tuổi

        // 2. Gọi các phương thức đếm từ repository
        long trenTuoiLaoDong = nhanKhauRepository.countByNgaySinhBefore(ngayBatDauLaoDong);

        long duoiViThanhNien = nhanKhauRepository.countByNgaySinhAfter(ngayKetThucViThanhNien);

        long trongDoTuoiLaoDong = nhanKhauRepository.countByNgaySinhBetween(ngayBatDauLaoDong, ngayKetThucViThanhNien);

        // 3. Xây dựng và trả về đối tượng response
        return ThongKeDoTuoiResponse.builder()
                .duoiViThanhNien(duoiViThanhNien)
                .trongDoTuoiLaoDong(trongDoTuoiLaoDong)
                .trenTuoiLaoDong(trenTuoiLaoDong)
                .tongSo(nhanKhauRepository.count()) // Lấy tổng số từ repository
                .build();
    }
}
