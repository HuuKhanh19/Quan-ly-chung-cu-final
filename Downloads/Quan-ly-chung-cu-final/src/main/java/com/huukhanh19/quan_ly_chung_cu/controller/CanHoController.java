package com.huukhanh19.quan_ly_chung_cu.controller;

import com.huukhanh19.quan_ly_chung_cu.dto.request.UserCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ApiResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.CanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.service.CanHoService;
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
@RequestMapping("/can-ho")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CanHoController {
    CanHoService canHoService;

    @GetMapping
    ApiResponse<List<CanHoResponse>> getAllCanHo(){
        return ApiResponse.<List<CanHoResponse>>builder()
                .result(canHoService.getAllCanHo())
                .build();
    }
}
