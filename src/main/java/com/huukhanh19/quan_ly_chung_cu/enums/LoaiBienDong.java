package com.huukhanh19.quan_ly_chung_cu.enums;

public enum LoaiBienDong {
    TAM_TRU("Tạm trú"),
    TAM_VANG("Tạm vắng"),
    THEM_NHAN_KHAU("Thêm nhân khẩu"),
    SUA_NHAN_KHAU("Sửa nhân khẩu"),
    XOA_NHAN_KHAU("Xóa nhân khẩu");

    private final String displayName;

    LoaiBienDong(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
