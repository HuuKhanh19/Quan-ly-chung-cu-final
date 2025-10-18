package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.PhiGuiXeCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiGuiXeBatchResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiGuiXeResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.PhiGuiXe;
import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import com.huukhanh19.quan_ly_chung_cu.mapper.PhiGuiXeMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.PhiGuiXeRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.ThoiGianThuPhiRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PhiGuiXeService {

    PhiGuiXeRepository phiGuiXeRepository;
    CanHoRepository canHoRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    PhiGuiXeMapper phiGuiXeMapper;

    @Transactional
    public PhiGuiXeBatchResponse createPhiGuiXeForAllCanHo(PhiGuiXeCreationRequest request) {
        log.info("Starting batch create PhiGuiXe for thoiGianThu: {}", request.getIdThoiGianThu());

        // 1. Validate thời gian thu phí
        ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(request.getIdThoiGianThu())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời gian thu phí với ID: " + request.getIdThoiGianThu()));

        // 2. Lấy tất cả căn hộ có người ở
        List<CanHo> danhSachCanHo = canHoRepository.findAllCanHoCoNguoiO();

        if (danhSachCanHo.isEmpty()) {
            log.warn("Không có căn hộ nào có người ở");
            return PhiGuiXeBatchResponse.builder()
                    .totalCanHo(0)
                    .successCount(0)
                    .failCount(0)
                    .danhSachPhiGuiXe(new ArrayList<>())
                    .build();
        }

        log.info("Found {} căn hộ có người ở", danhSachCanHo.size());

        // 3. Tạo phí gửi xe cho từng căn hộ
        List<PhiGuiXeResponse> danhSachResponse = new ArrayList<>();
        int successCount = 0;
        int failCount = 0;

        for (CanHo canHo : danhSachCanHo) {
            try {
                PhiGuiXe phiGuiXe = createPhiGuiXeForCanHo(canHo, thoiGianThuPhi, request);
                PhiGuiXeResponse response = phiGuiXeMapper.toResponse(phiGuiXe);
                danhSachResponse.add(response);
                successCount++;
                log.info("Created PhiGuiXe for canHo: {} - {} (XeMay: {}, Oto: {})",
                        canHo.getIdCanHo(), canHo.getSoNha(),
                        response.getSoXeMay(), response.getSoOto());
            } catch (Exception e) {
                failCount++;
                log.error("Failed to create PhiGuiXe for canHo: {} - Error: {}",
                        canHo.getIdCanHo(), e.getMessage());
            }
        }

        log.info("Batch create completed. Success: {}, Fail: {}", successCount, failCount);

        // 4. Trả về kết quả
        return PhiGuiXeBatchResponse.builder()
                .totalCanHo(danhSachCanHo.size())
                .successCount(successCount)
                .failCount(failCount)
                .danhSachPhiGuiXe(danhSachResponse)
                .build();
    }

    private PhiGuiXe createPhiGuiXeForCanHo(CanHo canHo,
                                            ThoiGianThuPhi thoiGianThuPhi,
                                            PhiGuiXeCreationRequest request) {
        // 1. Tạo composite key
        MonthlyFeeId monthlyFeeId = new MonthlyFeeId();
        monthlyFeeId.setIdCanHo(canHo.getIdCanHo());
        monthlyFeeId.setIdThoiGianThu(request.getIdThoiGianThu());

        // 2. Kiểm tra đã tồn tại chưa
        if (phiGuiXeRepository.existsById(monthlyFeeId)) {
            throw new RuntimeException("Phí gửi xe cho căn hộ này trong kỳ thu phí này đã tồn tại");
        }

        // 3. Lấy thông tin số xe từ hộ gia đình
        HoGiaDinh hoGiaDinh = canHo.getHoGiaDinh();
        Integer soXeMay = (hoGiaDinh != null && hoGiaDinh.getSoXeMay() != null) ? hoGiaDinh.getSoXeMay() : 0;
        Integer soOto = (hoGiaDinh != null && hoGiaDinh.getSoOto() != null) ? hoGiaDinh.getSoOto() : 0;

        // 4. Tính phí gửi xe
        Integer tienXeMay = soXeMay * request.getTienXeMayPerXe();
        Integer tienXeOto = soOto * request.getTienXeOtoPerXe();
        Integer tongGuiXe = tienXeMay + tienXeOto;

        // 5. Tạo và lưu PhiGuiXe
        PhiGuiXe phiGuiXe = PhiGuiXe.builder()
                .id(monthlyFeeId)
                .canHo(canHo)
                .thoiGianThuPhi(thoiGianThuPhi)
                .tienXeMay(tienXeMay)
                .tienXeOto(tienXeOto)
                .tongGuiXe(tongGuiXe)
                .build();

        return phiGuiXeRepository.save(phiGuiXe);
    }
}