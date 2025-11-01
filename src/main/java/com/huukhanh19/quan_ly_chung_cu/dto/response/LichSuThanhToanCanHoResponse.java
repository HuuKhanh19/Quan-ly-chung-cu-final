package com.huukhanh19.quan_ly_chung_cu.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LichSuThanhToanCanHoResponse {
    // Thông tin căn hộ
    Integer idCanHo;
    String soNha;
    String loaiCanHo;

    // Thống kê tổng quan
    Integer tongPhiBatBuocPhaiNop;
    Integer tongPhiBatBuocDaNop;
    Integer tongPhiBatBuocConThieu;
    Integer tongDongGopTuNguyen;
    Integer tongTatCa;

    // Số lượng kỳ
    Integer soKyChuaThanhToan;
    Integer soKyDaThanhToan;

    // Danh sách phí bắt buộc
    List<PhiBatBuocCanHoResponse> danhSachPhiBatBuoc;

    // Danh sách đóng góp tự nguyện
    List<DongGopCanHoResponse> danhSachDongGop;
}