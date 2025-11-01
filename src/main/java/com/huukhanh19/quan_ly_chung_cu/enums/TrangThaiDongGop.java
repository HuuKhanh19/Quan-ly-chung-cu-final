package com.huukhanh19.quan_ly_chung_cu.enums;

public enum TrangThaiDongGop {
    DANG_THU("Đang thu"),
    DA_KET_THUC("Đã kết thúc"),
    HUY("Hủy");

    private final String displayName;

    TrangThaiDongGop(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
