package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChangeChuHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.service.HoGiaDinhService;
import com.huukhanh19.quan_ly_chung_cu.service.NhanKhauService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ho-gia-dinh")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HoGiaDinhController {
    HoGiaDinhService hoGiaDinhService;

    @PostMapping("/change-chu-ho/{cccdChuHo}")
    public ApiResponse<String> changeChuHo(
            @PathVariable String cccdChuHo,
            @RequestBody @Valid ChangeChuHoRequest request) {

        hoGiaDinhService.changeChuHo(cccdChuHo, request);

        return ApiResponse.<String>builder().result("Thay đổi chủ hộ thành công!").build();
    }

}
