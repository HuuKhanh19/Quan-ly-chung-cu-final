package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.BienDongCuDanResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.BienDongCuDan;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.enums.LoaiBienDong;
import com.huukhanh19.quan_ly_chung_cu.repository.BienDongCuDanRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('QUANLY')")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BienDongCuDanService {

    BienDongCuDanRepository bienDongCuDanRepository;
    CanHoRepository canHoRepository;

    public List<BienDongCuDanResponse> get10BienDongGanNhat() {
        log.info("Getting top 10 recent bien dong cu dan");

        List<BienDongCuDan> danhSachBienDong = bienDongCuDanRepository.findTop10ByOrderByNgayTaoDesc();

        if (danhSachBienDong.isEmpty()) {
            log.warn("No bien dong cu dan found");
            return List.of();
        }

        // Giới hạn 10 record
        if (danhSachBienDong.size() > 10) {
            danhSachBienDong = danhSachBienDong.subList(0, 10);
        }

        log.info("Found {} bien dong cu dan", danhSachBienDong.size());

        return danhSachBienDong.stream()
                .map(this::toBienDongResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void ghiNhanBienDong(LoaiBienDong loaiBienDong,
                                Integer idCanHo,
                                String cccdNhanKhau,
                                String hoVaTen,
                                String noiDung) {
        String nguoiTao = SecurityContextHolder.getContext().getAuthentication().getName();

        BienDongCuDan bienDong = BienDongCuDan.builder()
                .loaiBienDong(loaiBienDong)
                .idCanHo(idCanHo)
                .cccdNhanKhau(cccdNhanKhau)
                .hoVaTen(hoVaTen)
                .noiDung(noiDung)
                .ngayTao(LocalDateTime.now())
                .nguoiTao(nguoiTao)
                .build();

        bienDongCuDanRepository.save(bienDong);

        log.info("Recorded bien dong: {} - {} (CanHo: {})",
                loaiBienDong.getDisplayName(), hoVaTen, idCanHo);
    }

    private BienDongCuDanResponse toBienDongResponse(BienDongCuDan bienDong) {
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

        return BienDongCuDanResponse.builder()
                .id(bienDong.getId())
                .loai(bienDong.getLoaiBienDong().getDisplayName())
                .text(text)
                .ngayTao(bienDong.getNgayTao())
                .build();
    }

    private String taoTextMoTa(BienDongCuDan bienDong, String soNha) {
        String hoVaTen = bienDong.getHoVaTen() != null ? bienDong.getHoVaTen() : "N/A";

        switch (bienDong.getLoaiBienDong()) {
            case TAM_TRU:
                return String.format("%s (Căn hộ %s) vừa đăng ký tạm trú.", hoVaTen, soNha);

            case TAM_VANG:
                return String.format("%s (Căn hộ %s) vừa đăng ký tạm vắng.", hoVaTen, soNha);

            case THEM_NHAN_KHAU:
                return String.format("Thêm nhân khẩu %s vào căn hộ %s.", hoVaTen, soNha);

            case SUA_NHAN_KHAU:
                if (bienDong.getNoiDung() != null) {
                    return String.format("Sửa thông tin nhân khẩu %s (Căn hộ %s): %s",
                            hoVaTen, soNha, bienDong.getNoiDung());
                }
                return String.format("Sửa thông tin nhân khẩu %s (Căn hộ %s).", hoVaTen, soNha);

            case XOA_NHAN_KHAU:
                return String.format("Xóa nhân khẩu %s khỏi căn hộ %s.", hoVaTen, soNha);

            default:
                return String.format("%s (Căn hộ %s) có biến động.", hoVaTen, soNha);
        }
    }
}