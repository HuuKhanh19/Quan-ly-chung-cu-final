package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeCanHoResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeThuPhiResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import com.huukhanh19.quan_ly_chung_cu.entity.TongThanhToan;
import com.huukhanh19.quan_ly_chung_cu.enums.TrangThaiThanhToan;
import com.huukhanh19.quan_ly_chung_cu.mapper.ThongKeMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.ThoiGianThuPhiRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.TongThanhToanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ThongKeThuPhiService {

    TongThanhToanRepository tongThanhToanRepository;
    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    ThongKeMapper thongKeMapper;

    public ThongKeThuPhiResponse thongKeThuPhiByThoiGianThu(Integer idThoiGianThu) {
        log.info("Thống kê thu phí for thoiGianThu: {}", idThoiGianThu);

        // 1. Validate và lấy thời gian thu phí
        ThoiGianThuPhi thoiGianThuPhi = thoiGianThuPhiRepository.findById(idThoiGianThu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thời gian thu phí với ID: " + idThoiGianThu));

        // 2. Lấy tất cả TongThanhToan của kỳ này
        List<TongThanhToan> danhSachTongThanhToan = tongThanhToanRepository.findByIdThoiGianThu(idThoiGianThu);

        if (danhSachTongThanhToan.isEmpty()) {
            log.warn("Không tìm thấy dữ liệu thanh toán cho kỳ thu phí này");
            return createEmptyThongKeResponse(thoiGianThuPhi);
        }

        log.info("Found {} records TongThanhToan", danhSachTongThanhToan.size());

        // 3. Tính toán thống kê
        int tongCanHo = danhSachTongThanhToan.size();
        int soCanHoDaNop = 0;
        int soCanHoChuaNop = 0;

        int tongPhiPhaiThu = 0;
        int tongTienDaThu = 0;
        int tongConThieu = 0;

        int tongPhiChungCu = 0;
        int tongTienIch = 0;
        int tongGuiXe = 0;

        List<ThongKeCanHoResponse> danhSachCanHo = new ArrayList<>();

        for (TongThanhToan tongThanhToan : danhSachTongThanhToan) {
            // Thống kê từng căn hộ
            ThongKeCanHoResponse canHoResponse = thongKeMapper.toThongKeCanHoResponse(tongThanhToan);
            danhSachCanHo.add(canHoResponse);

            // Đếm số căn hộ theo trạng thái
            if (tongThanhToan.getTrangThai() == TrangThaiThanhToan.DA_THANH_TOAN) {
                soCanHoDaNop++;
            } else if (tongThanhToan.getTrangThai() == TrangThaiThanhToan.CHUA_THANH_TOAN) {
                soCanHoChuaNop++;
            }

            // Tính tổng tiền
            tongPhiPhaiThu += tongThanhToan.getTongPhi();
            tongTienDaThu += tongThanhToan.getSoTienDaNop();
            tongConThieu += tongThanhToan.getSoDu();

            // Tính tổng theo loại phí
            tongPhiChungCu += tongThanhToan.getTongPhiChungCu();
            tongTienIch += tongThanhToan.getTongTienIch();
            tongGuiXe += tongThanhToan.getTongGuiXe();
        }

        // 4. Sắp xếp danh sách căn hộ (chưa thanh toán lên đầu, sau đó nộp một phần, cuối cùng đã thanh toán)
        danhSachCanHo = danhSachCanHo.stream()
                .sorted((c1, c2) -> {
                    // Sắp xếp theo trạng thái: CHUA_THANH_TOAN -> THANH_TOAN_MOT_PHAN -> DA_THANH_TOAN
                    int trangThaiCompare = c1.getTrangThai().compareTo(c2.getTrangThai());
                    if (trangThaiCompare != 0) {
                        return trangThaiCompare;
                    }
                    // Cùng trạng thái thì sắp xếp theo số dư giảm dần
                    return c2.getSoDu().compareTo(c1.getSoDu());
                })
                .collect(Collectors.toList());

        // 5. Trả về kết quả
        return ThongKeThuPhiResponse.builder()
                .ngayThu(thoiGianThuPhi.getNgayThu())
                .hanThu(thoiGianThuPhi.getHanThu())
                .tongCanHo(tongCanHo)
                .soCanHoDaNop(soCanHoDaNop)
                .soCanHoChuaNop(soCanHoChuaNop)
                .tongPhiPhaiThu(tongPhiPhaiThu)
                .tongTienDaThu(tongTienDaThu)
                .tongConThieu(tongConThieu)
                .tongPhiChungCu(tongPhiChungCu)
                .tongTienIch(tongTienIch)
                .tongGuiXe(tongGuiXe)
                .danhSachCanHo(danhSachCanHo)
                .build();
    }

    private ThongKeThuPhiResponse createEmptyThongKeResponse(ThoiGianThuPhi thoiGianThuPhi) {
        return ThongKeThuPhiResponse.builder()
                .ngayThu(thoiGianThuPhi.getNgayThu())
                .hanThu(thoiGianThuPhi.getHanThu())
                .tongCanHo(0)
                .soCanHoDaNop(0)
                .soCanHoChuaNop(0)
                .tongPhiPhaiThu(0)
                .tongTienDaThu(0)
                .tongConThieu(0)
                .tongPhiChungCu(0)
                .tongTienIch(0)
                .tongGuiXe(0)
                .danhSachCanHo(new ArrayList<>())
                .build();
    }
}