package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.KhoanDongGopCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.CanHoDaDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.KhoanDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiTuNguyenDetailResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiTuNguyenResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiDongGop;
import com.huukhanh19.quan_ly_chung_cu.mapper.CanHoDaDongGopMapper;
import com.huukhanh19.quan_ly_chung_cu.mapper.KhoanDongGopMapper;
import com.huukhanh19.quan_ly_chung_cu.mapper.PhiTuNguyenMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.ChiTietDongGopRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.KhoanDongGopRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KhoanDongGopService {

    KhoanDongGopRepository khoanDongGopRepository;
    ChiTietDongGopRepository chiTietDongGopRepository;
    KhoanDongGopMapper khoanDongGopMapper;
    PhiTuNguyenMapper phiTuNguyenMapper;
    CanHoDaDongGopMapper canHoDaDongGopMapper;

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

    public PhiTuNguyenResponse getAllPhiTuNguyen() {
        log.info("Getting all PhiTuNguyen");

        // 1. Lấy tất cả khoản đóng góp (sắp xếp theo ngày tạo mới nhất)
        List<KhoanDongGop> danhSachKhoanDongGop = khoanDongGopRepository.findAll()
                .stream()
                .sorted((k1, k2) -> k2.getNgayTao().compareTo(k1.getNgayTao()))
                .collect(Collectors.toList());

        if (danhSachKhoanDongGop.isEmpty()) {
            log.warn("No KhoanDongGop found");
            return createEmptyPhiTuNguyenResponse();
        }

        log.info("Found {} KhoanDongGop", danhSachKhoanDongGop.size());

        // 2. Xử lý từng khoản đóng góp
        List<PhiTuNguyenDetailResponse> danhSachDetail = new ArrayList<>();
        int tongTienThuDuocAll = 0;
        int tongSoCanHoDongGopAll = 0;

        for (KhoanDongGop khoanDongGop : danhSachKhoanDongGop) {
            try {
                // Lấy danh sách chi tiết đóng góp của khoản này
                List<ChiTietDongGop> danhSachChiTiet = chiTietDongGopRepository
                        .findByIdKhoanDongGop(khoanDongGop.getIdKhoanDongGop());

                // Map sang CanHoDaDongGopResponse
                List<CanHoDaDongGopResponse> danhSachCanHo = danhSachChiTiet.stream()
                        .map(canHoDaDongGopMapper::toResponse)
                        .collect(Collectors.toList());

                // Map sang PhiTuNguyenDetailResponse
                PhiTuNguyenDetailResponse detail = phiTuNguyenMapper.toDetailResponse(khoanDongGop);
                detail.setDanhSachCanHoDaDongGop(danhSachCanHo);

                danhSachDetail.add(detail);

                // Tính tổng
                tongTienThuDuocAll += khoanDongGop.getTongTienThuDuoc();
                tongSoCanHoDongGopAll += khoanDongGop.getSoCanHoDongGop();

                log.debug("Processed KhoanDongGop: {} with {} contributions",
                        khoanDongGop.getIdKhoanDongGop(), danhSachChiTiet.size());
            } catch (Exception e) {
                log.error("Failed to process KhoanDongGop: {} - Error: {}",
                        khoanDongGop.getIdKhoanDongGop(), e.getMessage());
            }
        }

        log.info("Processing completed. Total: {} khoản, {} đồng, {} căn hộ",
                danhSachDetail.size(), tongTienThuDuocAll, tongSoCanHoDongGopAll);

        // 3. Trả về kết quả
        return PhiTuNguyenResponse.builder()
                .totalKhoanDongGop(danhSachDetail.size())
                .tongTienThuDuocAll(tongTienThuDuocAll)
                .tongSoCanHoDongGopAll(tongSoCanHoDongGopAll)
                .danhSachKhoanDongGop(danhSachDetail)
                .build();
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

    private PhiTuNguyenResponse createEmptyPhiTuNguyenResponse() {
        return PhiTuNguyenResponse.builder()
                .totalKhoanDongGop(0)
                .tongTienThuDuocAll(0)
                .tongSoCanHoDongGopAll(0)
                .danhSachKhoanDongGop(new ArrayList<>())
                .build();
    }
}