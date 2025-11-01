package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TienIchCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TienIchResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.*;
import com.huukhanh19.quan_ly_chung_cu.mapper.TienIchMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TienIchService {
    TienIchRepository tienIchRepository;
    CanHoRepository canHoRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    TienIchMapper tienIchMapper;

    @Transactional
    public TienIchResponse createTienIch(TienIchCreationRequest request) {
        log.info("Creating TienIch for canHo: {}, thoiGianThu: {}",
                request.getIdCanHo(), request.getIdThoiGianThu());

        // Kiểm tra căn hộ có tồn tại không
        CanHo canHo = canHoRepository.findById(request.getIdCanHo())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ với ID: " + request.getIdCanHo()));

        // Kiểm tra căn hộ có hộ gia đình (có người ở) không
        if (canHo.getHoGiaDinh() == null) {
            log.warn("CanHo {} does not have HoGiaDinh", request.getIdCanHo());
            throw new RuntimeException("Không thể tạo tiện ích cho căn hộ chưa có người ở");
        }

        log.info("CanHo {} has HoGiaDinh, proceeding to create TienIch", request.getIdCanHo());

        // Kiểm tra thời gian thu phí có tồn tại không
        ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(request.getIdThoiGianThu())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời gian thu phí với ID: " + request.getIdThoiGianThu()));

        // Tạo composite key
        MonthlyFeeId monthlyFeeId = new MonthlyFeeId();
        monthlyFeeId.setIdCanHo(request.getIdCanHo());
        monthlyFeeId.setIdThoiGianThu(request.getIdThoiGianThu());

        // Kiểm tra xem tiện ích đã tồn tại chưa
        if (tienIchRepository.existsById(monthlyFeeId)) {
            throw new RuntimeException("Tiện ích cho căn hộ này trong kỳ thu phí này đã tồn tại");
        }

        // Tính tổng tiền tiện ích
        Integer tongTienIch = request.getTienDien() + request.getTienNuoc() + request.getTienInternet();

        // Sử dụng mapper để tạo entity
        TienIch tienIch = tienIchMapper.toEntity(request, monthlyFeeId, canHo, thoiGianThuPhi, tongTienIch);

        tienIchRepository.save(tienIch);

        log.info("Created TienIch successfully for canHo: {} with tongTienIch: {}",
                request.getIdCanHo(), tongTienIch);

        // Sử dụng mapper để tạo response
        return tienIchMapper.toResponse(tienIch);
    }
}