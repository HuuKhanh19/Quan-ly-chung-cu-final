package com.huukhanh19.quan_ly_chung_cu.configuration;

import com.huukhanh19.quan_ly_chung_cu.entity.CanHo;
import com.huukhanh19.quan_ly_chung_cu.entity.HoGiaDinh;
import com.huukhanh19.quan_ly_chung_cu.entity.NhanKhau;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.repository.CanHoRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.HoGiaDinhRepository;
import com.huukhanh19.quan_ly_chung_cu.repository.NhanKhauRepository;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DataInitializer {

    CanHoRepository canHoRepository;
    HoGiaDinhRepository hoGiaDinhRepository;
    NhanKhauRepository nhanKhauRepository;

    @PostConstruct
    public void initData() {
        if (canHoRepository.count() > 0) {
            log.info("Dữ liệu đã tồn tại, bỏ qua khởi tạo.");
            return;
        }

        log.info("=== BẮT ĐẦU KHỞI TẠO DỮ LIỆU MẪU ===");

        // =======================
        // 1. Căn hộ A101 (Hộ có 3 người)
        // =======================
        CanHo canHo1 = new CanHo();
        canHo1.setSoNha("A101");
        canHo1.setLoaiCanHo("Chung cư");
        canHo1.setDienTich(new BigDecimal("75.5"));
        canHo1.setDiaChi("Tòa A, Chung cư XYZ, Hà Nội");
        canHoRepository.save(canHo1);

        HoGiaDinh hoGiaDinh1 = new HoGiaDinh();
        hoGiaDinh1.setCccdChuHo("012345678999");
        hoGiaDinh1.setHoTenChuHo("Nguyễn Văn A");
        hoGiaDinh1.setGioiTinh(GioiTinh.NAM);
        hoGiaDinh1.setNgaySinh(LocalDate.of(1980, 5, 20));
        hoGiaDinh1.setDanToc("Kinh");
        hoGiaDinh1.setTonGiao("Không");
        hoGiaDinh1.setQuocTich("Việt Nam");
        hoGiaDinh1.setDiaChi("Tòa A, Chung cư XYZ, Hà Nội");
        hoGiaDinh1.setSdt("0901234567");
        hoGiaDinh1.setEmail("vana@example.com");
        hoGiaDinh1.setSoXeMay(2);
        hoGiaDinh1.setSoOto(1);
        hoGiaDinh1.setSoThanhVien(3);
        hoGiaDinh1.setTrangThai("Đang ở");
        hoGiaDinh1.setCanHo(canHo1);
        hoGiaDinhRepository.save(hoGiaDinh1);

        NhanKhau chuHo1 = NhanKhau.builder()
                .cccd("012345678999")
                .hoGiaDinh(hoGiaDinh1)
                .hoVaTen("Nguyễn Văn A")
                .gioiTinh(GioiTinh.NAM)
                .ngaySinh(LocalDate.of(1980, 5, 20))
                .danToc("Kinh")
                .tonGiao("Không")
                .quocTich("Việt Nam")
                .diaChi("Tòa A, Chung cư XYZ, Hà Nội")
                .sdt("0901234567")
                .email("vana@example.com")
                .quanHe("Chủ hộ")
                .trangThai("Đang ở")
                .build();

        NhanKhau vo1 = NhanKhau.builder()
                .cccd("987654321111")
                .hoGiaDinh(hoGiaDinh1)
                .hoVaTen("Trần Thị B")
                .gioiTinh(GioiTinh.NU)
                .ngaySinh(LocalDate.of(1985, 3, 15))
                .danToc("Kinh")
                .tonGiao("Không")
                .quocTich("Việt Nam")
                .diaChi("Tòa A, Chung cư XYZ, Hà Nội")
                .sdt("0911222333")
                .email("tranthib@example.com")
                .quanHe("Vợ")
                .trangThai("Đang ở")
                .build();

        NhanKhau con1 = NhanKhau.builder()
                .cccd("112233445566")
                .hoGiaDinh(hoGiaDinh1)
                .hoVaTen("Nguyễn Văn C")
                .gioiTinh(GioiTinh.KHAC)
                .ngaySinh(LocalDate.of(2010, 9, 10))
                .danToc("Kinh")
                .tonGiao("Không")
                .quocTich("Việt Nam")
                .diaChi("Tòa A, Chung cư XYZ, Hà Nội")
                .sdt("0988777666")
                .email("nguyenvanc@example.com")
                .quanHe("Con")
                .trangThai("Đang ở")
                .build();

        nhanKhauRepository.saveAll(Set.of(chuHo1, vo1, con1));
        log.info("✔ Khởi tạo xong hộ gia đình tại căn hộ A101 với 3 nhân khẩu.");

        // =======================
        // 2. Căn hộ A102 (Hộ có 2 người)
        // =======================
        CanHo canHo2 = new CanHo();
        canHo2.setSoNha("A102");
        canHo2.setLoaiCanHo("Chung cư");
        canHo2.setDienTich(new BigDecimal("60.0"));
        canHo2.setDiaChi("Tòa A, Chung cư XYZ, Hà Nội");
        canHoRepository.save(canHo2);

        HoGiaDinh hoGiaDinh2 = new HoGiaDinh();
        hoGiaDinh2.setCccdChuHo("123456789000");
        hoGiaDinh2.setHoTenChuHo("Phạm Văn D");
        hoGiaDinh2.setGioiTinh(GioiTinh.NAM);
        hoGiaDinh2.setNgaySinh(LocalDate.of(1990, 7, 10));
        hoGiaDinh2.setDanToc("Kinh");
        hoGiaDinh2.setTonGiao("Không");
        hoGiaDinh2.setQuocTich("Việt Nam");
        hoGiaDinh2.setDiaChi("Tòa A, Chung cư XYZ, Hà Nội");
        hoGiaDinh2.setSdt("0933222111");
        hoGiaDinh2.setEmail("phamvand@example.com");
        hoGiaDinh2.setSoXeMay(1);
        hoGiaDinh2.setSoOto(0);
        hoGiaDinh2.setSoThanhVien(2);
        hoGiaDinh2.setTrangThai("Đang ở");
        hoGiaDinh2.setCanHo(canHo2);
        hoGiaDinhRepository.save(hoGiaDinh2);

        NhanKhau chuHo2 = NhanKhau.builder()
                .cccd("123456789000")
                .hoGiaDinh(hoGiaDinh2)
                .hoVaTen("Phạm Văn D")
                .gioiTinh(GioiTinh.NAM)
                .ngaySinh(LocalDate.of(1990, 7, 10))
                .danToc("Kinh")
                .tonGiao("Không")
                .quocTich("Việt Nam")
                .diaChi("Tòa A, Chung cư XYZ, Hà Nội")
                .sdt("0933222111")
                .email("phamvand@example.com")
                .quanHe("Chủ hộ")
                .trangThai("Đang ở")
                .build();

        NhanKhau vo2 = NhanKhau.builder()
                .cccd("223344556677")
                .hoGiaDinh(hoGiaDinh2)
                .hoVaTen("Lê Thị E")
                .gioiTinh(GioiTinh.NU)
                .ngaySinh(LocalDate.of(1992, 4, 25))
                .danToc("Kinh")
                .tonGiao("Không")
                .quocTich("Việt Nam")
                .diaChi("Tòa A, Chung cư XYZ, Hà Nội")
                .sdt("0977555444")
                .email("lethie@example.com")
                .quanHe("Vợ")
                .trangThai("Đang ở")
                .build();

        nhanKhauRepository.saveAll(Set.of(chuHo2, vo2));
        log.info("✔ Khởi tạo xong hộ gia đình tại căn hộ A102 với 2 nhân khẩu.");

        // =======================
        // 3. Căn hộ A103 (Trống)
        // =======================
        CanHo canHo3 = new CanHo();
        canHo3.setSoNha("A103");
        canHo3.setLoaiCanHo("Chung cư");
        canHo3.setDienTich(new BigDecimal("80.0"));
        canHo3.setDiaChi("Tòa A, Chung cư XYZ, Hà Nội");
        canHoRepository.save(canHo3);

        log.info("✔ Khởi tạo xong căn hộ A103 (hiện đang trống).");

        log.info("=== KHỞI TẠO DỮ LIỆU MẪU HOÀN TẤT ===");
    }
}
