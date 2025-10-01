package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChangeChuHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TachHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TachHoResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.mapper.HoGiaDinhMapper;
import com.huukhanh19.quan_ly_chung_cu.mapper.NhanKhauMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.HoGiaDinhRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('QUANLY')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HoGiaDinhService {
    HoGiaDinhRepository hoGiaDinhRepository;
    NhanKhauRepository nhanKhauRepository;
    CanHoRepository canHoRepository;
    HoGiaDinhMapper hoGiaDinhMapper;
    NhanKhauMapper nhanKhauMapper;

    @Transactional
    public void changeChuHo(String cccdChuHoCu, ChangeChuHoRequest request) {
        String cccdNhanKhauMoi = request.getCccdNhanKhauMoi();

        HoGiaDinh hoGiaDinhCu = hoGiaDinhRepository.findById(cccdChuHoCu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hộ gia đình cũ."));

        NhanKhau nhanKhauSeLamChuHo = nhanKhauRepository.findById(cccdNhanKhauMoi)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân khẩu sẽ làm chủ hộ mới."));

        if (!nhanKhauSeLamChuHo.getHoGiaDinh().getCccdChuHo().equals(cccdChuHoCu)) {
            throw new RuntimeException("Nhân khẩu được chỉ định không thuộc hộ gia đình này.");
        }

        String quanHeCuCuaChuHoMoi = nhanKhauSeLamChuHo.getQuanHe();

        HoGiaDinh hoGiaDinhMoi = hoGiaDinhMapper.createNewHoGiaDinh(hoGiaDinhCu, nhanKhauSeLamChuHo);
        hoGiaDinhRepository.save(hoGiaDinhMoi);

        String quanHeMoiCuaChuHoCu = xacDinhQuanHeNguoc(quanHeCuCuaChuHoMoi, nhanKhauSeLamChuHo.getGioiTinh());

        NhanKhau nhanKhauCuaChuHoCu = nhanKhauMapper.createNhanKhauFromHoGiaDinh(
                hoGiaDinhCu,
                hoGiaDinhMoi,
                quanHeMoiCuaChuHoCu
        );

        nhanKhauRepository.save(nhanKhauCuaChuHoCu);

        List<NhanKhau> allNhanKhauCu = nhanKhauRepository.findAllByHoGiaDinh_CccdChuHo(cccdChuHoCu);
        for (NhanKhau nk : allNhanKhauCu) {
            if (nk.getCccd().equals(cccdNhanKhauMoi)) {
                // Nhân khẩu mới trở thành chủ hộ
                nk.setQuanHe("Chủ hộ");
            } else if (nk.getCccd().equals(cccdChuHoCu)) {
                // Chủ hộ cũ đã được xử lý ở trên, giữ nguyên quan hệ mới
                // (không cần set lại vì đã set trong nhanKhauCuaChuHoCu)
            } else {
                // Cập nhật quan hệ cho các thành viên còn lại
                String quanHeCuVoiChuHoCu = nk.getQuanHe();
                String quanHeMoiVoiChuHoMoi = capNhatQuanHeThanhVien(
                        quanHeCuVoiChuHoCu,
                        quanHeCuCuaChuHoMoi,
                        quanHeMoiCuaChuHoCu,
                        nk.getGioiTinh(),
                        nhanKhauSeLamChuHo.getGioiTinh()
                );
                nk.setQuanHe(quanHeMoiVoiChuHoMoi);
            }
            nk.setHoGiaDinh(hoGiaDinhMoi);
        }
        nhanKhauRepository.saveAll(allNhanKhauCu);

        hoGiaDinhRepository.delete(hoGiaDinhCu);
    }

    /**
     * Cập nhật quan hệ của thành viên với chủ hộ mới
     * @param quanHeVoiChuHoCu Quan hệ cũ của thành viên với chủ hộ cũ (VD: "Con")
     * @param quanHeChuHoMoiVoiChuHoCu Quan hệ cũ của chủ hộ mới với chủ hộ cũ (VD: "Vợ")
     * @param quanHeChuHoCuVoiChuHoMoi Quan hệ mới của chủ hộ cũ với chủ hộ mới (VD: "Chồng")
     * @param gioiTinhThanhVien Giới tính của thành viên
     * @param gioiTinhChuHoMoi Giới tính của chủ hộ mới
     * @return Quan hệ mới của thành viên với chủ hộ mới
     */
    String capNhatQuanHeThanhVien(String quanHeVoiChuHoCu,
                                  String quanHeChuHoMoiVoiChuHoCu,
                                  String quanHeChuHoCuVoiChuHoMoi,
                                  GioiTinh gioiTinhThanhVien,
                                  GioiTinh gioiTinhChuHoMoi) {

        if (quanHeVoiChuHoCu == null || quanHeVoiChuHoCu.isBlank()) {
            return "Thành viên";
        }

        String qhCu = quanHeVoiChuHoCu.trim().toLowerCase();
        String qhChuHoMoi = quanHeChuHoMoiVoiChuHoCu != null ?
                quanHeChuHoMoiVoiChuHoCu.trim().toLowerCase() : "";

        // Trường hợp 1: Con của chủ hộ cũ + Chủ hộ mới là vợ/chồng của chủ hộ cũ
        // => Con của chủ hộ mới
        if ((qhCu.equals("con") || qhCu.equals("con trai") || qhCu.equals("con gái")) &&
                (qhChuHoMoi.equals("vợ") || qhChuHoMoi.equals("chồng"))) {
            return "Con";
        }

        // Trường hợp 2: Con của chủ hộ cũ + Chủ hộ mới là con của chủ hộ cũ
        // => Anh/chị/em với nhau
        if ((qhCu.equals("con") || qhCu.equals("con trai") || qhCu.equals("con gái")) &&
                (qhChuHoMoi.equals("con") || qhChuHoMoi.equals("con trai") || qhChuHoMoi.equals("con gái"))) {
            return "Anh/Chị/Em";
        }

        // Trường hợp 3: Cháu của chủ hộ cũ + Chủ hộ mới là con của chủ hộ cũ
        // => Con của chủ hộ mới
        if ((qhCu.equals("cháu") || qhCu.equals("cháu trai") || qhCu.equals("cháu gái")) &&
                (qhChuHoMoi.equals("con") || qhChuHoMoi.equals("con trai") || qhChuHoMoi.equals("con gái"))) {
            return "Con";
        }

        // Trường hợp 4: Bố/Mẹ của chủ hộ cũ + Chủ hộ mới là con của chủ hộ cũ
        // => Ông/Bà của chủ hộ mới
        if ((qhCu.equals("bố") || qhCu.equals("mẹ")) &&
                (qhChuHoMoi.equals("con") || qhChuHoMoi.equals("con trai") || qhChuHoMoi.equals("con gái"))) {
            return gioiTinhThanhVien == GioiTinh.Nam ? "Ông" : "Bà";
        }

        // Trường hợp 5: Ông/Bà của chủ hộ cũ + Chủ hộ mới là con của chủ hộ cũ
        // => Cụ (ông/bà cố) của chủ hộ mới
        if ((qhCu.equals("ông") || qhCu.equals("bà")) &&
                (qhChuHoMoi.equals("con") || qhChuHoMoi.equals("con trai") || qhChuHoMoi.equals("con gái"))) {
            return "Cụ";
        }

        // Trường hợp 6: Vợ/Chồng của chủ hộ cũ + Chủ hộ mới là con của chủ hộ cũ
        // => Bố/Mẹ của chủ hộ mới
        if ((qhCu.equals("vợ") || qhCu.equals("chồng")) &&
                (qhChuHoMoi.equals("con") || qhChuHoMoi.equals("con trai") || qhChuHoMoi.equals("con gái"))) {
            return gioiTinhThanhVien == GioiTinh.Nam ? "Bố" : "Mẹ";
        }

        // Các trường hợp phức tạp khác hoặc không xác định được
        return "Thành viên";
    }

    String xacDinhQuanHeNguoc(String quanHe, GioiTinh gioiTinhChuHoMoi) {
        if (quanHe == null || quanHe.isBlank()) {
            return "Thành viên";
        }

        return switch (quanHe.trim().toLowerCase()) {
            case "vợ" -> "Chồng";
            case "chồng" -> "Vợ";
            case "con", "con trai", "con gái" ->
                    (gioiTinhChuHoMoi == GioiTinh.Nam) ? "Bố" : "Mẹ";
            case "bố", "mẹ" -> "Con";
            case "cháu", "cháu trai", "cháu gái" ->
                    (gioiTinhChuHoMoi == GioiTinh.Nam) ? "Ông" : "Bà";
            case "ông", "bà" -> "Cháu";
            default -> "Thành viên";
        };
    }

    // Trong file HoGiaDinhService.java

    @Transactional // Bắt buộc: Đảm bảo tất cả các thao tác đều là một giao dịch
    public void tachHo(String cccdChuHoCu, TachHoRequest request) {
        // 1. LẤY CÁC ĐỐI TƯỢNG CẦN THIẾT TỪ DATABASE
        HoGiaDinh hoGiaDinhCu = hoGiaDinhRepository.findById(cccdChuHoCu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hộ gia đình gốc."));

        NhanKhau chuHoMoi = nhanKhauRepository.findById(request.getCccdChuHoMoi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân khẩu sẽ làm chủ hộ mới."));

        CanHo canHoMoi = canHoRepository.findById(request.getIdCanHoMoi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ mới."));

        // 2. VALIDATE DỮ LIỆU
        if (!chuHoMoi.getHoGiaDinh().getCccdChuHo().equals(cccdChuHoCu)) {
            throw new RuntimeException("Chủ hộ mới không thuộc hộ gia đình gốc.");
        }

        // 3. TẠO HỘ GIA ĐÌNH MỚI
        HoGiaDinh hoGiaDinhMoi = HoGiaDinh.builder()
                .cccdChuHo(chuHoMoi.getCccd())
                .hoTenChuHo(chuHoMoi.getHoVaTen())
                .canHo(canHoMoi) // Gán vào căn hộ mới
                .soThanhVien(1) // Bắt đầu với 1 thành viên là chủ hộ
                .ngaySinh(chuHoMoi.getNgaySinh())
                .gioiTinh(chuHoMoi.getGioiTinh())
                .trangThai("Đang ở")
                .build();
        hoGiaDinhRepository.save(hoGiaDinhMoi);

        // 4. CHUYỂN CHỦ HỘ MỚI TỪ NHÂN KHẨU CŨ SANG HỘ MỚI
        chuHoMoi.setHoGiaDinh(hoGiaDinhMoi);
        chuHoMoi.setQuanHe("Chủ hộ");
        nhanKhauRepository.save(chuHoMoi);

        // 5. CHUYỂN CÁC THÀNH VIÊN KHÁC (NẾU CÓ)
        int soNguoiChuyenDi = 1; // Bắt đầu đếm từ chủ hộ mới
        if (request.getDanhSachCccdThanhVienChuyenDi() != null && !request.getDanhSachCccdThanhVienChuyenDi().isEmpty()) {
            List<NhanKhau> cacThanhVienChuyenDi = nhanKhauRepository.findAllById(request.getDanhSachCccdThanhVienChuyenDi());

            for(NhanKhau thanhVien : cacThanhVienChuyenDi) {
                // Kiểm tra xem thành viên có thực sự thuộc hộ cũ không
                if(thanhVien.getHoGiaDinh().getCccdChuHo().equals(cccdChuHoCu)) {
                    thanhVien.setHoGiaDinh(hoGiaDinhMoi); // Chuyển sang hộ mới
                    soNguoiChuyenDi++;
                }
            }
            nhanKhauRepository.saveAll(cacThanhVienChuyenDi);
        }

        // 6. CẬP NHẬT LẠI SỐ LƯỢNG THÀNH VIÊN
        hoGiaDinhMoi.setSoThanhVien(soNguoiChuyenDi);
        hoGiaDinhRepository.save(hoGiaDinhMoi);

        int soThanhVienCu = hoGiaDinhCu.getSoThanhVien() != null ? hoGiaDinhCu.getSoThanhVien() : 0;
        hoGiaDinhCu.setSoThanhVien(soThanhVienCu - soNguoiChuyenDi);
        hoGiaDinhRepository.save(hoGiaDinhCu);
    }

}
