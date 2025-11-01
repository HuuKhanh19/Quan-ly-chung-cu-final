package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.DongGopCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.LichSuThanhToanCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.PhiBatBuocCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiThanhToan;
import com.huukhanh19.quan_ly_chung_cu.mapper.LichSuThanhToanMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.ChiTietDongGopRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.TongThanhToanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LichSuThanhToanService {

    TongThanhToanRepository tongThanhToanRepository;
    ChiTietDongGopRepository chiTietDongGopRepository;
    CanHoRepository canHoRepository;
    LichSuThanhToanMapper lichSuThanhToanMapper;

    public LichSuThanhToanCanHoResponse getLichSuThanhToanCanHo(Integer idCanHo) {
        log.info("Getting payment history for canHo: {}", idCanHo);

        // 1. Validate căn hộ
        CanHo canHo = canHoRepository.findById(idCanHo)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ với ID: " + idCanHo));

        // 2. Lấy danh sách phí bắt buộc
        List<TongThanhToan> danhSachPhiBatBuoc = tongThanhToanRepository.findByIdCanHo(idCanHo);

        // 3. Lấy danh sách đóng góp tự nguyện
        List<ChiTietDongGop> danhSachDongGop = chiTietDongGopRepository.findByIdCanHo(idCanHo);

        log.info("Found {} phí bắt buộc and {} đóng góp for canHo {}",
                danhSachPhiBatBuoc.size(), danhSachDongGop.size(), idCanHo);

        // 4. Tính toán thống kê
        int tongPhiBatBuocPhaiNop = 0;
        int tongPhiBatBuocDaNop = 0;
        int tongPhiBatBuocConThieu = 0;

        int soKyChuaThanhToan = 0;
        int soKyDaThanhToan = 0;

        for (TongThanhToan tongThanhToan : danhSachPhiBatBuoc) {
            tongPhiBatBuocPhaiNop += tongThanhToan.getTongPhi();
            tongPhiBatBuocDaNop += tongThanhToan.getSoTienDaNop();
            tongPhiBatBuocConThieu += tongThanhToan.getSoDu();

            // Đếm số kỳ theo trạng thái
            if (tongThanhToan.getTrangThai() == TrangThaiThanhToan.DA_THANH_TOAN) {
                soKyDaThanhToan++;
            } else if (tongThanhToan.getTrangThai() == TrangThaiThanhToan.CHUA_THANH_TOAN) {
                soKyChuaThanhToan++;
            }
        }

        // Tính tổng đóng góp tự nguyện
        int tongDongGopTuNguyen = danhSachDongGop.stream()
                .mapToInt(ChiTietDongGop::getSoTienDongGop)
                .sum();

        // Tổng tất cả
        int tongTatCa = tongPhiBatBuocDaNop + tongDongGopTuNguyen;

        // 5. Map sang response
        List<PhiBatBuocCanHoResponse> danhSachPhiBatBuocResponse = danhSachPhiBatBuoc.stream()
                .map(lichSuThanhToanMapper::toPhiBatBuocCanHoResponse)
                .sorted(Comparator.comparing(PhiBatBuocCanHoResponse::getIdThoiGianThu).reversed()) // Mới nhất lên đầu
                .collect(Collectors.toList());

        List<DongGopCanHoResponse> danhSachDongGopResponse = danhSachDongGop.stream()
                .map(lichSuThanhToanMapper::toDongGopCanHoResponse)
                .sorted(Comparator.comparing(DongGopCanHoResponse::getNgayDongGop).reversed()) // Mới nhất lên đầu
                .collect(Collectors.toList());

        log.info("CanHo {} - Total must pay: {}, paid: {}, remaining: {}, donations: {}",
                idCanHo, tongPhiBatBuocPhaiNop, tongPhiBatBuocDaNop, tongPhiBatBuocConThieu, tongDongGopTuNguyen);

        // 6. Trả về kết quả
        return LichSuThanhToanCanHoResponse.builder()
                .idCanHo(canHo.getIdCanHo())
                .soNha(canHo.getSoNha())
                .loaiCanHo(canHo.getLoaiCanHo())
                .tongPhiBatBuocPhaiNop(tongPhiBatBuocPhaiNop)
                .tongPhiBatBuocDaNop(tongPhiBatBuocDaNop)
                .tongPhiBatBuocConThieu(tongPhiBatBuocConThieu)
                .tongDongGopTuNguyen(tongDongGopTuNguyen)
                .tongTatCa(tongTatCa)
                .soKyChuaThanhToan(soKyChuaThanhToan)
                .soKyDaThanhToan(soKyDaThanhToan)
                .danhSachPhiBatBuoc(danhSachPhiBatBuocResponse)
                .danhSachDongGop(danhSachDongGopResponse)
                .build();
    }
}