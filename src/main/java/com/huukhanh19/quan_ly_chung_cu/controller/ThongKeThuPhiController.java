package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.LichSuThanhToanCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeSauThangResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeThuPhiResponse;
import com.huukhanh19.quan_ly_chung_cu.service.LichSuThanhToanService;
import com.huukhanh19.quan_ly_chung_cu.service.ThongKeSauThangService;
import com.huukhanh19.quan_ly_chung_cu.service.ThongKeThuPhiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thong-ke-thu-phi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ThongKeThuPhiController {

    ThongKeThuPhiService thongKeService;
    LichSuThanhToanService lichSuThanhToanService;
    ThongKeSauThangService thongKeSauThangService;

    @GetMapping("/{idThoiGianThu}")
    public ApiResponse<ThongKeThuPhiResponse> thongKeThuPhi(@PathVariable Integer idThoiGianThu) {
        return ApiResponse.<ThongKeThuPhiResponse>builder()
                .result(thongKeService.thongKeThuPhiByThoiGianThu(idThoiGianThu))
                .build();
    }

    @GetMapping("/can-ho/{idCanHo}")
    public ApiResponse<LichSuThanhToanCanHoResponse> getLichSuThanhToanCanHo(@PathVariable Integer idCanHo) {
        return ApiResponse.<LichSuThanhToanCanHoResponse>builder()
                .result(lichSuThanhToanService.getLichSuThanhToanCanHo(idCanHo))
                .build();
    }

    @GetMapping("/sau-thang-gan-nhat")
    public ApiResponse<ThongKeSauThangResponse> thongKeSauThang() {
        return ApiResponse.<ThongKeSauThangResponse>builder()
                .result(thongKeSauThangService.thongKeSauThang())
                .build();
    }
}