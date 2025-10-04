package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChangeChuHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TachHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.HoGiaDinhResponse;
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

import java.util.*;
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


    @Transactional
    public void tachHo(String cccdChuHoCu, TachHoRequest request) {
        log.info("=== BẮT ĐẦU TÁCH HỘ: Chủ hộ cũ {} -> Chủ hộ mới {} ===",
                cccdChuHoCu, request.getCccdChuHoMoi());

        // ========== BƯỚC 1: LẤY DỮ LIỆU VÀ VALIDATE ==========
        HoGiaDinh hoGiaDinhCu = hoGiaDinhRepository.findById(cccdChuHoCu)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hộ gia đình gốc"));

        NhanKhau chuHoMoi = nhanKhauRepository.findById(request.getCccdChuHoMoi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân khẩu sẽ làm chủ hộ mới"));

        CanHo canHoMoi = canHoRepository.findById(request.getIdCanHoMoi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ mới"));

        // Validate chủ hộ mới thuộc hộ cũ
        if (chuHoMoi.getHoGiaDinh() == null ||
                !Objects.equals(chuHoMoi.getHoGiaDinh().getCccdChuHo(), cccdChuHoCu)) {
            throw new RuntimeException("Chủ hộ mới không thuộc hộ gia đình gốc");
        }

        // Validate căn hộ mới còn trống
        if (canHoMoi.getHoGiaDinh() != null &&
                !Objects.equals(canHoMoi.getHoGiaDinh().getCccdChuHo(), cccdChuHoCu)) {
            throw new RuntimeException("Căn hộ mới đã có hộ gia đình khác");
        }

        // Lưu quan hệ cũ của chủ hộ mới với chủ hộ cũ (QUAN TRỌNG!)
        String quanHeChuHoMoiVoiChuHoCu = chuHoMoi.getQuanHe();
        log.info("Quan hệ cũ của chủ hộ mới với chủ hộ cũ: {}", quanHeChuHoMoiVoiChuHoCu);

        // ========== BƯỚC 2: TẠO HỘ GIA ĐÌNH MỚI ==========
        HoGiaDinh hoGiaDinhMoi = HoGiaDinh.builder()
                .cccdChuHo(chuHoMoi.getCccd())
                .hoTenChuHo(chuHoMoi.getHoVaTen())
                .canHo(canHoMoi)
                .soThanhVien(1) // Sẽ cập nhật sau
                .ngaySinh(chuHoMoi.getNgaySinh())
                .gioiTinh(chuHoMoi.getGioiTinh())
                .danToc(chuHoMoi.getDanToc())
                .tonGiao(chuHoMoi.getTonGiao())
                .quocTich(chuHoMoi.getQuocTich())
                .diaChi(chuHoMoi.getDiaChi())
                .sdt(chuHoMoi.getSdt())
                .email(chuHoMoi.getEmail())
                .soXeMay(request.getSoXeMay() != null ? request.getSoXeMay() : 0)
                .soOto(request.getSoOto() != null ? request.getSoOto() : 0)
                .trangThai("Đang ở")
                .build();

        HoGiaDinh savedHoGiaDinhMoi = hoGiaDinhRepository.save(hoGiaDinhMoi);
        log.info("Đã tạo hộ gia đình mới: {}", savedHoGiaDinhMoi.getCccdChuHo());

        // Cập nhật hai chiều cho CanHo
        canHoMoi.setHoGiaDinh(savedHoGiaDinhMoi);
        canHoRepository.save(canHoMoi);

        // ========== BƯỚC 3: LẤY DANH SÁCH THÀNH VIÊN CHUYỂN ĐI ==========
        List<NhanKhau> cacThanhVienChuyenDi = new ArrayList<>();

        if (request.getDanhSachCccdThanhVienChuyenDi() != null &&
                !request.getDanhSachCccdThanhVienChuyenDi().isEmpty()) {

            List<NhanKhau> thanhVienTimThay = nhanKhauRepository
                    .findAllById(request.getDanhSachCccdThanhVienChuyenDi());

            // Lọc chỉ lấy những người thuộc hộ cũ
            for (NhanKhau tv : thanhVienTimThay) {
                if (tv.getHoGiaDinh() != null &&
                        Objects.equals(tv.getHoGiaDinh().getCccdChuHo(), cccdChuHoCu) &&
                        !tv.getCccd().equals(request.getCccdChuHoMoi())) { // Loại trừ chủ hộ mới
                    cacThanhVienChuyenDi.add(tv);
                }
            }
            log.info("Số thành viên chuyển đi (không bao gồm chủ hộ mới): {}",
                    cacThanhVienChuyenDi.size());
        }

        // ========== BƯỚC 4: CẬP NHẬT QUAN HẸ CHO CHỦ HỘ MỚI ==========
        chuHoMoi.setHoGiaDinh(savedHoGiaDinhMoi);
        chuHoMoi.setQuanHe("Chủ hộ");
        nhanKhauRepository.save(chuHoMoi);
        log.info("Đã chuyển {} thành chủ hộ mới", chuHoMoi.getHoVaTen());

        // ========== BƯỚC 5: CẬP NHẬT QUAN HỆ CHO CÁC THÀNH VIÊN KHÁC ==========
        if (!cacThanhVienChuyenDi.isEmpty()) {
            for (NhanKhau thanhVien : cacThanhVienChuyenDi) {
                String quanHeVoiChuHoCu = thanhVien.getQuanHe();

                // Tính toán quan hệ mới với chủ hộ mới
                String quanHeMoi = tinhQuanHeVoiChuHoMoi(
                        quanHeVoiChuHoCu,
                        quanHeChuHoMoiVoiChuHoCu,
                        thanhVien.getGioiTinh(),
                        chuHoMoi.getGioiTinh()
                );

                log.info("Thành viên {}: {} (với chủ hộ cũ) -> {} (với chủ hộ mới)",
                        thanhVien.getHoVaTen(), quanHeVoiChuHoCu, quanHeMoi);

                thanhVien.setHoGiaDinh(savedHoGiaDinhMoi);
                thanhVien.setQuanHe(quanHeMoi);
            }
            nhanKhauRepository.saveAll(cacThanhVienChuyenDi);
        }

        // ========== BƯỚC 6: CẬP NHẬT SỐ THÀNH VIÊN ==========
        int soNguoiChuyenDi = 1 + cacThanhVienChuyenDi.size(); // Chủ hộ mới + thành viên khác

        savedHoGiaDinhMoi.setSoThanhVien(soNguoiChuyenDi);
        hoGiaDinhRepository.save(savedHoGiaDinhMoi);

        int soThanhVienCu = hoGiaDinhCu.getSoThanhVien() != null ?
                hoGiaDinhCu.getSoThanhVien() : 0;
        hoGiaDinhCu.setSoThanhVien(Math.max(0, soThanhVienCu - soNguoiChuyenDi));
        hoGiaDinhRepository.save(hoGiaDinhCu);

        // Trừ số xe đã chuyển cho hộ mới
        if (request.getSoXeMay() != null && request.getSoXeMay() > 0) {
            int soXeMayCu = hoGiaDinhCu.getSoXeMay() != null ?
                    hoGiaDinhCu.getSoXeMay() : 0;
            hoGiaDinhCu.setSoXeMay(Math.max(0, soXeMayCu - request.getSoXeMay()));
        }

        if (request.getSoOto() != null && request.getSoOto() > 0) {
            int soOtoCu = hoGiaDinhCu.getSoOto() != null ?
                    hoGiaDinhCu.getSoOto() : 0;
            hoGiaDinhCu.setSoOto(Math.max(0, soOtoCu - request.getSoOto()));
        }

        log.info("=== KẾT THÚC TÁCH HỘ: Hộ cũ còn {} người, hộ mới có {} người ===",
                hoGiaDinhCu.getSoThanhVien(), savedHoGiaDinhMoi.getSoThanhVien());
    }

    /**
     * Tính quan hệ của thành viên với chủ hộ mới khi tách hộ
     *
     * @param quanHeVoiChuHoCu Quan hệ cũ của thành viên với chủ hộ cũ
     * @param quanHeChuHoMoiVoiChuHoCu Quan hệ cũ của chủ hộ mới với chủ hộ cũ
     * @param gioiTinhThanhVien Giới tính của thành viên
     * @param gioiTinhChuHoMoi Giới tính của chủ hộ mới
     * @return Quan hệ mới của thành viên với chủ hộ mới
     */
    private String tinhQuanHeVoiChuHoMoi(String quanHeVoiChuHoCu,
                                         String quanHeChuHoMoiVoiChuHoCu,
                                         GioiTinh gioiTinhThanhVien,
                                         GioiTinh gioiTinhChuHoMoi) {

        if (quanHeVoiChuHoCu == null || quanHeVoiChuHoCu.isBlank()) {
            return "Thành viên";
        }
        if (quanHeChuHoMoiVoiChuHoCu == null || quanHeChuHoMoiVoiChuHoCu.isBlank()) {
            return "Thành viên";
        }

        String qhThanhVien = normalizeQuanHe(quanHeVoiChuHoCu);
        String qhChuHoMoi = normalizeQuanHe(quanHeChuHoMoiVoiChuHoCu);

        log.debug("Tính quan hệ: Thành viên là '{}' của chủ hộ cũ, " +
                "Chủ hộ mới là '{}' của chủ hộ cũ", qhThanhVien, qhChuHoMoi);

        // ===== TRƯỜNG HỢP 1: Chủ hộ mới là vợ/chồng của chủ hộ cũ =====
        if (qhChuHoMoi.equals("vợ") || qhChuHoMoi.equals("chồng")) {
            return switch (qhThanhVien) {
                case "con" -> "Con"; // Con của chủ hộ cũ vẫn là con của vợ/chồng
                case "cháu" -> "Cháu";
                case "bố", "mẹ" -> gioiTinhThanhVien == GioiTinh.Nam ?
                        "Bố chồng/vợ" : "Mẹ chồng/vợ";
                case "ông", "bà" -> gioiTinhThanhVien == GioiTinh.Nam ? "Ông" : "Bà";
                case "anh/chị/em" -> "Anh/Chị/Em chồng/vợ";
                default -> "Thành viên";
            };
        }

        // ===== TRƯỜNG HỢP 2: Chủ hộ mới là con của chủ hộ cũ =====
        if (qhChuHoMoi.equals("con")) {
            return switch (qhThanhVien) {
                case "vợ", "chồng" -> gioiTinhThanhVien == GioiTinh.Nam ? "Bố" : "Mẹ";
                case "con" -> "Anh/Chị/Em"; // Anh chị em với chủ hộ mới
                case "cháu" -> "Con"; // Cháu của chủ hộ cũ có thể là con của chủ hộ mới
                case "bố", "mẹ" -> gioiTinhThanhVien == GioiTinh.Nam ? "Ông" : "Bà";
                case "ông", "bà" -> "Cụ";
                case "anh/chị/em" -> "Chú/Bác/Cô/Dì";
                default -> "Thành viên";
            };
        }

        // ===== TRƯỜNG HỢP 3: Chủ hộ mới là anh/chị/em của chủ hộ cũ =====
        if (qhChuHoMoi.equals("anh/chị/em")) {
            return switch (qhThanhVien) {
                case "vợ" -> "Em dâu/Chị dâu";
                case "chồng" -> "Em rể/Anh rể";
                case "con" -> "Cháu"; // Con của chủ hộ cũ là cháu của anh/chị/em
                case "cháu" -> "Chắt";
                case "bố", "mẹ" -> gioiTinhThanhVien == GioiTinh.Nam ? "Bố" : "Mẹ";
                case "ông", "bà" -> gioiTinhThanhVien == GioiTinh.Nam ? "Ông" : "Bà";
                case "anh/chị/em" -> "Anh/Chị/Em";
                default -> "Thành viên";
            };
        }

        // ===== TRƯỜNG HỢP 4: Chủ hộ mới là bố/mẹ của chủ hộ cũ =====
        if (qhChuHoMoi.equals("bố") || qhChuHoMoi.equals("mẹ")) {
            return switch (qhThanhVien) {
                case "vợ" -> "Con dâu";
                case "chồng" -> "Con rể";
                case "con" -> "Cháu"; // Con của chủ hộ cũ là cháu của ông/bà
                case "cháu" -> "Chắt";
                case "bố" -> "Chồng";
                case "mẹ" -> "Vợ";
                case "ông", "bà" -> gioiTinhThanhVien == GioiTinh.Nam ? "Bố" : "Mẹ";
                case "anh/chị/em" -> "Con";
                default -> "Thành viên";
            };
        }

        // ===== TRƯỜNG HỢP 5: Chủ hộ mới là cháu của chủ hộ cũ =====
        if (qhChuHoMoi.equals("cháu")) {
            return switch (qhThanhVien) {
                case "vợ", "chồng" -> gioiTinhThanhVien == GioiTinh.Nam ? "Ông" : "Bà";
                case "con" -> gioiTinhThanhVien == GioiTinh.Nam ? "Bố" : "Mẹ";
                case "cháu" -> "Anh/Chị/Em"; // Các cháu với nhau là anh/chị/em
                case "bố", "mẹ" -> "Cụ";
                case "anh/chị/em" -> "Chú/Bác/Cô/Dì";
                default -> "Thành viên";
            };
        }

        // ===== TRƯỜNG HỢP 6: Chủ hộ mới là ông/bà của chủ hộ cũ =====
        if (qhChuHoMoi.equals("ông") || qhChuHoMoi.equals("bà")) {
            return switch (qhThanhVien) {
                case "vợ" -> "Cháu dâu";
                case "chồng" -> "Cháu rể";
                case "con" -> "Chắt";
                case "bố", "mẹ" -> "Con";
                case "ông" -> "Chồng";
                case "bà" -> "Vợ";
                case "anh/chị/em" -> "Cháu";
                default -> "Thành viên";
            };
        }

        // Trường hợp không xác định
        log.warn("Không xác định được quan hệ: Thành viên '{}', Chủ hộ mới '{}'",
                qhThanhVien, qhChuHoMoi);
        return "Thành viên";
    }

    /**
     * Chuẩn hóa tên quan hệ về dạng lowercase và gộp các biến thể
     */
    private String normalizeQuanHe(String quanHe) {
        if (quanHe == null || quanHe.isBlank()) {
            return "";
        }

        String normalized = quanHe.trim().toLowerCase();

        // Gộp các biến thể của con
        if (normalized.equals("con trai") || normalized.equals("con gái") ||
                normalized.equals("con")) {
            return "con";
        }

        // Gộp các biến thể của cháu
        if (normalized.equals("cháu trai") || normalized.equals("cháu gái") ||
                normalized.equals("cháu")) {
            return "cháu";
        }

        // Gộp anh/chị/em
        if (normalized.equals("anh") || normalized.equals("chị") ||
                normalized.equals("em") || normalized.equals("anh/chị/em") ||
                normalized.contains("anh") || normalized.contains("chị") ||
                normalized.contains("em")) {
            return "anh/chị/em";
        }

        // Chuẩn hóa chủ hộ
        if (normalized.equals("chủ hộ") || normalized.equals("chu ho")) {
            return "chủ hộ";
        }

        return normalized;
    }

    @Transactional(readOnly = true)
    public List<HoGiaDinhResponse> getAllHoGiaDinh() {
        log.info("Lấy tất cả hộ gia đình");

        List<HoGiaDinh> hoGiaDinhs = hoGiaDinhRepository.findAll();

        log.info("Tìm thấy {} hộ gia đình", hoGiaDinhs.size());

        return hoGiaDinhMapper.toHoGiaDinhResponseList(hoGiaDinhs);
    }

//    @Transactional(readOnly = true)
//    public HoGiaDinhResponse getHoGiaDinhById(String cccdChuHo) {
//        log.info("Lấy hộ gia đình: {}", cccdChuHo);
//
//        HoGiaDinh hoGiaDinh = hoGiaDinhRepository.findById(cccdChuHo)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy hộ gia đình"));
//
//        return hoGiaDinhMapper.toHoGiaDinhResponse(hoGiaDinh);
//    }
}
