package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ThongKeTheoNamRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.ThongKeThoiGianRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.*;
import com.huukhanh19.quan_ly_chung_cu.service.ThongKeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thong-ke")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ThongKeController {
    ThongKeService thongKeService;

    @GetMapping("/gioi-tinh")
    public ApiResponse<ThongKeGioiTinhResponse> thongKeTheoGioiTinh() {
        return ApiResponse.<ThongKeGioiTinhResponse>builder()
                .result(thongKeService.thongKeTheoGioiTinh())
                .build();
    }

    @GetMapping("/do-tuoi")
    public ApiResponse<ThongKeDoTuoiResponse> thongKeTheoDoTuoi() {
        return ApiResponse.<ThongKeDoTuoiResponse>builder()
                .result(thongKeService.thongKeTheoDoTuoi())
                .build();
    }

    @PostMapping("/khoang-thoi-gian")
    public ApiResponse<ThongKeThoiGianResponse> thongKeNhanKhauTheoThoiGian(
            @RequestBody @Valid ThongKeThoiGianRequest request) {
        return ApiResponse.<ThongKeThoiGianResponse>builder()
                .result(thongKeService.thongKeNhanKhauTheoThoiGian(request))
                .build();
    }

    @GetMapping("/tam-tru-tam-vang")
    public ApiResponse<ThongKeTamVangResponse> thongKeTamTruTamVang() {
        return ApiResponse.<ThongKeTamVangResponse>builder()
                .result(thongKeService.thongKeTamTruTamVang())
                .build();
    }

    // Thêm các endpoints này vào ThongKeController.java

    /**
     * Thống kê theo tháng trong năm
     * GET /thong-ke/theo-thang?nam=2024
     */
    @PostMapping("/theo-thang")
    public ApiResponse<ThongKeTheoThangResponse> thongKeTheoThang(
            @RequestBody @Valid ThongKeTheoNamRequest request) {
        log.info("API thống kê theo tháng cho năm: {}", request.getNam());
        return ApiResponse.<ThongKeTheoThangResponse>builder()
                .result(thongKeService.thongKeTheoThang(request))
                .build();
    }

    /**
     * Thống kê theo quý trong năm
     * GET /thong-ke/theo-quy?nam=2024
     */
    @PostMapping("/theo-quy")
    public ApiResponse<ThongKeTheoQuyResponse> thongKeTheoQuy(
            @RequestBody @Valid ThongKeTheoNamRequest request) {
        log.info("API thống kê theo quý cho năm: {}", request.getNam());
        return ApiResponse.<ThongKeTheoQuyResponse>builder()
                .result(thongKeService.thongKeTheoQuy(request))
                .build();
    }

}
