package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeGioiTinhResponse;
import com.huukhanh19.quan_ly_chung_cu.service.ThongKeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thong-ke")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ThongKeController {
    ThongKeService thongKeService;

    @GetMapping("/gioi-tinh")
    public ApiResponse<ThongKeGioiTinhResponse> thongKeTheoGioiTinh() {
        return ApiResponse.<ThongKeGioiTinhResponse>builder()
                .result(thongKeService.thongKeTheoGioiTinh())
                .build();
    }
}
