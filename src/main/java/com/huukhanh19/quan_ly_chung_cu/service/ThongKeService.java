package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ThongKeTheoNamRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.ThongKeThoiGianRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.*;
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
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Thống kê theo tháng trong năm
     */
    public ThongKeTheoThangResponse thongKeTheoThang(ThongKeTheoNamRequest request) {
        Integer nam = request.getNam();
        log.info("Thống kê theo tháng cho năm: {}", nam);

        // 1. Tính dân số đầu năm và cuối năm
        LocalDate dauNam = LocalDate.of(nam, 1, 1);
        LocalDate cuoiNam = LocalDate.of(nam, 12, 31);

        long danSoDauNam = nhanKhauRepository.countDanSoAtDate(dauNam.minusDays(1));
        long danSoCuoiNam = nhanKhauRepository.countDanSoAtDate(cuoiNam);

        // 2. Tính tổng chuyển đến và chuyển đi trong năm
        long tongChuyenDen = 0;
        long tongChuyenDi = 0;

        // 3. Tạo danh sách chi tiết theo từng tháng
        List<ThongKeCuDanThangResponse> chiTietTheoThang = new ArrayList<>();

        for (int thang = 1; thang <= 12; thang++) {
            LocalDate dauThang = LocalDate.of(nam, thang, 1);
            LocalDate cuoiThang = dauThang.withDayOfMonth(dauThang.lengthOfMonth());

            // Đếm người chuyển đến trong tháng (người mới + tạm trú)
            long chuyenDenThangNay = nhanKhauRepository.countChuyenDenBetween(dauThang, cuoiThang)
                    + bangTamVangRepository.countTamTruBetween(dauThang, cuoiThang);

            // Đếm người chuyển đi trong tháng (tạm vắng)
            long chuyenDiThangNay = bangTamVangRepository.countTamVangBetween(dauThang, cuoiThang);

            // Tính dân số cuối tháng
            long danSoCuoiThang = nhanKhauRepository.countDanSoAtDate(cuoiThang);

            tongChuyenDen += chuyenDenThangNay;
            tongChuyenDi += chuyenDiThangNay;

            ThongKeCuDanThangResponse thangResponse = ThongKeCuDanThangResponse.builder()
                    .thang(thang)
                    .thangDisplay("Tháng " + thang)
                    .soNguoiChuyenDen(chuyenDenThangNay)
                    .soNguoiChuyenDi(chuyenDiThangNay)
                    .tongDanSo(danSoCuoiThang)
                    .build();

            chiTietTheoThang.add(thangResponse);

            log.debug("Tháng {}: Đến={}, Đi={}, Dân số={}",
                    thang, chuyenDenThangNay, chuyenDiThangNay, danSoCuoiThang);
        }

        log.info("Năm {}: Tổng đến={}, Tổng đi={}, Dân số đầu={}, Dân số cuối={}",
                nam, tongChuyenDen, tongChuyenDi, danSoDauNam, danSoCuoiNam);

        return ThongKeTheoThangResponse.builder()
                .nam(nam)
                .tongChuyenDenTrongNam(tongChuyenDen)
                .tongChuyenDiTrongNam(tongChuyenDi)
                .danSoDauNam(danSoDauNam)
                .danSoCuoiNam(danSoCuoiNam)
                .chiTietTheoThang(chiTietTheoThang)
                .build();
    }

    /**
     * Thống kê theo quý trong năm
     */
    public ThongKeTheoQuyResponse thongKeTheoQuy(ThongKeTheoNamRequest request) {
        Integer nam = request.getNam();
        log.info("Thống kê theo quý cho năm: {}", nam);

        // 1. Tính dân số đầu năm và cuối năm
        LocalDate dauNam = LocalDate.of(nam, 1, 1);
        LocalDate cuoiNam = LocalDate.of(nam, 12, 31);

        long danSoDauNam = nhanKhauRepository.countDanSoAtDate(dauNam.minusDays(1));
        long danSoCuoiNam = nhanKhauRepository.countDanSoAtDate(cuoiNam);

        // 2. Tính tổng chuyển đến và chuyển đi trong năm
        long tongChuyenDen = 0;
        long tongChuyenDi = 0;

        // 3. Tạo danh sách chi tiết theo từng quý
        List<ThongKeQuyResponse> chiTietTheoQuy = new ArrayList<>();

        for (int quy = 1; quy <= 4; quy++) {
            // Xác định tháng đầu và tháng cuối của quý
            int thangDau = (quy - 1) * 3 + 1;
            int thangCuoi = quy * 3;

            LocalDate dauQuy = LocalDate.of(nam, thangDau, 1);
            LocalDate cuoiQuy = LocalDate.of(nam, thangCuoi, 1)
                    .withDayOfMonth(LocalDate.of(nam, thangCuoi, 1).lengthOfMonth());

            // Đếm người chuyển đến trong quý (người mới + tạm trú)
            long chuyenDenQuyNay = nhanKhauRepository.countChuyenDenBetween(dauQuy, cuoiQuy)
                    + bangTamVangRepository.countTamTruBetween(dauQuy, cuoiQuy);

            // Đếm người chuyển đi trong quý (tạm vắng)
            long chuyenDiQuyNay = bangTamVangRepository.countTamVangBetween(dauQuy, cuoiQuy);

            // Tính dân số cuối quý
            long danSoCuoiQuy = nhanKhauRepository.countDanSoAtDate(cuoiQuy);

            tongChuyenDen += chuyenDenQuyNay;
            tongChuyenDi += chuyenDiQuyNay;

            ThongKeQuyResponse quyResponse = ThongKeQuyResponse.builder()
                    .quy(quy)
                    .quyDisplay("Quý " + quy)
                    .soNguoiChuyenDen(chuyenDenQuyNay)
                    .soNguoiChuyenDi(chuyenDiQuyNay)
                    .tongDanSo(danSoCuoiQuy)
                    .build();

            chiTietTheoQuy.add(quyResponse);

            log.debug("Quý {}: Đến={}, Đi={}, Dân số={}",
                    quy, chuyenDenQuyNay, chuyenDiQuyNay, danSoCuoiQuy);
        }

        log.info("Năm {}: Tổng đến={}, Tổng đi={}, Dân số đầu={}, Dân số cuối={}",
                nam, tongChuyenDen, tongChuyenDi, danSoDauNam, danSoCuoiNam);

        return ThongKeTheoQuyResponse.builder()
                .nam(nam)
                .tongChuyenDenTrongNam(tongChuyenDen)
                .tongChuyenDiTrongNam(tongChuyenDi)
                .danSoDauNam(danSoDauNam)
                .danSoCuoiNam(danSoCuoiNam)
                .chiTietTheoQuy(chiTietTheoQuy)
                .build();
    }

}
