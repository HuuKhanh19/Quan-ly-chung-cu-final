package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChangeChuHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TachHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.HoGiaDinhResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TachHoResponse;
import com.huukhanh19.quan_ly_chung_cu.service.HoGiaDinhService;
import com.huukhanh19.quan_ly_chung_cu.service.NhanKhauService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Trong file HoGiaDinhController.java

    @PostMapping("/tach-ho/{cccdChuHoCu}")
    public ApiResponse<String> tachHo(
            @PathVariable String cccdChuHoCu,
            @RequestBody @Valid TachHoRequest request) {

        hoGiaDinhService.tachHo(cccdChuHoCu, request);
        return ApiResponse.<String>builder().result("Tách hộ thành công!").build();
    }

    @GetMapping
    public ApiResponse<List<HoGiaDinhResponse>> getAllHoGiaDinh() {
        log.info("GET /ho-gia-dinh");
        return ApiResponse.<List<HoGiaDinhResponse>>builder()
                .result(hoGiaDinhService.getAllHoGiaDinh())
                .build();
    }

//    @GetMapping("/{cccdChuHo}")
//    public ApiResponse<HoGiaDinhResponse> getHoGiaDinhById(@PathVariable String cccdChuHo) {
//        log.info("GET /ho-gia-dinh/{}", cccdChuHo);
//        return ApiResponse.<HoGiaDinhResponse>builder()
//                .result(hoGiaDinhService.getHoGiaDinhById(cccdChuHo))
//                .build();
//    }
}
