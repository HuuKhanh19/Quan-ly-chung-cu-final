package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TienIchCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TienIchResponse;
import com.huukhanh19.quan_ly_chung_cu.service.PhiService;
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
@RequestMapping("/phi")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhiController {
    PhiService phiService;

    // Trong file PhiController.java
    @PostMapping("/tien-ich")
    public ApiResponse<TienIchResponse> createTienIch(@RequestBody @Valid TienIchCreationRequest request) {
        return ApiResponse.<TienIchResponse>builder()
                .result(phiService.createTienIch(request))
                .build();
    }
}
