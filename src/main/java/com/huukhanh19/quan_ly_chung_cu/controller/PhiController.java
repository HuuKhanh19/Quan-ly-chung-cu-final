package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.*;
import com.huukhanh19.quan_ly_chung_cu.dto.response.*;
import com.huukhanh19.quan_ly_chung_cu.service.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    CapNhatThanhToanService capNhatThanhToanService;

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

    @GetMapping("/phi-bat-buoc/{idThoiGianThu}")
    public ApiResponse<PhiBatBuocResponse> getPhiBatBuoc(@PathVariable Integer idThoiGianThu) {
        return ApiResponse.<PhiBatBuocResponse>builder()
                .result(tongThanhToanService.getPhiBatBuocByThoiGianThu(idThoiGianThu))
                .build();
    }

    @PostMapping("/thanh-toan/{idThoiGianThu}")
    public ApiResponse<PhiBatBuocResponse> capNhatThanhToan(
            @PathVariable Integer idThoiGianThu,
            @RequestBody @Valid CapNhatThanhToanRequest request) {
        return ApiResponse.<PhiBatBuocResponse>builder()
                .result(capNhatThanhToanService.capNhatThanhToan(idThoiGianThu, request))
                .build();
    }
}