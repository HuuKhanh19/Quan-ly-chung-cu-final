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
        hoGiaDinh1.setGioiTinh(GioiTinh.Nam);
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

        NhanKhau chuHo1 = new NhanKhau("012345678999", hoGiaDinh1, "Nguyễn Văn A", GioiTinh.Nam,
                LocalDate.of(1980, 5, 20), "Kinh", "Không", "Việt Nam",
                "Tòa A, Chung cư XYZ, Hà Nội", "0901234567", "vana@example.com",
                "Chủ hộ", "Đang ở");

        NhanKhau vo1 = new NhanKhau("987654321111", hoGiaDinh1, "Trần Thị B", GioiTinh.Nữ,
                LocalDate.of(1985, 3, 15), "Kinh", "Không", "Việt Nam",
                "Tòa A, Chung cư XYZ, Hà Nội", "0911222333", "tranthib@example.com",
                "Vợ", "Đang ở");

        NhanKhau con1 = new NhanKhau("112233445566", hoGiaDinh1, "Nguyễn Văn C", GioiTinh.Khác,
                LocalDate.of(2010, 9, 10), "Kinh", "Không", "Việt Nam",
                "Tòa A, Chung cư XYZ, Hà Nội", "0988777666", "nguyenvanc@example.com",
                "Con", "Đang ở");

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
        hoGiaDinh2.setGioiTinh(GioiTinh.Nam);
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

        NhanKhau chuHo2 = new NhanKhau("123456789000", hoGiaDinh2, "Phạm Văn D", GioiTinh.Nam,
                LocalDate.of(1990, 7, 10), "Kinh", "Không", "Việt Nam",
                "Tòa A, Chung cư XYZ, Hà Nội", "0933222111", "phamvand@example.com",
                "Chủ hộ", "Đang ở");

        NhanKhau vo2 = new NhanKhau("223344556677", hoGiaDinh2, "Lê Thị E", GioiTinh.Nữ,
                LocalDate.of(1992, 4, 25), "Kinh", "Không", "Việt Nam",
                "Tòa A, Chung cư XYZ, Hà Nội", "0977555444", "lethie@example.com",
                "Vợ", "Đang ở");

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
