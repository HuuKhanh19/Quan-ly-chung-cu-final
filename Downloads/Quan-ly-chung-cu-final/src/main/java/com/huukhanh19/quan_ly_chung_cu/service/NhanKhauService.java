package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauCreationRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.NhanKhauUpdateRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.NhanKhauResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
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

@PreAuthorize("hasRole('QUANLY')")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NhanKhauService {
    NhanKhauRepository nhanKhauRepository;
    HoGiaDinhRepository hoGiaDinhRepository;
    NhanKhauMapper nhanKhauMapper;

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

        return nhanKhauMapper.toNhanKhauResponse(updatedNhanKhau);
    }

}
