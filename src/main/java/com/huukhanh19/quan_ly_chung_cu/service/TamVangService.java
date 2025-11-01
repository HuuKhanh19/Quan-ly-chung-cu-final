package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TamTruRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.request.TamVangRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TamVangResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.BangTamVang;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.LoaiDangKy;
import com.huukhanh19.quan_ly_chung_cu.mapper.BangTamVangMapper;
import com.huukhanh19.quan_ly_chung_cu.repository.BangTamVangRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@PreAuthorize("hasRole('QUANLY')")
@Slf4j
public class TamVangService {
    BangTamVangRepository bangTamVangRepository;
    NhanKhauRepository nhanKhauRepository;
    CanHoRepository canHoRepository;
    BangTamVangMapper bangTamVangMapper;

    // Logic cho Tạm vắng (dành cho cư dân đã tồn tại)
    @Transactional
    public TamVangResponse createTamVang(TamVangRequest request) {
        // VALIDATE: Kiểm tra CCCD phải có trong NhanKhau
        NhanKhau nhanKhau = nhanKhauRepository.findById(request.getCccd())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân khẩu với CCCD này."));

        // VALIDATE: Kiểm tra idCanHo phải đúng với căn hộ của nhân khẩu đó
        if (nhanKhau.getHoGiaDinh() == null || nhanKhau.getHoGiaDinh().getCanHo() == null) {
            throw new RuntimeException("Nhân khẩu này không thuộc hộ gia đình hoặc căn hộ nào.");
        }

        // Tạo đối tượng BangTamVang
        BangTamVang tamVang = new BangTamVang();
        tamVang.setCccd(nhanKhau.getCccd());
        tamVang.setHoVaTen(nhanKhau.getHoVaTen());
        tamVang.setCanHo(nhanKhau.getHoGiaDinh().getCanHo()); // Lấy CanHo trực tiếp từ nhân khẩu
        tamVang.setNgayBatDau(request.getNgayBatDau());
        tamVang.setNgayKetThuc(request.getNgayKetThuc());
        tamVang.setLyDo(request.getLyDo());
        tamVang.setLoaiDangKy(LoaiDangKy.TAM_VANG);
        tamVang.setTrangThai("Thành Công");

        BangTamVang saved = bangTamVangRepository.save(tamVang);
        return bangTamVangMapper.toTamVangResponse(saved);
    }

    // Logic cho Tạm trú (dành cho người ngoài)
    @Transactional
    public TamVangResponse createTamTru(TamTruRequest request) {
        // VALIDATE: Kiểm tra CCCD không được có trong NhanKhau (phải là người ngoài)
        if (nhanKhauRepository.existsById(request.getCccd())) {
            throw new RuntimeException("Người này đã là nhân khẩu của chung cư, không thể đăng ký tạm trú.");
        }

        // Kiểm tra xem căn hộ mà người này sẽ ở có tồn tại không
        CanHo canHo = canHoRepository.findById(request.getIdCanHo())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ."));

        BangTamVang tamTru = new BangTamVang();
        tamTru.setCccd(request.getCccd());
        tamTru.setHoVaTen(request.getHoVaTen());
        tamTru.setCanHo(canHo); // Gán căn hộ sẽ ở
        tamTru.setNgayBatDau(request.getNgayBatDau());
        tamTru.setNgayKetThuc(request.getNgayKetThuc());
        tamTru.setLyDo(request.getLyDo());
        tamTru.setLoaiDangKy(LoaiDangKy.TAM_TRU);
        tamTru.setTrangThai("Thành công");

        BangTamVang saved = bangTamVangRepository.save(tamTru);
        return bangTamVangMapper.toTamVangResponse(saved);
    }

}
