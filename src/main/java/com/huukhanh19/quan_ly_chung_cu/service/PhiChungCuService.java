package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.PhiChungCuCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiChungCuBatchResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiChungCuResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiChungCu;
import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import com.huukhanh19.quan_ly_chung_cu.mapper.PhiChungCuMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.PhiChungCuRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.ThoiGianThuPhiRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhiChungCuService {

    PhiChungCuRepository phiChungCuRepository;
    CanHoRepository canHoRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    PhiChungCuMapper phiChungCuMapper;

    @Transactional
    public PhiChungCuBatchResponse createPhiChungCuForAllCanHo(PhiChungCuCreationRequest request) {
        log.info("Starting batch create PhiChungCu for thoiGianThu: {}", request.getIdThoiGianThu());

        // 1. Validate thời gian thu phí
        ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(request.getIdThoiGianThu())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời gian thu phí với ID: " + request.getIdThoiGianThu()));

        // 2. Lấy tất cả căn hộ có người ở
        List<CanHo> danhSachCanHo = canHoRepository.findAllCanHoCoNguoiO();

        if (danhSachCanHo.isEmpty()) {
            log.warn("Không có căn hộ nào có người ở");
            return PhiChungCuBatchResponse.builder()
                    .totalCanHo(0)
                    .successCount(0)
                    .failCount(0)
                    .danhSachPhiChungCu(new ArrayList<>())
                    .build();
        }

        log.info("Found {} căn hộ có người ở", danhSachCanHo.size());

        // 3. Tạo phí chung cư cho từng căn hộ
        List<PhiChungCuResponse> danhSachResponse = new ArrayList<>();
        int successCount = 0;
        int failCount = 0;

        for (CanHo canHo : danhSachCanHo) {
            try {
                PhiChungCu phiChungCu = createPhiChungCuForCanHo(canHo, thoiGianThuPhi, request);
                PhiChungCuResponse response = phiChungCuMapper.toResponse(phiChungCu);
                danhSachResponse.add(response);
                successCount++;
                log.info("Created PhiChungCu for canHo: {} - {}", canHo.getIdCanHo(), canHo.getSoNha());
            } catch (Exception e) {
                failCount++;
                log.error("Failed to create PhiChungCu for canHo: {} - Error: {}",
                        canHo.getIdCanHo(), e.getMessage());
            }
        }

        log.info("Batch create completed. Success: {}, Fail: {}", successCount, failCount);

        // 4. Trả về kết quả
        return PhiChungCuBatchResponse.builder()
                .totalCanHo(danhSachCanHo.size())
                .successCount(successCount)
                .failCount(failCount)
                .danhSachPhiChungCu(danhSachResponse)
                .build();
    }

    private PhiChungCu createPhiChungCuForCanHo(CanHo canHo,
                                                ThoiGianThuPhi thoiGianThuPhi,
                                                PhiChungCuCreationRequest request) {
        // 1. Tạo composite key
        MonthlyFeeId monthlyFeeId = new MonthlyFeeId();
        monthlyFeeId.setIdCanHo(canHo.getIdCanHo());
        monthlyFeeId.setIdThoiGianThu(request.getIdThoiGianThu());

        // 2. Kiểm tra đã tồn tại chưa
        if (phiChungCuRepository.existsById(monthlyFeeId)) {
            throw new RuntimeException("Phí chung cư cho căn hộ này trong kỳ thu phí này đã tồn tại");
        }

        // 3. Tính phí chung cư dựa trên diện tích
        BigDecimal dienTich = canHo.getDienTich();
        Integer phiDichVu = dienTich.multiply(new BigDecimal(request.getPhiDichVuPerM2())).intValue();
        Integer phiQuanLy = dienTich.multiply(new BigDecimal(request.getPhiQuanLyPerM2())).intValue();
        Integer tongPhiChungCu = phiDichVu + phiQuanLy;

        // 4. Tạo và lưu PhiChungCu
        PhiChungCu phiChungCu = PhiChungCu.builder()
                .id(monthlyFeeId)
                .canHo(canHo)
                .thoiGianThuPhi(thoiGianThuPhi)
                .phiDichVu(phiDichVu)
                .phiQuanLy(phiQuanLy)
                .tongPhiChungCu(tongPhiChungCu)
                .build();

        return phiChungCuRepository.save(phiChungCu);
    }
}