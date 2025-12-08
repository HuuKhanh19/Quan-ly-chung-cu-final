package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ThongKeThoiGianRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeDoTuoiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeGioiTinhResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeTamVangResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeThoiGianResponse;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.enums.LoaiDangKy;
import com.huukhanh19.quan_ly_chung_cu.repository.BangTamVangRepository;
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
    BangTamVangRepository bangTamVangRepository;

    public ThongKeGioiTinhResponse thongKeTheoGioiTinh() {
        // 1. Gọi các phương thức đếm từ repository
        long soLuongNam = nhanKhauRepository.countByGioiTinh(GioiTinh.NAM);
        long soLuongNu = nhanKhauRepository.countByGioiTinh(GioiTinh.NU);
        long soLuongKhac = nhanKhauRepository.countByGioiTinh(GioiTinh.KHAC);

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

    public ThongKeThoiGianResponse thongKeNhanKhauTheoThoiGian(ThongKeThoiGianRequest request) {
        // 1. Validate: Đảm bảo ngày bắt đầu không sau ngày kết thúc
        if (request.getNgayBatDau().isAfter(request.getNgayKetThuc())) {
            throw new RuntimeException("Ngày bắt đầu không được sau ngày kết thúc.");
        }

        // 2. Gọi phương thức đếm từ repository
        long soLuong = nhanKhauRepository.countByNgayTaoBetween(
                request.getNgayBatDau(),
                request.getNgayKetThuc()
        );

        // 3. Xây dựng và trả về đối tượng response
        return ThongKeThoiGianResponse.builder()
                .soLuongNhanKhauMoi(soLuong)
                .build();
    }

    public ThongKeTamVangResponse thongKeTamTruTamVang() {
        // 1. Thống kê theo Loại đăng ký trong bảng bang_tam_vang
        long soLuongTamTru = bangTamVangRepository.countByLoaiDangKy(LoaiDangKy.TAM_TRU);
        long soLuongTamVang = bangTamVangRepository.countByLoaiDangKy(LoaiDangKy.TAM_VANG);

        // 2. Thống kê nhân khẩu thường trú (từ bảng nhan_khau)
        long soLuongThuongTru = nhanKhauRepository.count() - soLuongTamVang;

        // 3. Tính tổng
        long tongSo = soLuongThuongTru + soLuongTamTru + soLuongTamVang;

        log.info("Thống kê: Thường trú={}, Tạm trú={}, Tạm vắng={}, Tổng={}",
                soLuongThuongTru, soLuongTamTru, soLuongTamVang, tongSo);

        // 4. Xây dựng và trả về đối tượng response
        return ThongKeTamVangResponse.builder()
                .soLuongThuongTru(soLuongThuongTru)
                .soLuongTamTru(soLuongTamTru)
                .soLuongTamVang(soLuongTamVang)
                .tongSo(tongSo)
                .build();
    }

}
