package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChiTietDongGopCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.KhoanDongGopCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ChiTietDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.KhoanDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiTuNguyenResponse;
import com.huukhanh19.quan_ly_chung_cu.service.ChiTietDongGopService;
import com.huukhanh19.quan_ly_chung_cu.service.KhoanDongGopService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/khoan-dong-gop")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DongGopController {

    KhoanDongGopService khoanDongGopService;
    ChiTietDongGopService chiTietDongGopService;

    @PostMapping("/create")
    public ApiResponse<KhoanDongGopResponse> createKhoanDongGop(
            @RequestBody @Valid KhoanDongGopCreationRequest request) {
        return ApiResponse.<KhoanDongGopResponse>builder()
                .result(khoanDongGopService.createKhoanDongGop(request))
                .build();
    }

    @PostMapping("/chi-tiet")
    public ApiResponse<ChiTietDongGopResponse> createChiTietDongGop(
            @RequestBody @Valid ChiTietDongGopCreationRequest request) {
        return ApiResponse.<ChiTietDongGopResponse>builder()
                .result(chiTietDongGopService.createChiTietDongGop(request))
                .build();
    }

    @GetMapping("/all")
    public ApiResponse<PhiTuNguyenResponse> getAllPhiTuNguyen() {
        return ApiResponse.<PhiTuNguyenResponse>builder()
                .result(khoanDongGopService.getAllPhiTuNguyen())
                .build();
    }
}