package com.huukhanh19.quan_ly_chung_cu.service;

import com.huukhanh19.quan_ly_chung_cu.dto.request.TamVangRequest;
import com.huukhanh19.quan_ly_chung_cu.dto.response.TamVangResponse;
import com.huukhanh19.quan_ly_chung_cu.entity.BangTamVang;
import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
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

    @Transactional
    public TamVangResponse createTamVang(TamVangRequest request) {
        // 1. Kiểm tra xem nhân khẩu và căn hộ có tồn tại không
        NhanKhau nhanKhau = nhanKhauRepository.findById(request.getCccd())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân khẩu."));

        CanHo canHo = canHoRepository.findById(request.getIdCanHo())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy căn hộ."));

        // (Tùy chọn) Kiểm tra xem nhân khẩu có thực sự thuộc căn hộ đó không
        if (!nhanKhau.getHoGiaDinh().getCanHo().getIdCanHo().equals(request.getIdCanHo())) {
            throw new RuntimeException("Nhân khẩu không thuộc căn hộ đã đăng ký.");
        }

        // 2. Chuyển đổi từ DTO sang Entity
        BangTamVang tamVang = bangTamVangMapper.toBangTamVang(request);
        tamVang.setHoVaTen(nhanKhau.getHoVaTen()); // Lấy họ tên từ nhân khẩu
        tamVang.setCanHo(canHo);
        tamVang.setTrangThai("Chờ duyệt"); // Trạng thái mặc định

        // 3. Lưu vào database
        BangTamVang savedTamVang = bangTamVangRepository.save(tamVang);

        // 4. Trả về response
        return bangTamVangMapper.toTamVangResponse(savedTamVang);
    }
}
