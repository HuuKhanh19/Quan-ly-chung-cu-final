package com.huukhanh19.quan_ly_chung_cu.configuration;

import com.huukhanh19.quan_ly_chung_cu.entity.*;
import com.huukhanh19.quan_ly_chung_cu.enums.GioiTinh;
import com.huukhanh19.quan_ly_chung_cu.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    // Không cần UserRepository và PasswordEncoder ở đây nữa
    private final CanHoRepository canHoRepository;
    private final HoGiaDinhRepository hoGiaDinhRepository;
    private final NhanKhauRepository nhanKhauRepository;
    private final PhiChungCuRepository phiChungCuRepository;

    @Override
    public void run(String... args) throws Exception {
        if (canHoRepository.count() == 0) {
            log.info("Khởi tạo dữ liệu mẫu (Căn hộ, Hộ gia đình,...)...");

            // 1. Tạo Căn Hộ
            CanHo canHo101 = CanHo.builder()
                    .soNha("P.101 - S1")
                    .loaiCanHo("2PN, 1VS")
                    .dienTich(new BigDecimal("65.5"))
                    .diaChi("Tòa S1, Chung cư BlueMoon")
                    .hoGiaDinhs(new HashSet<>())
                    .build();

            CanHo canHo205 = CanHo.builder()
                    .soNha("P.205 - S2")
                    .loaiCanHo("3PN, 2VS")
                    .dienTich(new BigDecimal("92.0"))
                    .diaChi("Tòa S2, Chung cư BlueMoon")
                    .hoGiaDinhs(new HashSet<>())
                    .build();

            canHoRepository.saveAll(List.of(canHo101, canHo205));

// 2. Tạo Hộ Gia Đình A và Nhân khẩu cho chủ hộ A
            HoGiaDinh hoGiaDinhA = HoGiaDinh.builder()
                    .cccdChuHo("111111111111")
                    .canHo(canHo101)
                    .soThanhVien(1) // ban đầu chỉ có chủ hộ
                    .hoTenChuHo("Nguyễn Văn A")
                    .gioiTinh(GioiTinh.Nam)
                    .ngaySinh(LocalDate.of(1985, 5, 20))
                    .tang(1)
                    .danToc("Kinh")
                    .tonGiao("Không")
                    .quocTich("Việt Nam")
                    .diaChi("P.101 - S1, Tòa S1, Chung cư BlueMoon")
                    .sdt("0912345678")
                    .email("nguyenvana@email.com")
                    .soXeMay(1)
                    .soOto(0)
                    .trangThai("Đang ở")
                    .nhanKhaus(new HashSet<>())
                    .build();
            hoGiaDinhRepository.save(hoGiaDinhA);

// Nhân khẩu chủ hộ A
            NhanKhau chuHoA = NhanKhau.builder()
                    .cccd(hoGiaDinhA.getCccdChuHo())
                    .hoGiaDinh(hoGiaDinhA)
                    .hoVaTen(hoGiaDinhA.getHoTenChuHo())
                    .gioiTinh(hoGiaDinhA.getGioiTinh())
                    .ngaySinh(hoGiaDinhA.getNgaySinh())
                    .danToc("Kinh")
                    .tonGiao("Không")
                    .quocTich("Việt Nam")
                    .diaChi(hoGiaDinhA.getDiaChi())
                    .sdt(hoGiaDinhA.getSdt())
                    .email(hoGiaDinhA.getEmail())
                    .quanHe("Chủ hộ")
                    .trangThai("Đang ở")
                    .build();
            nhanKhauRepository.save(chuHoA);

// 3. Tạo Hộ Gia Đình B và Nhân khẩu cho chủ hộ B
            HoGiaDinh hoGiaDinhB = HoGiaDinh.builder()
                    .cccdChuHo("222222222222")
                    .canHo(canHo205)
                    .soThanhVien(1)
                    .hoTenChuHo("Trần Thị B")
                    .gioiTinh(GioiTinh.Nữ)
                    .ngaySinh(LocalDate.of(1992, 10, 15))
                    .tang(2)
                    .danToc("Kinh")
                    .tonGiao("Phật giáo")
                    .quocTich("Việt Nam")
                    .diaChi("P.205 - S2, Tòa S2, Chung cư BlueMoon")
                    .sdt("0987654321")
                    .email("tranthib@email.com")
                    .soXeMay(2)
                    .soOto(1)
                    .trangThai("Đang ở")
                    .nhanKhaus(new HashSet<>())
                    .build();
            hoGiaDinhRepository.save(hoGiaDinhB);

// Nhân khẩu chủ hộ B
            NhanKhau chuHoB = NhanKhau.builder()
                    .cccd(hoGiaDinhB.getCccdChuHo())
                    .hoGiaDinh(hoGiaDinhB)
                    .hoVaTen(hoGiaDinhB.getHoTenChuHo())
                    .gioiTinh(hoGiaDinhB.getGioiTinh())
                    .ngaySinh(hoGiaDinhB.getNgaySinh())
                    .danToc("Kinh")
                    .tonGiao("Phật giáo")
                    .quocTich("Việt Nam")
                    .diaChi(hoGiaDinhB.getDiaChi())
                    .sdt(hoGiaDinhB.getSdt())
                    .email(hoGiaDinhB.getEmail())
                    .quanHe("Chủ hộ")
                    .trangThai("Đang ở")
                    .build();
            nhanKhauRepository.save(chuHoB);

// 4. Thêm thành viên cho Hộ Gia Đình A
            NhanKhau nhanKhauVoA = NhanKhau.builder()
                    .cccd("333333333333")
                    .hoGiaDinh(hoGiaDinhA)
                    .hoVaTen("Lê Thị C")
                    .gioiTinh(GioiTinh.Nữ)
                    .ngaySinh(LocalDate.of(1990, 1, 1))
                    .danToc("Kinh")
                    .tonGiao("Không")
                    .quocTich("Việt Nam")
                    .diaChi(hoGiaDinhA.getDiaChi())
                    .sdt("0911222333")
                    .email("lethic@email.com")
                    .quanHe("Vợ")
                    .trangThai("Đang ở")
                    .build();
            nhanKhauRepository.save(nhanKhauVoA);

// Cập nhật lại số thành viên cho Hộ Gia Đình A
            hoGiaDinhA.setSoThanhVien(2);
            hoGiaDinhRepository.save(hoGiaDinhA);


            log.info("Đã khởi tạo dữ liệu mẫu thành công.");
        } else {
            log.info("Dữ liệu nghiệp vụ đã tồn tại. Bỏ qua khởi tạo.");
        }
    }
}