package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.BienDongThuPhiResponse;
import com.huukhanh19.quan_ly_chung_cu.service.BienDongThuPhiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bien-dong-thu-phi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BienDongThuPhiController {

    BienDongThuPhiService bienDongThuPhiService;

    @GetMapping
    public ApiResponse<List<BienDongThuPhiResponse>> get10BienDongGanNhat() {
        return ApiResponse.<List<BienDongThuPhiResponse>>builder()
                .result(bienDongThuPhiService.get10BienDongGanNhat())
                .build();
    }
}