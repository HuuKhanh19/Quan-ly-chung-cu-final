package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.UserCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.UserUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.service.NhanKhauService;
import com.huukhanh19.quan_ly_chung_cu.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nhan-khau")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NhanKhauController {
    NhanKhauService nhanKhauService;

    @PostMapping
    public ApiResponse<NhanKhauResponse> createNhanKhau(@RequestBody @Valid NhanKhauCreationRequest request) {
        return ApiResponse.<NhanKhauResponse>builder()
                .result(nhanKhauService.createNhanKhau(request))
                .build();
    }

    @PutMapping("/{cccd}") // Nhận cccd từ URL
    public ApiResponse<NhanKhauResponse> updateNhanKhau(
            @PathVariable String cccd,
            @RequestBody @Valid NhanKhauUpdateRequest request) {

        return ApiResponse.<NhanKhauResponse>builder()
                .result(nhanKhauService.updateNhanKhau(cccd, request))
                .build();
    }

}
