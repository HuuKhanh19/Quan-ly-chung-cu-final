package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.PhiChungCuCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiChungCuResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiChungCu;
import com.huukhanh19.quan_ly_chung_cu.mapper.PhiChungCuMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.PhiChungCuRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhiService {
    static BigDecimal SERVICE_FEE_RATE = new BigDecimal("10000"); // Ví dụ: 10,000đ/m2
    static BigDecimal MANAGEMENT_FEE_RATE = new BigDecimal("7000"); // 7,000đ/m2

    CanHoRepository canHoRepository;
    PhiChungCuRepository phiChungCuRepository;
    PhiChungCuMapper phiChungCuMapper;

    @Transactional
    public PhiChungCuResponse createPhiChungCu(PhiChungCuCreationRequest request) {
        // 1. TÌM CĂN HỘ
        CanHo canHo = canHoRepository.findById(request.getIdCanHo())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Căn hộ."));

        // 2. TÍNH TOÁN CÁC GIÁ TRỊ TỰ ĐỘNG
        LocalDate today = LocalDate.now();
        LocalDate hanThu = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate thoiGianThu = hanThu.minusDays(4);

        // Tính toán các khoản phí dựa trên diện tích
        BigDecimal dienTich = canHo.getDienTich();
        int phiDichVu = dienTich.multiply(SERVICE_FEE_RATE).intValue();
        int phiQuanLy = dienTich.multiply(MANAGEMENT_FEE_RATE).intValue();

        // 3. TẠO KHÓA CHÍNH VÀ KIỂM TRA TỒN TẠI
        MonthlyFeeId feeId = new MonthlyFeeId(request.getIdCanHo(), hanThu);
        if (phiChungCuRepository.existsById(feeId)) {
            throw new RuntimeException("Phí chung cư cho căn hộ này trong tháng này đã tồn tại.");
        }

        // 4. TẠO VÀ LƯU ENTITY
        PhiChungCu phiChungCu = new PhiChungCu();
        phiChungCu.setId(feeId);
        phiChungCu.setCanHo(canHo);
        phiChungCu.setPhiDichVu(phiDichVu); // Gán phí đã tính
        phiChungCu.setPhiQuanLy(phiQuanLy); // Gán phí đã tính
        phiChungCu.setTongPhiChungCu(phiDichVu + phiQuanLy);
        phiChungCu.setThoiGianThu(thoiGianThu);

        PhiChungCu savedPhiChungCu = phiChungCuRepository.save(phiChungCu);

        // 5. DÙNG MAPPER ĐỂ TRẢ VỀ RESPONSE
        return phiChungCuMapper.toPhiChungCuResponse(savedPhiChungCu);
    }
}
