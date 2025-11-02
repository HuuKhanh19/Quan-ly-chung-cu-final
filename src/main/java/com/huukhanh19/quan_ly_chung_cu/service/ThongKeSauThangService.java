package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeSauThangResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.ThongKeThangResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.KhoanDongGop;
import com.huukhanh19.quan_ly_chung_cu.entity.ThoiGianThuPhi;
import com.huukhanh19.quan_ly_chung_cu.repository.KhoanDongGopRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.ThoiGianThuPhiRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.TongThanhToanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('KETOAN')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ThongKeSauThangService {

    ThoiGianThuPhiRepository thoiGianThuPhiRepository;
    TongThanhToanRepository tongThanhToanRepository;
    KhoanDongGopRepository khoanDongGopRepository;

    public ThongKeSauThangResponse thongKeSauThang() {
        log.info("Getting statistics for last 6 months");

        LocalDate today = LocalDate.now();
        log.info("Current date: {}", today);

        // 1. Lấy tất cả thời gian thu phí ĐÃ QUA (ngayThu <= hôm nay)
        List<ThoiGianThuPhi> tatCaThoiGianThu = thoiGianThuPhiRepository.findAll()
                .stream()
                .filter(t -> !t.getNgayThu().isAfter(today)) // Chỉ lấy tháng đã qua
                .sorted(Comparator.comparing(ThoiGianThuPhi::getNgayThu).reversed()) // Mới nhất lên đầu
                .collect(Collectors.toList());

        if (tatCaThoiGianThu.isEmpty()) {
            log.warn("No ThoiGianThuPhi found");
            return createEmptyResponse();
        }

        log.info("Found {} past months", tatCaThoiGianThu.size());

        // 2. Lấy 6 tháng gần nhất
        List<ThoiGianThuPhi> danhSachThoiGianThu = tatCaThoiGianThu.stream()
                .limit(6)
                .collect(Collectors.toList());

        log.info("Processing {} months", danhSachThoiGianThu.size());

        // 3. Tính toán từng tháng
        List<ThongKeThangResponse> danhSachThang = new ArrayList<>();
        int tongTienThuTatCa = 0;

        for (ThoiGianThuPhi thoiGianThuPhi : danhSachThoiGianThu) {
            // Tính tổng phí bắt buộc đã thu
            Long tongPhiBatBuoc = tongThanhToanRepository.sumSoTienDaNopByIdThoiGianThu(thoiGianThuPhi.getId());
            int phiBatBuoc = tongPhiBatBuoc != null ? tongPhiBatBuoc.intValue() : 0;

            // Tính tổng phí tự nguyện đã thu trong tháng này
            int phiTuNguyen = getTongPhiTuNguyenTheoThang(thoiGianThuPhi);

            // Tổng thu trong tháng
            int tongTienThu = phiBatBuoc + phiTuNguyen;
            tongTienThuTatCa += tongTienThu;

            // Format tháng năm từ ngayThu
            String thangNam = formatThangNamFromDate(thoiGianThuPhi.getNgayThu());

            ThongKeThangResponse thangResponse = ThongKeThangResponse.builder()
                    .thangNam(thangNam)
                    .tongTienThu(tongTienThu)
                    .build();

            danhSachThang.add(thangResponse);

            log.info("Month {} (ID={}): phiBatBuoc={}, phiTuNguyen={}, total={}",
                    thangNam, thoiGianThuPhi.getId(), phiBatBuoc, phiTuNguyen, tongTienThu);
        }

        // 4. Sắp xếp từ cũ đến mới (để hiển thị biểu đồ đẹp hơn)
        danhSachThang = danhSachThang.stream()
                .sorted(Comparator.comparing(ThongKeThangResponse::getThangNam))
                .collect(Collectors.toList());

        log.info("Total revenue for {} months: {}", danhSachThang.size(), tongTienThuTatCa);

        // 5. Trả về kết quả
        return ThongKeSauThangResponse.builder()
                .tongTienThuTatCa(tongTienThuTatCa)
                .danhSachThang(danhSachThang)
                .build();
    }

    private int getTongPhiTuNguyenTheoThang(ThoiGianThuPhi thoiGianThuPhi) {
        // Lấy các khoản đóng góp trong khoảng thời gian của tháng này
        LocalDate startDate = thoiGianThuPhi.getNgayThu();
        LocalDate endDate = thoiGianThuPhi.getHanThu();

        List<KhoanDongGop> danhSachKhoanDongGop = khoanDongGopRepository.findByNgayBatDauThuBetween(startDate, endDate);

        // Tính tổng tiền thu được từ các khoản đóng góp này
        int total = danhSachKhoanDongGop.stream()
                .mapToInt(KhoanDongGop::getTongTienThuDuoc)
                .sum();

        log.debug("Found {} dong gop for period {}-{}, total: {}",
                danhSachKhoanDongGop.size(), startDate, endDate, total);

        return total;
    }

    private String formatThangNamFromDate(LocalDate ngayThu) {
        // Format: MM-yyyy từ ngayThu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        return ngayThu.format(formatter);
    }

    private ThongKeSauThangResponse createEmptyResponse() {
        return ThongKeSauThangResponse.builder()
                .tongTienThuTatCa(0)
                .danhSachThang(new ArrayList<>())
                .build();
    }
}