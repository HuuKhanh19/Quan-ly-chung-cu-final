package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.PhiChungCuCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.PhiGuiXeCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TienIchCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TongThanhToanCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.*;
import com.huukhanh19.quan_ly_chung_cu.service.PhiChungCuService;
import com.huukhanh19.quan_ly_chung_cu.service.PhiGuiXeService;
import com.huukhanh19.quan_ly_chung_cu.service.TienIchService;
import com.huukhanh19.quan_ly_chung_cu.service.TongThanhToanService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhiController {
    TienIchService tienIchService;
    PhiChungCuService phiChungCuService;
    PhiGuiXeService phiGuiXeService;
    TongThanhToanService tongThanhToanService;

    // Trong file PhiController.java
    @PostMapping("/tien-ich")
    public ApiResponse<TienIchResponse> createTienIch(@RequestBody @Valid TienIchCreationRequest request) {
        return ApiResponse.<TienIchResponse>builder()
                .result(tienIchService.createTienIch(request))
                .build();
    }

    @PostMapping("/phi-chung-cu/batch")
    public ApiResponse<PhiChungCuBatchResponse> createPhiChungCuForAllCanHo(
            @RequestBody @Valid PhiChungCuCreationRequest request) {
        return ApiResponse.<PhiChungCuBatchResponse>builder()
                .result(phiChungCuService.createPhiChungCuForAllCanHo(request))
                .build();
    }

    @PostMapping("/phi-gui-xe/batch")
    public ApiResponse<PhiGuiXeBatchResponse> createPhiGuiXeForAllCanHo(
            @RequestBody @Valid PhiGuiXeCreationRequest request) {
        return ApiResponse.<PhiGuiXeBatchResponse>builder()
                .result(phiGuiXeService.createPhiGuiXeForAllCanHo(request))
                .build();
    }

    @PostMapping("/tong-thanh-toan/batch")
    public ApiResponse<TongThanhToanBatchResponse> createTongThanhToanForAllCanHo(
            @RequestBody @Valid TongThanhToanCreationRequest request) {
        return ApiResponse.<TongThanhToanBatchResponse>builder()
                .result(tongThanhToanService.createTongThanhToanForAllCanHo(request))
                .build();
    }
}