package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.KhoanDongGopCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.KhoanDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.service.KhoanDongGopService;
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
@RequestMapping("/khoan-dong-gop")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DongGopController {

    KhoanDongGopService khoanDongGopService;

    @PostMapping
    public ApiResponse<KhoanDongGopResponse> createKhoanDongGop(
            @RequestBody @Valid KhoanDongGopCreationRequest request) {
        return ApiResponse.<KhoanDongGopResponse>builder()
                .result(khoanDongGopService.createKhoanDongGop(request))
                .build();
    }
}