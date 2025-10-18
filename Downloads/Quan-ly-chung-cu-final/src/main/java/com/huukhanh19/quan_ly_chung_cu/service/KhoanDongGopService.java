package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.KhoanDongGopCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.KhoanDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiDongGop;
import com.huukhanh19.quan_ly_chung_cu.mapper.KhoanDongGopMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.KhoanDongGopRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KhoanDongGopService {

    KhoanDongGopRepository khoanDongGopRepository;
    KhoanDongGopMapper khoanDongGopMapper;

    @Transactional
    public KhoanDongGopResponse createKhoanDongGop(KhoanDongGopCreationRequest request) {
        log.info("Creating KhoanDongGop: {}", request.getTenKhoanDongGop());

        // 1. Validate ngày bắt đầu thu và hạn đóng góp
        validateDates(request.getNgayBatDauThu(), request.getHanDongGop());

        // 2. Tạo khoản đóng góp
        KhoanDongGop khoanDongGop = KhoanDongGop.builder()
                .tenKhoanDongGop(request.getTenKhoanDongGop())
                .ngayTao(LocalDate.now())
                .ngayBatDauThu(request.getNgayBatDauThu())
                .hanDongGop(request.getHanDongGop())
                .trangThai(TrangThaiDongGop.DANG_THU)
                .tongTienThuDuoc(0)
                .soCanHoDongGop(0)
                .build();

        khoanDongGopRepository.save(khoanDongGop);

        log.info("Created KhoanDongGop successfully with ID: {}", khoanDongGop.getIdKhoanDongGop());

        // 3. Trả về response
        return khoanDongGopMapper.toResponse(khoanDongGop);
    }

    private void validateDates(LocalDate ngayBatDauThu, LocalDate hanDongGop) {
        LocalDate today = LocalDate.now();

        // Ngày bắt đầu thu không được trong quá khứ
        if (ngayBatDauThu.isBefore(today)) {
            throw new RuntimeException("Ngày bắt đầu thu không được trong quá khứ");
        }

        // Hạn đóng góp phải sau ngày bắt đầu thu
        if (hanDongGop.isBefore(ngayBatDauThu) || hanDongGop.isEqual(ngayBatDauThu)) {
            throw new RuntimeException("Hạn đóng góp phải sau ngày bắt đầu thu");
        }
    }
}