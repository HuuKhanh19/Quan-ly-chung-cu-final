package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChiTietDongGopCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ChiTietDongGopResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.ChiTietDongGop;
import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiDongGop;
import com.huukhanh19.quan_ly_chung_cu.mapper.ChiTietDongGopMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
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
import java.util.Optional;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChiTietDongGopService {

    ChiTietDongGopRepository chiTietDongGopRepository;
    KhoanDongGopRepository khoanDongGopRepository;
    CanHoRepository canHoRepository;
    ChiTietDongGopMapper chiTietDongGopMapper;

    @Transactional
    public ChiTietDongGopResponse createChiTietDongGop(ChiTietDongGopCreationRequest request) {
        log.info("Creating ChiTietDongGop for canHo: {}, khoanDongGop: {}",
                request.getIdCanHo(), request.getIdKhoanDongGop());

        // 1. Validate khoản đóng góp
        KhoanDongGop khoanDongGop = khoanDongGopRepository.findById(request.getIdKhoanDongGop())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoản đóng góp với ID: " + request.getIdKhoanDongGop()));

        // 2. Kiểm tra trạng thái khoản đóng góp
        if (khoanDongGop.getTrangThai() != TrangThaiDongGop.DANG_THU) {
            throw new RuntimeException("Khoản đóng góp này không còn nhận đóng góp (Trạng thái: " + khoanDongGop.getTrangThai().getDisplayName() + ")");
        }

        // 3. Validate căn hộ
        CanHo canHo = canHoRepository.findById(request.getIdCanHo())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ với ID: " + request.getIdCanHo()));

        // 4. Kiểm tra căn hộ có người ở không
        if (canHo.getHoGiaDinh() == null) {
            throw new RuntimeException("Căn hộ " + canHo.getSoNha() + " chưa có người ở");
        }

        // 5. Kiểm tra căn hộ đã đóng góp chưa
        Optional<ChiTietDongGop> existingDongGop = chiTietDongGopRepository.findByKhoanDongGopAndCanHo(
                request.getIdKhoanDongGop(), request.getIdCanHo());

        if (existingDongGop.isPresent()) {
            throw new RuntimeException("Căn hộ " + canHo.getSoNha() + " đã đóng góp cho khoản này rồi");
        }

        // 6. Validate ngày đóng góp
        validateNgayDongGop(request.getNgayDongGop(), khoanDongGop);

        // 7. Tạo chi tiết đóng góp
        ChiTietDongGop chiTietDongGop = ChiTietDongGop.builder()
                .khoanDongGop(khoanDongGop)
                .canHo(canHo)
                .soTienDongGop(request.getSoTienDongGop())
                .ngayDongGop(request.getNgayDongGop())
                .hinhThucThanhToan(request.getHinhThucThanhToan())
                .nguoiNhan(request.getNguoiNhan())
                .ghiChu(request.getGhiChu())
                .build();

        chiTietDongGopRepository.save(chiTietDongGop);

        // 8. Cập nhật thông tin khoản đóng góp
        updateKhoanDongGop(khoanDongGop, request.getSoTienDongGop());

        log.info("Created ChiTietDongGop successfully with ID: {}", chiTietDongGop.getIdChiTiet());

        // 9. Trả về response
        return chiTietDongGopMapper.toResponse(chiTietDongGop);
    }

    private void validateNgayDongGop(LocalDate ngayDongGop, KhoanDongGop khoanDongGop) {
        // Ngày đóng góp không được trước ngày bắt đầu thu
        if (ngayDongGop.isBefore(khoanDongGop.getNgayBatDauThu())) {
            throw new RuntimeException("Ngày đóng góp không được trước ngày bắt đầu thu (" + khoanDongGop.getNgayBatDauThu() + ")");
        }

        // Ngày đóng góp không được sau hạn đóng góp
        if (ngayDongGop.isAfter(khoanDongGop.getHanDongGop())) {
            throw new RuntimeException("Ngày đóng góp không được sau hạn đóng góp (" + khoanDongGop.getHanDongGop() + ")");
        }

        // Ngày đóng góp không được trong tương lai
        if (ngayDongGop.isAfter(LocalDate.now())) {
            throw new RuntimeException("Ngày đóng góp không được trong tương lai");
        }
    }

    private void updateKhoanDongGop(KhoanDongGop khoanDongGop, Integer soTienDongGop) {
        // Cập nhật tổng tiền thu được
        Integer tongTienMoi = khoanDongGop.getTongTienThuDuoc() + soTienDongGop;
        khoanDongGop.setTongTienThuDuoc(tongTienMoi);

        // Cập nhật số căn hộ đóng góp
        Integer soCanHoMoi = khoanDongGop.getSoCanHoDongGop() + 1;
        khoanDongGop.setSoCanHoDongGop(soCanHoMoi);

        khoanDongGopRepository.save(khoanDongGop);

        log.info("Updated KhoanDongGop: tongTien={}, soCanHo={}", tongTienMoi, soCanHoMoi);
    }
}