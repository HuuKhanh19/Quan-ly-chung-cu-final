package com.huukhanh19.quan_ly_chung_cu.enums;

public enum LoaiBienDongThuPhi {
    THU_PHI_BAT_BUOC("Thu phí"),
    DONG_GOP_TU_NGUYEN("Đóng góp");

    private final String displayName;

    LoaiBienDongThuPhi(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
