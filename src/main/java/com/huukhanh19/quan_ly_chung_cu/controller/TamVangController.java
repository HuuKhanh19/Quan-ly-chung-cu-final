package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TamTruRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TamVangRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TamVangResponse;
import com.huukhanh19.quan_ly_chung_cu.service.NhanKhauService;
import com.huukhanh19.quan_ly_chung_cu.service.TamVangService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TamVangController {
    TamVangService tamVangService;

    @PostMapping("/tam-vang")
    public ApiResponse<TamVangResponse> createTamVang(@RequestBody @Valid TamVangRequest request) {
        return ApiResponse.<TamVangResponse>builder()
                .result(tamVangService.createTamVang(request))
                .build();
    }

    @PostMapping("/tam-tru")
    public ApiResponse<TamVangResponse> createTamTru(@RequestBody @Valid TamTruRequest request) {
        return ApiResponse.<TamVangResponse>builder()
                .result(tamVangService.createTamTru(request))
                .build();
    }

}
