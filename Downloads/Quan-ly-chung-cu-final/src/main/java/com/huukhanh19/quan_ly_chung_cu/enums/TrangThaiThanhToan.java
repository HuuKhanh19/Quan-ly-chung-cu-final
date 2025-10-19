package com.huukhanh19.quan_ly_chung_cu.enums;

public enum TrangThaiThanhToan {
    DA_THANH_TOAN("Đã thanh toán"),
    CHUA_THANH_TOAN("Chưa thanh toán");

    private final String displayName;

    TrangThaiThanhToan(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
