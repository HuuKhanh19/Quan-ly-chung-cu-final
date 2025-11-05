package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.BienDongThuPhiResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.*;
import com.huukhanh19.quan_ly_chung_cu.enums.LoaiBienDongThuPhi;
import com.huukhanh19.quan_ly_chung_cu.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BienDongThuPhiService {

    BienDongThuPhiRepository bienDongThuPhiRepository;
    CanHoRepository canHoRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    KhoanDongGopRepository khoanDongGopRepository;

    public List<BienDongThuPhiResponse> get10BienDongGanNhat() {
        log.info("Getting top 10 recent bien dong thu phi");

        List<BienDongThuPhi> danhSachBienDong = bienDongThuPhiRepository.findTop10ByOrderByNgayTaoDesc();

        if (danhSachBienDong.isEmpty()) {
            log.warn("No bien dong thu phi found");
            return List.of();
        }

        // Giới hạn 10 record
        if (danhSachBienDong.size() > 10) {
            danhSachBienDong = danhSachBienDong.subList(0, 10);
        }

        log.info("Found {} bien dong thu phi", danhSachBienDong.size());

        return danhSachBienDong.stream()
                .map(this::toBienDongResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void ghiNhanBienDongThuPhi(LoaiBienDongThuPhi loaiBienDong,
                                      Integer idCanHo,
                                      Integer idThoiGianThu,
                                      Integer idKhoanDongGop,
                                      Integer soTien,
                                      String noiDung) {
        String nguoiTao = SecurityContextHolder.getContext().getAuthentication().getName();

        BienDongThuPhi bienDong = BienDongThuPhi.builder()
                .loaiBienDong(loaiBienDong)
                .idCanHo(idCanHo)
                .idThoiGianThu(idThoiGianThu)
                .idKhoanDongGop(idKhoanDongGop)
                .soTien(soTien)
                .noiDung(noiDung)
                .ngayTao(LocalDateTime.now())
                .nguoiTao(nguoiTao)
                .build();

        bienDongThuPhiRepository.save(bienDong);

        log.info("Recorded bien dong thu phi: {} - CanHo: {}, Amount: {}",
                loaiBienDong.getDisplayName(), idCanHo, soTien);
    }

    private BienDongThuPhiResponse toBienDongResponse(BienDongThuPhi bienDong) {
        // Lấy thông tin căn hộ
        String soNha = "N/A";
        try {
            CanHo canHo = canHoRepository.findById(bienDong.getIdCanHo()).orElse(null);
            if (canHo != null) {
                soNha = canHo.getSoNha();
            }
        } catch (Exception e) {
            log.error("Error getting canHo info: {}", e.getMessage());
        }

        // Tạo text mô tả
        String text = taoTextMoTa(bienDong, soNha);

        return BienDongThuPhiResponse.builder()
                .id(bienDong.getId())
                .type(bienDong.getLoaiBienDong().getDisplayName())
                .text(text)
                .ngayTao(bienDong.getNgayTao())
                .build();
    }

    private String taoTextMoTa(BienDongThuPhi bienDong, String soNha) {
        String soTienFormat = String.format("%,d", bienDong.getSoTien());

        switch (bienDong.getLoaiBienDong()) {
            case THU_PHI_BAT_BUOC:
                // Lấy tháng từ idThoiGianThu
                String thangNam = getThangNamFromId(bienDong.getIdThoiGianThu());
                return String.format("Hộ gia đình căn hộ %s vừa thanh toán phí tháng %s.",
                        soNha, thangNam);

            case DONG_GOP_TU_NGUYEN:
                // Lấy tên khoản đóng góp
                String tenKhoanDongGop = getTenKhoanDongGop(bienDong.getIdKhoanDongGop());
                return String.format("Căn hộ %s đã ủng hộ %s %s VNĐ.",
                        soNha, tenKhoanDongGop, soTienFormat);

            default:
                return String.format("Căn hộ %s có biến động thu phí.", soNha);
        }
    }

    private String getThangNamFromId(Integer idThoiGianThu) {
        if (idThoiGianThu == null) {
            return "N/A";
        }

        try {
            ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(idThoiGianThu).orElse(null);
            if (thoiGianThuPhi != null && thoiGianThuPhi.getNgayThu() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
                return thoiGianThuPhi.getNgayThu().format(formatter);
            }
        } catch (Exception e) {
            log.error("Error getting thang nam: {}", e.getMessage());
        }

        return "N/A";
    }

    private String getTenKhoanDongGop(Integer idKhoanDongGop) {
        if (idKhoanDongGop == null) {
            return "quỹ";
        }

        try {
            KhoanDongGop khoanDongGop = khoanDongGopRepository.findById(idKhoanDongGop).orElse(null);
            if (khoanDongGop != null) {
                return khoanDongGop.getTenKhoanDongGop();
            }
        } catch (Exception e) {
            log.error("Error getting ten khoan dong gop: {}", e.getMessage());
        }

        return "quỹ";
    }
}