package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TongThanhToanCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TongThanhToanBatchResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TongThanhToanDetailResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.*;
import com.huukhanh19.quan_ly_chung_cu.mapper.TongThanhToanMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.*;
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
public class TongThanhToanService {

    TongThanhToanRepository tongThanhToanRepository;
    PhiChungCuRepository phiChungCuRepository;
    PhiGuiXeRepository phiGuiXeRepository;
    TienIchRepository tienIchRepository;
    CanHoRepository canHoRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    TongThanhToanMapper tongThanhToanMapper;

    @Transactional
    public TongThanhToanBatchResponse createTongThanhToanForAllCanHo(TongThanhToanCreationRequest request) {
        log.info("Starting batch create TongThanhToan for thoiGianThu: {}", request.getIdThoiGianThu());

        // 1. Validate thời gian thu phí
        ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(request.getIdThoiGianThu())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời gian thu phí với ID: " + request.getIdThoiGianThu()));

        // 2. Lấy tất cả căn hộ có người ở
        List<CanHo> danhSachCanHo = canHoRepository.findAllCanHoCoNguoiO();

        if (danhSachCanHo.isEmpty()) {
            log.warn("Không có căn hộ nào có người ở");
            return createEmptyResponse();
        }

        log.info("Found {} căn hộ có người ở", danhSachCanHo.size());

        // 3. Tạo tổng thanh toán cho từng căn hộ
        List<TongThanhToanDetailResponse> danhSachResponse = new ArrayList<>();
        int successCount = 0;
        int failCount = 0;
        int tongPhiChungCuAll = 0;
        int tongTienIchAll = 0;
        int tongGuiXeAll = 0;
        int tongPhiAll = 0;

        for (CanHo canHo : danhSachCanHo) {
            try {
                TongThanhToanDetailResponse response = createTongThanhToanForCanHo(
                        canHo, thoiGianThuPhi, request.getIdThoiGianThu());

                danhSachResponse.add(response);
                successCount++;

                // Tính tổng
                tongPhiChungCuAll += response.getTongPhiChungCu();
                tongTienIchAll += response.getTongTienIch();
                tongGuiXeAll += response.getTongGuiXe();
                tongPhiAll += response.getTongPhi();

                log.info("Created TongThanhToan for canHo: {} - {} with tongPhi: {}",
                        canHo.getIdCanHo(), canHo.getSoNha(), response.getTongPhi());
            } catch (Exception e) {
                failCount++;
                log.error("Failed to create TongThanhToan for canHo: {} - Error: {}",
                        canHo.getIdCanHo(), e.getMessage());
            }
        }

        log.info("Batch create completed. Success: {}, Fail: {}, TongPhiAll: {}",
                successCount, failCount, tongPhiAll);

        // 4. Trả về kết quả
        return TongThanhToanBatchResponse.builder()
                .ngayThu(thoiGianThuPhi.getNgayThu())
                .hanThu(thoiGianThuPhi.getHanThu())
                .totalCanHo(danhSachCanHo.size())
                .successCount(successCount)
                .failCount(failCount)
                .tongPhiChungCuAll(tongPhiChungCuAll)
                .tongTienIchAll(tongTienIchAll)
                .tongGuiXeAll(tongGuiXeAll)
                .tongPhiAll(tongPhiAll)
                .danhSachTongThanhToan(danhSachResponse)
                .build();
    }

    private TongThanhToanDetailResponse createTongThanhToanForCanHo(CanHo canHo,
                                                                    ThoiGianThuPhi thoiGianThuPhi,
                                                                    Integer idThoiGianThu) {
        // 1. Tạo composite key
        MonthlyFeeId monthlyFeeId = new MonthlyFeeId();
        monthlyFeeId.setIdCanHo(canHo.getIdCanHo());
        monthlyFeeId.setIdThoiGianThu(idThoiGianThu);

        // 2. Kiểm tra đã tồn tại chưa
        if (tongThanhToanRepository.existsById(monthlyFeeId)) {
            throw new RuntimeException("Tổng thanh toán cho căn hộ này trong kỳ thu phí này đã tồn tại");
        }

        // 3. Lấy các khoản phí (phải đã được tạo trước bằng các API batch)
        PhiChungCu phiChungCu = phiChungCuRepository.findById(monthlyFeeId)
                .orElseThrow(() -> new RuntimeException("Chưa tạo phí chung cư cho căn hộ " + canHo.getSoNha()));

        PhiGuiXe phiGuiXe = phiGuiXeRepository.findById(monthlyFeeId)
                .orElseThrow(() -> new RuntimeException("Chưa tạo phí gửi xe cho căn hộ " + canHo.getSoNha()));

        TienIch tienIch = tienIchRepository.findById(monthlyFeeId)
                .orElse(null); // Tiện ích có thể null nếu chưa nhập

        // 4. Tính tổng các khoản phí
        Integer tongPhiChungCu = phiChungCu.getTongPhiChungCu();
        Integer tongTienIch = (tienIch != null) ? tienIch.getTongTienIch() : 0;
        Integer tongGuiXe = phiGuiXe.getTongGuiXe();
        Integer tongPhi = tongPhiChungCu + tongTienIch + tongGuiXe;
        Integer soDu = - tongPhi; // Ban đầu chưa nộp gì

        // 5. Tạo và lưu TongThanhToan
        TongThanhToan tongThanhToan = TongThanhToan.builder()
                .id(monthlyFeeId)
                .canHo(canHo)
                .thoiGianThuPhi(thoiGianThuPhi)
                .tongPhiChungCu(tongPhiChungCu)
                .tongTienIch(tongTienIch)
                .tongGuiXe(tongGuiXe)
                .tongPhi(tongPhi)
                .soTienDaNop(0)
                .soDu(soDu)
                .trangThai("Chưa thanh toán")
                .build();

        tongThanhToanRepository.save(tongThanhToan);

        // 6. Map sang response
        return tongThanhToanMapper.toDetailResponse(tongThanhToan, phiChungCu, tienIch, phiGuiXe);
    }

    private TongThanhToanBatchResponse createEmptyResponse() {
        return TongThanhToanBatchResponse.builder()
                .totalCanHo(0)
                .successCount(0)
                .failCount(0)
                .tongPhiChungCuAll(0)
                .tongTienIchAll(0)
                .tongGuiXeAll(0)
                .tongPhiAll(0)
                .danhSachTongThanhToan(new ArrayList<>())
                .build();
    }
}