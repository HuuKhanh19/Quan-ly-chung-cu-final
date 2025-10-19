package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.CapNhatThanhToanRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiBatBuocResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.MonthlyFeeId;
import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiThanhToan;
import com.huukhanh19.quan_ly_chung_cu.repository.ThoiGianThuPhiRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.TongThanhToanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CapNhatThanhToanService {

    TongThanhToanRepository tongThanhToanRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    TongThanhToanService tongThanhToanService;

    @Transactional
    public PhiBatBuocResponse capNhatThanhToan(Integer idThoiGianThu, CapNhatThanhToanRequest request) {
        log.info("Updating payment status for thoiGianThu: {}, canHo list: {}",
                idThoiGianThu, request.getDanhSachIdCanHo());

        // 1. Validate thời gian thu phí
        ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(idThoiGianThu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời gian thu phí với ID: " + idThoiGianThu));

        // 2. Validate danh sách căn hộ
        if (request.getDanhSachIdCanHo().isEmpty()) {
            throw new RuntimeException("Danh sách căn hộ không được để trống");
        }

        // 3. Lấy thông tin TongThanhToan của các căn hộ trong danh sách
        List<TongThanhToan> danhSachCanCapNhat = new ArrayList<>();

        for (Integer idCanHo : request.getDanhSachIdCanHo()) {
            MonthlyFeeId monthlyFeeId = new MonthlyFeeId();
            monthlyFeeId.setIdCanHo(idCanHo);
            monthlyFeeId.setIdThoiGianThu(idThoiGianThu);

            TongThanhToan tongThanhToan = tongThanhToanRepository.findById(monthlyFeeId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin thanh toán cho căn hộ ID: " + idCanHo));

            // Kiểm tra xem đã thanh toán chưa
            if (tongThanhToan.getTrangThai() == TrangThaiThanhToan.DA_THANH_TOAN) {
                log.warn("CanHo {} already paid, skipping", idCanHo);
                continue; // Bỏ qua căn hộ đã thanh toán
            }

            // Cập nhật trạng thái thanh toán
            tongThanhToan.setSoTienDaNop(tongThanhToan.getTongPhi());
            tongThanhToan.setSoDu(0);
            tongThanhToan.setTrangThai(TrangThaiThanhToan.DA_THANH_TOAN);

            danhSachCanCapNhat.add(tongThanhToan);
            log.info("Marked canHo {} as paid", idCanHo);
        }

        // 4. Lưu tất cả các thay đổi
        if (!danhSachCanCapNhat.isEmpty()) {
            tongThanhToanRepository.saveAll(danhSachCanCapNhat);
            log.info("Updated {} canHo as paid", danhSachCanCapNhat.size());
        } else {
            log.warn("No canHo to update");
        }

        // 5. Gọi lại API getPhiBatBuocByThoiGianThu để lấy response cập nhật
        return tongThanhToanService.getPhiBatBuocByThoiGianThu(idThoiGianThu);
    }
}