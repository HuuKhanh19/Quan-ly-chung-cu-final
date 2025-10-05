package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauSearchRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.LichSuThayDoi;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.mapper.NhanKhauMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.HoGiaDinhRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.LichSuThayDoiRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('QUANLY')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NhanKhauService {
    NhanKhauRepository nhanKhauRepository;
    HoGiaDinhRepository hoGiaDinhRepository;
    NhanKhauMapper nhanKhauMapper;
    LichSuThayDoiRepository lichSuThayDoiRepository;

    @Transactional
    public NhanKhauResponse createNhanKhau(NhanKhauCreationRequest request) {
        if (nhanKhauRepository.existsById(request.getCccd())) {
            throw new RuntimeException("Nhân khẩu với CCCD này đã tồn tại.");
        }

        HoGiaDinh hoGiaDinh = hoGiaDinhRepository.findById(request.getCccdChuHo())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hộ gia đình."));

        NhanKhau nhanKhau = nhanKhauMapper.toNhanKhau(request);
        nhanKhau.setHoGiaDinh(hoGiaDinh);

        int currentMembers = hoGiaDinh.getSoThanhVien() != null ? hoGiaDinh.getSoThanhVien() : 0;
        hoGiaDinh.setSoThanhVien(currentMembers + 1);

        NhanKhau savedNhanKhau = nhanKhauRepository.save(nhanKhau);

        ghiLaiLichSu(savedNhanKhau.getCccd(), "Tạo mới nhân khẩu.");

        return nhanKhauMapper.toNhanKhauResponse(savedNhanKhau);
    }

    @Transactional
    public NhanKhauResponse updateNhanKhau(String cccd, NhanKhauUpdateRequest request) {
        NhanKhau nhanKhau = nhanKhauRepository.findById(cccd)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân khẩu."));

        String cccdChuHoMoi = request.getCccdChuHo();
        HoGiaDinh hoGiaDinhCu = nhanKhau.getHoGiaDinh();

        if (cccdChuHoMoi != null && !cccdChuHoMoi.equals(hoGiaDinhCu.getCccdChuHo())) {

            HoGiaDinh hoGiaDinhMoi = hoGiaDinhRepository.findById(cccdChuHoMoi)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy hộ gia đình mới."));

            int soThanhVienCu = hoGiaDinhCu.getSoThanhVien() != null ? hoGiaDinhCu.getSoThanhVien() : 1;
            hoGiaDinhCu.setSoThanhVien(soThanhVienCu - 1);

            int soThanhVienMoi = hoGiaDinhMoi.getSoThanhVien() != null ? hoGiaDinhMoi.getSoThanhVien() : 0;
            hoGiaDinhMoi.setSoThanhVien(soThanhVienMoi + 1);

            nhanKhau.setHoGiaDinh(hoGiaDinhMoi);
        }

        nhanKhauMapper.updateNhanKhau(nhanKhau, request);

        NhanKhau updatedNhanKhau = nhanKhauRepository.save(nhanKhau);

        ghiLaiLichSu(cccd, "Cập nhật thông tin nhân khẩu.");

        return nhanKhauMapper.toNhanKhauResponse(updatedNhanKhau);
    }

    private void ghiLaiLichSu(String cccd, String hanhDong) {
        // Lấy tên người dùng đang đăng nhập
        String nguoiThucHien = SecurityContextHolder.getContext().getAuthentication().getName();

        LichSuThayDoi log = LichSuThayDoi.builder()
                .cccdNhanKhau(cccd)
                .thongTinThayDoi(hanhDong)
                .ngayThayDoi(LocalDate.now())
                .nguoiThucHien(nguoiThucHien)
                .build();
        lichSuThayDoiRepository.save(log);
    }

    @Transactional(readOnly = true)
    public List<NhanKhauResponse> getAllNhanKhau() {
        log.info("Lấy tất cả nhân khẩu");

        List<NhanKhau> nhanKhaus = nhanKhauRepository.findAll();

        log.info("Tìm thấy {} nhân khẩu", nhanKhaus.size());

        return nhanKhauMapper.toNhanKhauResponseList(nhanKhaus);
    }

    @Transactional(readOnly = true)
    public List<NhanKhauResponse> getNhanKhauByHoGiaDinh(String cccdChuHo) {
        log.info("Lấy nhân khẩu của hộ gia đình: {}", cccdChuHo);

        List<NhanKhau> nhanKhaus = nhanKhauRepository.findAllByHoGiaDinh_CccdChuHo(cccdChuHo);

        log.info("Tìm thấy {} nhân khẩu", nhanKhaus.size());

        return nhanKhauMapper.toNhanKhauResponseList(nhanKhaus);
    }

    public List<NhanKhauResponse> searchNhanKhau(NhanKhauSearchRequest request) {
        // Xây dựng bộ tiêu chí tìm kiếm (Specification)
        Specification<NhanKhau> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Tiêu chí 1: Tìm theo CCCD (nếu có)
            if (request.getCccd() != null && !request.getCccd().isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("cccd"), request.getCccd()));
            }

            // Tiêu chí 2: Tìm theo CCCD Chủ Hộ (nếu có)
            if (request.getCccdChuHo() != null && !request.getCccdChuHo().isBlank()) {
                Join<NhanKhau, HoGiaDinh> hoGiaDinhJoin = root.join("hoGiaDinh");
                predicates.add(criteriaBuilder.equal(hoGiaDinhJoin.get("cccdChuHo"), request.getCccdChuHo()));
            }

            // Tiêu chí 3: Tìm theo Tên (tìm kiếm gần đúng)
            if (request.getHoVaTen() != null && !request.getHoVaTen().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("hoVaTen"), "%" + request.getHoVaTen() + "%"));
            }

            // Tiêu chí 4: Tìm theo Giới tính (nếu có)
            if (request.getGioiTinh() != null) {
                predicates.add(criteriaBuilder.equal(root.get("gioiTinh"), request.getGioiTinh()));
            }

            // Kết hợp tất cả các tiêu chí bằng mệnh đề AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<NhanKhau> results = nhanKhauRepository.findAll(spec);

        return results.stream()
                .map(nhanKhauMapper::toNhanKhauResponse)
                .collect(Collectors.toList());
    }

    public List<LichSuThayDoi> getHistory(String cccd) {
        // Kiểm tra xem nhân khẩu có tồn tại không
        if (!nhanKhauRepository.existsById(cccd)) {
            throw new RuntimeException("Không tìm thấy nhân khẩu.");
        }
        return lichSuThayDoiRepository.findAllByCccdNhanKhau(cccd);
    }

}
