package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.CanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.UserResponse;
import com.huukhanh19.quan_ly_chung_cu.mapper.CanHoMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('QUANLY')")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CanHoService {
    CanHoRepository canHoRepository;
    CanHoMapper canHoMapper;

    public List<CanHoResponse> getAllCanHo(){
        log.info("In method get All Can Ho");
        return canHoRepository.findAll().stream()
                .map(canHoMapper::toCanHoResponse).toList();
    }
}
