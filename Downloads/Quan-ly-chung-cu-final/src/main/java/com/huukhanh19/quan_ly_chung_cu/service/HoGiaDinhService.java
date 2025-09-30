package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.ChangeChuHoRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.mapper.HoGiaDinhMapper;
import com.huukhanh19.quan_ly_chung_cu.mapper.NhanKhauMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.HoGiaDinhRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('QUANLY')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class HoGiaDinhService {
    HoGiaDinhRepository hoGiaDinhRepository;
    NhanKhauRepository nhanKhauRepository;
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
                nk.setQuanHe("Chủ hộ");
            }
            nk.setHoGiaDinh(hoGiaDinhMoi);
        }
        nhanKhauRepository.saveAll(allNhanKhauCu);

        hoGiaDinhRepository.delete(hoGiaDinhCu);
    }

    String xacDinhQuanHeNguoc(String quanHe, GioiTinh gioiTinhChuHoMoi) {
        // Trả về "Thành viên" nếu không có thông tin quan hệ
        if (quanHe == null || quanHe.isBlank()) {
            return "Thành viên";
        }

        // Sử dụng switch expression để code ngắn gọn và an toàn hơn
        return switch (quanHe.trim().toLowerCase()) {
            case "Vợ" -> "Chồng";
            case "Chồng" -> "Vợ";

            case "Con", "Con trai", "Con gái" ->
                    (gioiTinhChuHoMoi == GioiTinh.Nam) ? "Bố" : "Mẹ";

            case "Bố", "Mẹ" -> "Con";

            case "Cháu", "Cháu trai", "Cháu gái" ->
                    (gioiTinhChuHoMoi == GioiTinh.Nam) ? "Ông" : "Bà";

            case "Ông", "Bà" -> "Cháu";

            // Các trường hợp khác không xác định được thì mặc định là "Thành viên"
            default -> "Thành viên";
        };
    }

}
