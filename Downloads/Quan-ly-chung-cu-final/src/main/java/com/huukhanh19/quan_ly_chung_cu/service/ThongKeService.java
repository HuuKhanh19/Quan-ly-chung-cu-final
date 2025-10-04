package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeGioiTinhResponse;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

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
}
