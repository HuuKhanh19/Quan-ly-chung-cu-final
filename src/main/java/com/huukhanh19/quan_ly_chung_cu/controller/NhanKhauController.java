package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.*;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.LichSuThayDoi;
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

    @DeleteMapping("/{cccd}")
    public ApiResponse<String> deleteNhanKhau(@PathVariable String cccd) {
        log.info("DELETE /nhan-khau/{}", cccd);
        nhanKhauService.deleteNhanKhau(cccd);
        return ApiResponse.<String>builder()
                .result("Xóa nhân khẩu thành công")
                .build();
    }

    @GetMapping
    public ApiResponse<List<NhanKhauResponse>> getAllNhanKhau() {
        log.info("GET /nhan-khau");
        return ApiResponse.<List<NhanKhauResponse>>builder()
                .result(nhanKhauService.getAllNhanKhau())
                .build();
    }

    @GetMapping("/ho-gia-dinh/{cccdChuHo}")
    public ApiResponse<List<NhanKhauResponse>> getNhanKhauByHoGiaDinh(
            @PathVariable String cccdChuHo) {
        log.info("GET /nhan-khau/ho-gia-dinh/{}", cccdChuHo);
        return ApiResponse.<List<NhanKhauResponse>>builder()
                .result(nhanKhauService.getNhanKhauByHoGiaDinh(cccdChuHo))
                .build();
    }

    @PostMapping("/search")
    public ApiResponse<List<NhanKhauResponse>> searchNhanKhau(@RequestBody NhanKhauSearchRequest request) {
        return ApiResponse.<List<NhanKhauResponse>>builder()
                .result(nhanKhauService.searchNhanKhau(request))
                .build();
    }

    @GetMapping("/history/{cccd}")
    public ApiResponse<List<LichSuThayDoi>> getNhanKhauHistory(@PathVariable String cccd) {
        return ApiResponse.<List<LichSuThayDoi>>builder()
                .result(nhanKhauService.getHistory(cccd))
                .build();
    }
}