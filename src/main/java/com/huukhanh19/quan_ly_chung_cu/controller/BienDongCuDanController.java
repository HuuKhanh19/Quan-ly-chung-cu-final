package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.BienDongCuDanResponse;
import com.huukhanh19.quan_ly_chung_cu.service.BienDongCuDanService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bien-dong-cu-dan")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BienDongCuDanController {

    BienDongCuDanService bienDongCuDanService;

    @GetMapping
    public ApiResponse<List<BienDongCuDanResponse>> get10BienDongGanNhat() {
        return ApiResponse.<List<BienDongCuDanResponse>>builder()
                .result(bienDongCuDanService.get10BienDongGanNhat())
                .build();
    }
}