USE QuanLyChungCu;

-- ============================================
-- 1. BẢNG CAN_HO (30 căn hộ trong chung cư)
-- ============================================
INSERT INTO can_ho (id_can_ho, so_nha, loai_can_ho, dien_tich, dia_chi) VALUES
-- Tầng 1-5: Căn hộ 2 phòng ngủ
(101, 'A101', '2 Phòng Ngủ', 65.50, 'Tầng 1, Tòa A, Chung cư Green Park'),
(102, 'A102', '2 Phòng Ngủ', 68.00, 'Tầng 1, Tòa A, Chung cư Green Park'),
(201, 'A201', '2 Phòng Ngủ', 65.50, 'Tầng 2, Tòa A, Chung cư Green Park'),
(202, 'A202', '2 Phòng Ngủ', 68.00, 'Tầng 2, Tòa A, Chung cư Green Park'),
(301, 'A301', '3 Phòng Ngủ', 85.00, 'Tầng 3, Tòa A, Chung cư Green Park'),
(302, 'A302', '3 Phòng Ngủ', 88.50, 'Tầng 3, Tòa A, Chung cư Green Park'),
(401, 'A401', '3 Phòng Ngủ', 85.00, 'Tầng 4, Tòa A, Chung cư Green Park'),
(402, 'A402', '3 Phòng Ngủ', 88.50, 'Tầng 4, Tòa A, Chung cư Green Park'),
(501, 'A501', 'Penthouse', 120.00, 'Tầng 5, Tòa A, Chung cư Green Park'),
(502, 'A502', 'Penthouse', 125.00, 'Tầng 5, Tòa A, Chung cư Green Park'),

-- Tòa B
(103, 'B101', '2 Phòng Ngủ', 65.50, 'Tầng 1, Tòa B, Chung cư Green Park'),
(104, 'B102', '2 Phòng Ngủ', 68.00, 'Tầng 1, Tòa B, Chung cư Green Park'),
(203, 'B201', '2 Phòng Ngủ', 65.50, 'Tầng 2, Tòa B, Chung cư Green Park'),
(204, 'B202', '2 Phòng Ngủ', 68.00, 'Tầng 2, Tòa B, Chung cư Green Park'),
(303, 'B301', '3 Phòng Ngủ', 85.00, 'Tầng 3, Tòa B, Chung cư Green Park'),
(304, 'B302', '3 Phòng Ngủ', 88.50, 'Tầng 3, Tòa B, Chung cư Green Park'),
(403, 'B401', '3 Phòng Ngủ', 85.00, 'Tầng 4, Tòa B, Chung cư Green Park'),
(404, 'B402', '3 Phòng Ngủ', 88.50, 'Tầng 4, Tòa B, Chung cư Green Park'),
(503, 'B501', 'Penthouse', 120.00, 'Tầng 5, Tòa B, Chung cư Green Park'),
(504, 'B502', 'Penthouse', 125.00, 'Tầng 5, Tòa B, Chung cư Green Park'),

-- Tòa C
(105, 'C101', '2 Phòng Ngủ', 65.50, 'Tầng 1, Tòa C, Chung cư Green Park'),
(106, 'C102', '2 Phòng Ngủ', 68.00, 'Tầng 1, Tòa C, Chung cư Green Park'),
(205, 'C201', '2 Phòng Ngủ', 65.50, 'Tầng 2, Tòa C, Chung cư Green Park'),
(206, 'C202', '2 Phòng Ngủ', 68.00, 'Tầng 2, Tòa C, Chung cư Green Park'),
(305, 'C301', '3 Phòng Ngủ', 85.00, 'Tầng 3, Tòa C, Chung cư Green Park'),
(306, 'C302', '3 Phòng Ngủ', 88.50, 'Tầng 3, Tòa C, Chung cư Green Park'),
(405, 'C401', '3 Phòng Ngủ', 85.00, 'Tầng 4, Tòa C, Chung cư Green Park'),
(406, 'C402', '3 Phòng Ngủ', 88.50, 'Tầng 4, Tòa C, Chung cư Green Park'),
(505, 'C501', 'Penthouse', 120.00, 'Tầng 5, Tòa C, Chung cư Green Park'),
(506, 'C502', 'Penthouse', 125.00, 'Tầng 5, Tòa C, Chung cư Green Park');

-- ============================================
-- 2. BẢNG HO_GIA_DINH (25 hộ gia đình)
-- ============================================
INSERT INTO ho_gia_dinh (cccd_chu_ho, id_can_ho, so_thanh_vien, ho_ten_chu_ho, gioi_tinh, ngay_sinh, dan_toc, ton_giao, quoc_tich, dia_chi, sdt, email, so_xe_may, so_oto, trang_thai) VALUES
-- Hộ gia đình đã ở từ 2023
('001088001234', 101, 4, 'Nguyễn Văn An', 'NAM', '1985-03-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa A, Chung cư Green Park', '0901234567', 'nva@gmail.com', 1, 1, 'Đang cư trú'),
('002088002345', 102, 3, 'Trần Thị Bình', 'NU', '1987-06-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa A, Chung cư Green Park', '0902345678', 'ttb@gmail.com', 2, 0, 'Đang cư trú'),
('003088003456', 201, 5, 'Lê Văn Cường', 'NAM', '1982-09-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa A, Chung cư Green Park', '0903456789', 'lvc@gmail.com', 1, 1, 'Đang cư trú'),
('004088004567', 202, 2, 'Phạm Thị Dung', 'NU', '1990-12-05', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 2, Tòa A, Chung cư Green Park', '0904567890', 'ptd@gmail.com', 1, 0, 'Đang cư trú'),
('005088005678', 301, 4, 'Hoàng Văn Em', 'NAM', '1988-02-28', 'Kinh', 'Không', 'Việt Nam', 'Tầng 3, Tòa A, Chung cư Green Park', '0905678901', 'hve@gmail.com', 2, 1, 'Đang cư trú'),

-- Hộ gia đình chuyển đến trong 2024
('006088006789', 302, 3, 'Đỗ Thị Phương', 'NU', '1986-04-18', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa A, Chung cư Green Park', '0906789012', 'dtp@gmail.com', 1, 1, 'Đang cư trú'),
('007088007890', 401, 4, 'Vũ Văn Giang', 'NAM', '1984-07-22', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa A, Chung cư Green Park', '0907890123', 'vvg@gmail.com', 1, 1, 'Đang cư trú'),
('008088008901', 402, 2, 'Bùi Thị Hoa', 'NU', '1992-10-30', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa A, Chung cư Green Park', '0908901234', 'bth@gmail.com', 1, 0, 'Đang cư trú'),
('009088009012', 501, 3, 'Đinh Văn Hùng', 'NAM', '1980-05-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 5, Tòa A, Chung cư Green Park', '0909012345', 'dvh@gmail.com', 0, 2, 'Đang cư trú'),
('010088010123', 502, 5, 'Ngô Thị Lan', 'NU', '1983-08-25', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa A, Chung cư Green Park', '0910123456', 'ntl@gmail.com', 2, 1, 'Đang cư trú'),

-- Tòa B
('011088011234', 103, 4, 'Mai Văn Long', 'NAM', '1986-11-12', 'Kinh', 'Không', 'Việt Nam', 'Tầng 1, Tòa B, Chung cư Green Park', '0911234567', 'mvl@gmail.com', 1, 1, 'Đang cư trú'),
('012088012345', 104, 3, 'Cao Thị Mai', 'NU', '1989-01-20', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa B, Chung cư Green Park', '0912345678', 'ctm@gmail.com', 2, 0, 'Đang cư trú'),
('013088013456', 203, 5, 'Tô Văn Nam', 'NAM', '1981-03-08', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 2, Tòa B, Chung cư Green Park', '0913456789', 'tvn@gmail.com', 1, 1, 'Đang cư trú'),
('014088014567', 204, 2, 'Lý Thị Oanh', 'NU', '1991-06-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa B, Chung cư Green Park', '0914567890', 'lto@gmail.com', 1, 0, 'Đang cư trú'),
('015088015678', 303, 4, 'Hồ Văn Phúc', 'NAM', '1987-09-22', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 3, Tòa B, Chung cư Green Park', '0915678901', 'hvp@gmail.com', 2, 1, 'Đang cư trú'),

-- Hộ chuyển đến 2024 Q3-Q4
('016088016789', 304, 3, 'Phan Thị Quỳnh', 'NU', '1988-12-05', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa B, Chung cư Green Park', '0916789012', 'ptq@gmail.com', 1, 1, 'Đang cư trú'),
('017088017890', 403, 4, 'Dương Văn Sơn', 'NAM', '1985-02-28', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa B, Chung cư Green Park', '0917890123', 'dvs@gmail.com', 1, 1, 'Đang cư trú'),
('018088018901', 404, 2, 'Võ Thị Thảo', 'NU', '1993-05-10', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa B, Chung cư Green Park', '0918901234', 'vtt@gmail.com', 1, 0, 'Đang cư trú'),
('019088019012', 503, 3, 'Trịnh Văn Tùng', 'NAM', '1982-08-18', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa B, Chung cư Green Park', '0919012345', 'tvt@gmail.com', 0, 2, 'Đang cư trú'),
('020088020123', 504, 5, 'Đặng Thị Uyên', 'NU', '1984-11-25', 'Kinh', 'Không', 'Việt Nam', 'Tầng 5, Tòa B, Chung cư Green Park', '0920123456', 'dtu@gmail.com', 2, 1, 'Đang cư trú'),

-- Tòa C (Hộ chuyển đến cuối 2024 và đầu 2025)
('021088021234', 105, 4, 'Lưu Văn Vinh', 'NAM', '1986-01-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa C, Chung cư Green Park', '0921234567', 'lvv@gmail.com', 1, 1, 'Đang cư trú'),
('022088022345', 106, 3, 'Hà Thị Xuân', 'NU', '1990-04-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa C, Chung cư Green Park', '0922345678', 'htx@gmail.com', 2, 0, 'Đang cư trú'),
('023088023456', 205, 5, 'Từ Văn Yên', 'NAM', '1983-07-08', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa C, Chung cư Green Park', '0923456789', 'tvy@gmail.com', 1, 1, 'Đang cư trú'),
('024088024567', 206, 2, 'Chu Thị Ánh', 'NU', '1994-10-12', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 2, Tòa C, Chung cư Green Park', '0924567890', 'cta@gmail.com', 1, 0, 'Đang cư trú'),
('025088025678', 305, 4, 'Tạ Văn Bình', 'NAM', '1987-12-30', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa C, Chung cư Green Park', '0925678901', 'tvb@gmail.com', 2, 1, 'Đang cư trú');

-- ============================================
-- 3. BẢNG NHAN_KHAU (Thành viên trong hộ gia đình)
-- Phân bổ theo thời gian để test API thống kê
-- ============================================

-- Nhân khẩu từ 2023 (30 người)
INSERT INTO nhan_khau (cccd, cccd_chu_ho, ho_va_ten, gioi_tinh, ngay_sinh, dan_toc, ton_giao, quoc_tich, dia_chi, sdt, email, quan_he, trang_thai, ngay_tao) VALUES
-- Hộ 001 (4 người)
('001088001234', '001088001234', 'Nguyễn Văn An', 'NAM', '1985-03-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa A', '0901234567', 'nva@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-01-15'),
('001088001235', '001088001234', 'Trần Thị Hương', 'NU', '1987-05-20', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa A', '0901234568', NULL, 'Vợ', 'Đang cư trú', '2023-01-15'),
('001088001236', '001088001234', 'Nguyễn Văn Minh', 'NAM', '2010-08-10', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-01-15'),
('001088001237', '001088001234', 'Nguyễn Thị Mai', 'NU', '2015-11-25', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-01-15'),

-- Hộ 002 (3 người)
('002088002345', '002088002345', 'Trần Thị Bình', 'NU', '1987-06-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa A', '0902345678', 'ttb@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-02-10'),
('002088002346', '002088002345', 'Lê Văn Đức', 'NAM', '1985-04-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa A', '0902345679', NULL, 'Chồng', 'Đang cư trú', '2023-02-10'),
('002088002347', '002088002345', 'Trần Văn Nam', 'NAM', '2012-09-05', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-02-10'),

-- Hộ 003 (5 người)
('003088003456', '003088003456', 'Lê Văn Cường', 'NAM', '1982-09-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa A', '0903456789', 'lvc@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-03-20'),
('003088003457', '003088003456', 'Nguyễn Thị Lan', 'NU', '1984-12-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa A', '0903456790', NULL, 'Vợ', 'Đang cư trú', '2023-03-20'),
('003088003458', '003088003456', 'Lê Văn Tuấn', 'NAM', '2008-05-20', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-03-20'),
('003088003459', '003088003456', 'Lê Thị Hà', 'NU', '2013-08-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-03-20'),
('003088003460', '003088003456', 'Lê Văn Khang', 'NAM', '2018-11-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-03-20'),

-- Hộ 004 (2 người)
('004088004567', '004088004567', 'Phạm Thị Dung', 'NU', '1990-12-05', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 2, Tòa A', '0904567890', 'ptd@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-04-15'),
('004088004568', '004088004567', 'Hoàng Văn Toàn', 'NAM', '1988-10-20', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 2, Tòa A', '0904567891', NULL, 'Chồng', 'Đang cư trú', '2023-04-15'),

-- Hộ 005 (4 người)
('005088005678', '005088005678', 'Hoàng Văn Em', 'NAM', '1988-02-28', 'Kinh', 'Không', 'Việt Nam', 'Tầng 3, Tòa A', '0905678901', 'hve@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-05-10'),
('005088005679', '005088005678', 'Vũ Thị Phương', 'NU', '1990-06-12', 'Kinh', 'Không', 'Việt Nam', 'Tầng 3, Tòa A', '0905678902', NULL, 'Vợ', 'Đang cư trú', '2023-05-10'),
('005088005680', '005088005678', 'Hoàng Văn Bảo', 'NAM', '2014-03-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 3, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-05-10'),
('005088005681', '005088005678', 'Hoàng Thị Nhung', 'NU', '2017-07-20', 'Kinh', 'Không', 'Việt Nam', 'Tầng 3, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2023-05-10'),

-- Hộ 011 (4 người)
('011088011234', '011088011234', 'Mai Văn Long', 'NAM', '1986-11-12', 'Kinh', 'Không', 'Việt Nam', 'Tầng 1, Tòa B', '0911234567', 'mvl@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-06-15'),
('011088011235', '011088011234', 'Đỗ Thị Hương', 'NU', '1988-09-08', 'Kinh', 'Không', 'Việt Nam', 'Tầng 1, Tòa B', '0911234568', NULL, 'Vợ', 'Đang cư trú', '2023-06-15'),
('011088011236', '011088011234', 'Mai Văn Đức', 'NAM', '2011-12-20', 'Kinh', 'Không', 'Việt Nam', 'Tầng 1, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2023-06-15'),
('011088011237', '011088011234', 'Mai Thị Linh', 'NU', '2016-05-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 1, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2023-06-15'),

-- Hộ 012 (3 người)
('012088012345', '012088012345', 'Cao Thị Mai', 'NU', '1989-01-20', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa B', '0912345678', 'ctm@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-07-20'),
('012088012346', '012088012345', 'Trần Văn Hải', 'NAM', '1987-11-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa B', '0912345679', NULL, 'Chồng', 'Đang cư trú', '2023-07-20'),
('012088012347', '012088012345', 'Cao Văn Minh', 'NAM', '2013-04-10', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2023-07-20'),

-- Hộ 013 (5 người)
('013088013456', '013088013456', 'Tô Văn Nam', 'NAM', '1981-03-08', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 2, Tòa B', '0913456789', 'tvn@gmail.com', 'Chủ hộ', 'Đang cư trú', '2023-08-10'),
('013088013457', '013088013456', 'Lý Thị Nga', 'NU', '1983-07-22', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 2, Tòa B', '0913456790', NULL, 'Vợ', 'Đang cư trú', '2023-08-10'),
('013088013458', '013088013456', 'Tô Văn Khoa', 'NAM', '2007-10-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 2, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2023-08-10'),
('013088013459', '013088013456', 'Tô Thị Hằng', 'NU', '2012-02-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 2, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2023-08-10'),
('013088013460', '013088013456', 'Tô Văn Phúc', 'NAM', '2017-05-12', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 2, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2023-08-10');

-- Nhân khẩu từ Q1-Q2/2024 (20 người)
INSERT INTO nhan_khau (cccd, cccd_chu_ho, ho_va_ten, gioi_tinh, ngay_sinh, dan_toc, ton_giao, quoc_tich, dia_chi, sdt, email, quan_he, trang_thai, ngay_tao) VALUES
-- Hộ 006 (3 người) - chuyển đến tháng 1/2024
('006088006789', '006088006789', 'Đỗ Thị Phương', 'NU', '1986-04-18', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa A', '0906789012', 'dtp@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-01-15'),
('006088006790', '006088006789', 'Nguyễn Văn Quang', 'NAM', '1984-08-25', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa A', '0906789013', NULL, 'Chồng', 'Đang cư trú', '2024-01-15'),
('006088006791', '006088006789', 'Đỗ Văn Hùng', 'NAM', '2015-11-10', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-01-15'),

-- Hộ 007 (4 người) - chuyển đến tháng 2/2024
('007088007890', '007088007890', 'Vũ Văn Giang', 'NAM', '1984-07-22', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa A', '0907890123', 'vvg@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-02-20'),
('007088007891', '007088007890', 'Phan Thị Linh', 'NU', '1986-10-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa A', '0907890124', NULL, 'Vợ', 'Đang cư trú', '2024-02-20'),
('007088007892', '007088007890', 'Vũ Văn Khải', 'NAM', '2010-03-12', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-02-20'),
('007088007893', '007088007890', 'Vũ Thị Ngọc', 'NU', '2014-06-08', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-02-20'),

-- Hộ 008 (2 người) - chuyển đến tháng 3/2024
('008088008901', '008088008901', 'Bùi Thị Hoa', 'NU', '1992-10-30', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa A', '0908901234', 'bth@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-03-25'),
('008088008902', '008088008901', 'Trần Văn Thắng', 'NAM', '1990-08-20', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa A', '0908901235', NULL, 'Chồng', 'Đang cư trú', '2024-03-25'),

-- Hộ 009 (3 người) - chuyển đến tháng 4/2024
('009088009012', '009088009012', 'Đinh Văn Hùng', 'NAM', '1980-05-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 5, Tòa A', '0909012345', 'dvh@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-04-10'),
('009088009013', '009088009012', 'Lê Thị Hồng', 'NU', '1982-09-22', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 5, Tòa A', '0909012346', NULL, 'Vợ', 'Đang cư trú', '2024-04-10'),
('009088009014', '009088009012', 'Đinh Văn Nam', 'NAM', '2008-12-05', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 5, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-04-10'),

-- Hộ 010 (5 người) - chuyển đến tháng 5/2024
('010088010123', '010088010123', 'Ngô Thị Lan', 'NU', '1983-08-25', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa A', '0910123456', 'ntl@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-05-15'),
('010088010124', '010088010123', 'Phạm Văn Tú', 'NAM', '1981-06-18', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa A', '0910123457', NULL, 'Chồng', 'Đang cư trú', '2024-05-15'),
('010088010125', '010088010123', 'Ngô Văn An', 'NAM', '2007-11-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-05-15'),
('010088010126', '010088010123', 'Ngô Thị Bích', 'NU', '2011-04-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-05-15'),
('010088010127', '010088010123', 'Ngô Văn Cường', 'NAM', '2016-07-10', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa A', NULL, NULL, 'Con', 'Đang cư trú', '2024-05-15'),

-- Hộ 014 (2 người) - chuyển đến tháng 6/2024
('014088014567', '014088014567', 'Lý Thị Oanh', 'NU', '1991-06-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa B', '0914567890', 'lto@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-06-20'),
('014088014568', '014088014567', 'Hoàng Văn Sơn', 'NAM', '1989-04-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa B', '0914567891', NULL, 'Chồng', 'Đang cư trú', '2024-06-20'),

-- Hộ 015 (4 người) - chuyển đến tháng 6/2024
('015088015678', '015088015678', 'Hồ Văn Phúc', 'NAM', '1987-09-22', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 3, Tòa B', '0915678901', 'hvp@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-06-25'),
('015088015679', '015088015678', 'Đặng Thị Thúy', 'NU', '1989-12-08', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 3, Tòa B', '0915678902', NULL, 'Vợ', 'Đang cư trú', '2024-06-25'),
('015088015680', '015088015678', 'Hồ Văn Dũng', 'NAM', '2012-05-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 3, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-06-25'),
('015088015681', '015088015678', 'Hồ Thị Mai', 'NU', '2016-08-20', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 3, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-06-25');

-- Nhân khẩu từ Q3/2024 (15 người)
INSERT INTO nhan_khau (cccd, cccd_chu_ho, ho_va_ten, gioi_tinh, ngay_sinh, dan_toc, ton_giao, quoc_tich, dia_chi, sdt, email, quan_he, trang_thai, ngay_tao) VALUES
-- Hộ 016 (3 người) - chuyển đến tháng 7/2024
('016088016789', '016088016789', 'Phan Thị Quỳnh', 'NU', '1988-12-05', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa B', '0916789012', 'ptq@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-07-15'),
('016088016790', '016088016789', 'Võ Văn Tài', 'NAM', '1986-10-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa B', '0916789013', NULL, 'Chồng', 'Đang cư trú', '2024-07-15'),
('016088016791', '016088016789', 'Phan Văn Khang', 'NAM', '2014-03-12', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-07-15'),

-- Hộ 017 (4 người) - chuyển đến tháng 8/2024
('017088017890', '017088017890', 'Dương Văn Sơn', 'NAM', '1985-02-28', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa B', '0917890123', 'dvs@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-08-20'),
('017088017891', '017088017890', 'Trương Thị Lan', 'NU', '1987-06-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa B', '0917890124', NULL, 'Vợ', 'Đang cư trú', '2024-08-20'),
('017088017892', '017088017890', 'Dương Văn Minh', 'NAM', '2011-09-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-08-20'),
('017088017893', '017088017890', 'Dương Thị Hương', 'NU', '2015-12-05', 'Kinh', 'Không', 'Việt Nam', 'Tầng 4, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-08-20'),

-- Hộ 018 (2 người) - chuyển đến tháng 9/2024
('018088018901', '018088018901', 'Võ Thị Thảo', 'NU', '1993-05-10', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa B', '0918901234', 'vtt@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-09-15'),
('018088018902', '018088018901', 'Lê Văn Tuấn', 'NAM', '1991-03-25', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 4, Tòa B', '0918901235', NULL, 'Chồng', 'Đang cư trú', '2024-09-15'),

-- Hộ 019 (3 người) - chuyển đến tháng 9/2024
('019088019012', '019088019012', 'Trịnh Văn Tùng', 'NAM', '1982-08-18', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa B', '0919012345', 'tvt@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-09-20'),
('019088019013', '019088019012', 'Nguyễn Thị Hà', 'NU', '1984-11-22', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa B', '0919012346', NULL, 'Vợ', 'Đang cư trú', '2024-09-20'),
('019088019014', '019088019012', 'Trịnh Văn Đức', 'NAM', '2009-02-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 5, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-09-20'),

-- Hộ 020 (5 người) - chuyển đến tháng 9/2024
('020088020123', '020088020123', 'Đặng Thị Uyên', 'NU', '1984-11-25', 'Kinh', 'Không', 'Việt Nam', 'Tầng 5, Tòa B', '0920123456', 'dtu@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-09-25'),
('020088020124', '020088020123', 'Lý Văn Hùng', 'NAM', '1982-09-18', 'Kinh', 'Không', 'Việt Nam', 'Tầng 5, Tòa B', '0920123457', NULL, 'Chồng', 'Đang cư trú', '2024-09-25'),
('020088020125', '020088020123', 'Đặng Văn Quân', 'NAM', '2008-12-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 5, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-09-25'),
('020088020126', '020088020123', 'Đặng Thị Phương', 'NU', '2013-05-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 5, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-09-25'),
('020088020127', '020088020123', 'Đặng Văn Thắng', 'NAM', '2018-08-20', 'Kinh', 'Không', 'Việt Nam', 'Tầng 5, Tòa B', NULL, NULL, 'Con', 'Đang cư trú', '2024-09-25');

-- Nhân khẩu từ Q4/2024 và 2025 (20 người)
INSERT INTO nhan_khau (cccd, cccd_chu_ho, ho_va_ten, gioi_tinh, ngay_sinh, dan_toc, ton_giao, quoc_tich, dia_chi, sdt, email, quan_he, trang_thai, ngay_tao) VALUES
-- Hộ 021 (4 người) - chuyển đến tháng 10/2024
('021088021234', '021088021234', 'Lưu Văn Vinh', 'NAM', '1986-01-15', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa C', '0921234567', 'lvv@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-10-15'),
('021088021235', '021088021234', 'Hồ Thị Yến', 'NU', '1988-04-20', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa C', '0921234568', NULL, 'Vợ', 'Đang cư trú', '2024-10-15'),
('021088021236', '021088021234', 'Lưu Văn Khoa', 'NAM', '2012-07-10', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2024-10-15'),
('021088021237', '021088021234', 'Lưu Thị Ngọc', 'NU', '2016-10-05', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 1, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2024-10-15'),

-- Hộ 022 (3 người) - chuyển đến tháng 11/2024
('022088022345', '022088022345', 'Hà Thị Xuân', 'NU', '1990-04-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa C', '0922345678', 'htx@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-11-20'),
('022088022346', '022088022345', 'Phan Văn Long', 'NAM', '1988-02-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa C', '0922345679', NULL, 'Chồng', 'Đang cư trú', '2024-11-20'),
('022088022347', '022088022345', 'Hà Văn Nam', 'NAM', '2015-05-10', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 1, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2024-11-20'),

-- Hộ 023 (5 người) - chuyển đến tháng 12/2024
('023088023456', '023088023456', 'Từ Văn Yên', 'NAM', '1983-07-08', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa C', '0923456789', 'tvy@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-12-15'),
('023088023457', '023088023456', 'Mai Thị Hằng', 'NU', '1985-10-22', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa C', '0923456790', NULL, 'Vợ', 'Đang cư trú', '2024-12-15'),
('023088023458', '023088023456', 'Từ Văn Bảo', 'NAM', '2009-03-15', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2024-12-15'),
('023088023459', '023088023456', 'Từ Thị Linh', 'NU', '2014-06-20', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2024-12-15'),
('023088023460', '023088023456', 'Từ Văn Minh', 'NAM', '2019-09-10', 'Kinh', 'Không', 'Việt Nam', 'Tầng 2, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2024-12-15'),

-- Hộ 024 (2 người) - chuyển đến tháng 12/2024
('024088024567', '024088024567', 'Chu Thị Ánh', 'NU', '1994-10-12', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 2, Tòa C', '0924567890', 'cta@gmail.com', 'Chủ hộ', 'Đang cư trú', '2024-12-20'),
('024088024568', '024088024567', 'Vũ Văn Đức', 'NAM', '1992-08-05', 'Kinh', 'Phật giáo', 'Việt Nam', 'Tầng 2, Tòa C', '0924567891', NULL, 'Chồng', 'Đang cư trú', '2024-12-20'),

-- Hộ 025 (4 người) - chuyển đến tháng 1/2025
('025088025678', '025088025678', 'Tạ Văn Bình', 'NAM', '1987-12-30', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa C', '0925678901', 'tvb@gmail.com', 'Chủ hộ', 'Đang cư trú', '2025-01-10'),
('025088025679', '025088025678', 'Lâm Thị Thảo', 'NU', '1989-11-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa C', '0925678902', NULL, 'Vợ', 'Đang cư trú', '2025-01-10'),
('025088025680', '025088025678', 'Tạ Văn Kiên', 'NAM', '2013-04-20', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2025-01-10'),
('025088025681', '025088025678', 'Tạ Thị Hoa', 'NU', '2017-07-15', 'Kinh', 'Công giáo', 'Việt Nam', 'Tầng 3, Tòa C', NULL, NULL, 'Con', 'Đang cư trú', '2025-01-10');

-- ============================================
-- 4. BẢNG BANG_TAM_VANG (Tạm trú và Tạm vắng)
-- ============================================
INSERT INTO bang_tam_vang (cccd, ho_va_ten, id_can_ho, ngay_bat_dau, ngay_ket_thuc, ly_do, trang_thai, loai_dang_ky) VALUES
-- Tạm trú (người ngoài đến ở tạm)
('099099001111', 'Nguyễn Văn Khách 1', 101, '2024-03-15', '2024-06-15', 'Công tác tại Hà Nội', 'Thành công', 'TAM_TRU'),
('099099002222', 'Trần Thị Khách 2', 202, '2024-06-20', '2024-09-20', 'Học tập tại Hà Nội', 'Thành công', 'TAM_TRU'),
('099099003333', 'Lê Văn Khách 3', 303, '2024-08-10', '2024-11-10', 'Điều trị y tế', 'Thành công', 'TAM_TRU'),
('099099004444', 'Phạm Thị Khách 4', 404, '2024-11-01', '2025-02-01', 'Thăm thân nhân', 'Thành công', 'TAM_TRU'),

-- Tạm vắng (cư dân đi vắng)
('001088001234', 'Nguyễn Văn An', 101, '2024-07-01', '2024-08-31', 'Công tác nước ngoài', 'Thành công', 'TAM_VANG'),
('003088003456', 'Lê Văn Cường', 201, '2024-09-15', '2024-11-15', 'Đi học tập', 'Thành công', 'TAM_VANG'),
('011088011234', 'Mai Văn Long', 103, '2024-12-20', '2025-01-20', 'Về quê ăn Tết', 'Thành công', 'TAM_VANG');

-- ============================================
-- 5. BẢNG KHOAN_DONG_GOP (Các khoản đóng góp)
-- ============================================
INSERT INTO khoan_dong_gop (id_khoan_dong_gop, ten_khoan_dong_gop, ngay_tao, ngay_bat_dau_thu, han_dong_gop, trang_thai, tong_tien_thu_duoc, so_can_ho_dong_gop) VALUES
(1, 'Quỹ Tết Trung Thu 2024', '2024-08-01', '2024-08-05', '2024-08-25', 'DA_KET_THUC', 15000000, 20),
(2, 'Quỹ Tu Sửa Thang Máy', '2024-10-01', '2024-10-05', '2024-10-31', 'DA_KET_THUC', 25000000, 22),
(3, 'Quỹ Tết Nguyên Đán 2025', '2024-12-01', '2024-12-05', '2024-12-31', 'DANG_THU', 18000000, 18),
(4, 'Quỹ Mua Máy Lọc Nước Chung', '2025-01-10', '2025-01-15', '2025-02-15', 'DANG_THU', 5000000, 10);

-- ============================================
-- 6. BẢNG CHI_TIET_DONG_GOP (Chi tiết đóng góp)
-- ============================================
INSERT INTO chi_tiet_dong_gop (id_khoan_dong_gop, id_can_ho, so_tien_dong_gop, ngay_dong_gop, hinh_thuc_thanh_toan, nguoi_nhan, ghi_chu) VALUES
-- Khoản 1: Quỹ Tết Trung Thu
(1, 101, 1000000, '2024-08-06', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 102, 800000, '2024-08-07', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 201, 1000000, '2024-08-08', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 202, 500000, '2024-08-10', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 301, 1200000, '2024-08-12', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 302, 700000, '2024-08-13', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 401, 1000000, '2024-08-15', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 402, 600000, '2024-08-16', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 501, 1500000, '2024-08-17', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 502, 1800000, '2024-08-18', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 103, 800000, '2024-08-19', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 104, 700000, '2024-08-20', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 203, 1000000, '2024-08-21', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 204, 500000, '2024-08-22', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 303, 1200000, '2024-08-23', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 304, 700000, '2024-08-24', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 403, 900000, '2024-08-24', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 404, 600000, '2024-08-25', 'Tiền mặt', 'Ban quản lý', NULL),
(1, 503, 1500000, '2024-08-25', 'Chuyển khoản', 'Ban quản lý', NULL),
(1, 504, 1500000, '2024-08-25', 'Chuyển khoản', 'Ban quản lý', NULL),

-- Khoản 2: Quỹ Tu Sửa Thang Máy
(2, 101, 1500000, '2024-10-06', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 102, 1000000, '2024-10-07', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 201, 1500000, '2024-10-08', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 202, 800000, '2024-10-10', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 301, 2000000, '2024-10-12', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 302, 1000000, '2024-10-13', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 401, 1500000, '2024-10-15', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 402, 800000, '2024-10-16', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 501, 2500000, '2024-10-17', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 502, 3000000, '2024-10-18', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 103, 1000000, '2024-10-19', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 104, 1000000, '2024-10-20', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 203, 1500000, '2024-10-21', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 204, 800000, '2024-10-22', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 303, 2000000, '2024-10-23', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 304, 1000000, '2024-10-24', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 403, 1200000, '2024-10-26', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 404, 800000, '2024-10-27', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 503, 2500000, '2024-10-28', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 504, 3000000, '2024-10-29', 'Chuyển khoản', 'Ban quản lý', NULL),
(2, 105, 1200000, '2024-10-30', 'Tiền mặt', 'Ban quản lý', NULL),
(2, 106, 1000000, '2024-10-31', 'Tiền mặt', 'Ban quản lý', NULL),

-- Khoản 3: Quỹ Tết Nguyên Đán 2025
(3, 101, 1200000, '2024-12-06', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 102, 800000, '2024-12-07', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 201, 1200000, '2024-12-08', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 202, 600000, '2024-12-10', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 301, 1500000, '2024-12-12', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 302, 800000, '2024-12-13', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 401, 1200000, '2024-12-15', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 402, 700000, '2024-12-16', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 501, 2000000, '2024-12-17', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 502, 2500000, '2024-12-18', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 103, 1000000, '2024-12-19', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 104, 800000, '2024-12-20', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 203, 1200000, '2024-12-21', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 204, 600000, '2024-12-22', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 303, 1500000, '2024-12-23', 'Chuyển khoản', 'Ban quản lý', NULL),
(3, 304, 800000, '2024-12-24', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 403, 1000000, '2024-12-26', 'Tiền mặt', 'Ban quản lý', NULL),
(3, 404, 700000, '2024-12-27', 'Tiền mặt', 'Ban quản lý', NULL),

-- Khoản 4: Quỹ Máy Lọc Nước (đang thu)
(4, 101, 500000, '2025-01-16', 'Chuyển khoản', 'Ban quản lý', NULL),
(4, 102, 500000, '2025-01-17', 'Tiền mặt', 'Ban quản lý', NULL),
(4, 201, 500000, '2025-01-18', 'Chuyển khoản', 'Ban quản lý', NULL),
(4, 202, 500000, '2025-01-19', 'Tiền mặt', 'Ban quản lý', NULL),
(4, 301, 500000, '2025-01-20', 'Chuyển khoản', 'Ban quản lý', NULL),
(4, 302, 500000, '2025-01-21', 'Tiền mặt', 'Ban quản lý', NULL),
(4, 401, 500000, '2025-01-22', 'Chuyển khoản', 'Ban quản lý', NULL),
(4, 402, 500000, '2025-01-23', 'Tiền mặt', 'Ban quản lý', NULL),
(4, 501, 500000, '2025-01-24', 'Chuyển khoản', 'Ban quản lý', NULL),
(4, 502, 500000, '2025-01-25', 'Chuyển khoản', 'Ban quản lý', NULL);

-- ============================================
-- 7. THỜI GIAN THU PHÍ đã có sẵn
-- ============================================
INSERT INTO thoi_gian_thu_phi (id, ngay_thu, han_thu) VALUES
( 12025, '2025-01-26', '2025-01-31'), ( 22025, '2025-02-23', '2025-02-28'),
( 32025, '2025-03-26', '2025-03-31'), ( 42025, '2025-04-25', '2025-04-30'),
( 52025, '2025-05-26', '2025-05-31'), ( 62025, '2025-06-25', '2025-06-30'),
( 72025, '2025-07-26', '2025-07-31'), ( 82025, '2025-08-26', '2025-08-31'),
( 92025, '2025-09-25', '2025-09-30'), (102025, '2025-10-26', '2025-10-31'),
(112025, '2025-11-25', '2025-11-30'), (122025, '2025-12-26', '2025-12-31'),
( 12026, '2026-01-26', '2026-01-31'), ( 22026, '2026-02-23', '2026-02-28'),
( 32026, '2026-03-26', '2026-03-31'), ( 42026, '2026-04-25', '2026-04-30'),
( 52026, '2026-05-26', '2026-05-31'), ( 62026, '2026-06-25', '2026-06-30'),
( 72026, '2026-07-26', '2026-07-31'), ( 82026, '2026-08-26', '2026-08-31'),
( 92026, '2026-09-25', '2026-09-30'), (102026, '2026-10-26', '2026-10-31'),
(112026, '2026-11-25', '2026-11-30'), (122026, '2026-12-26', '2026-12-31');

-- ============================================
-- 8. VÍ DỤ DỮ LIỆU CHO CÁC BẢNG PHÍ
-- Chỉ tạo cho một số tháng để test, không tạo hết
-- ============================================

-- PHI_CHUNG_CU cho tháng 1/2025 (25 căn hộ có người ở)
INSERT INTO phi_chung_cu (id_can_ho, id_thoi_gian_thu, phi_dich_vu, phi_quan_ly, tong_phi_chung_cu) VALUES
(101, 12025, 458500, 458500, 917000),
(102, 12025, 476000, 476000, 952000),
(201, 12025, 458500, 458500, 917000),
(202, 12025, 476000, 476000, 952000),
(301, 12025, 595000, 595000, 1190000),
(302, 12025, 619500, 619500, 1239000),
(401, 12025, 595000, 595000, 1190000),
(402, 12025, 619500, 619500, 1239000),
(501, 12025, 840000, 840000, 1680000),
(502, 12025, 875000, 875000, 1750000),
(103, 12025, 458500, 458500, 917000),
(104, 12025, 476000, 476000, 952000),
(203, 12025, 458500, 458500, 917000),
(204, 12025, 476000, 476000, 952000),
(303, 12025, 595000, 595000, 1190000),
(304, 12025, 619500, 619500, 1239000),
(403, 12025, 595000, 595000, 1190000),
(404, 12025, 619500, 619500, 1239000),
(503, 12025, 840000, 840000, 1680000),
(504, 12025, 875000, 875000, 1750000),
(105, 12025, 458500, 458500, 917000),
(106, 12025, 476000, 476000, 952000),
(205, 12025, 458500, 458500, 917000),
(206, 12025, 476000, 476000, 952000),
(305, 12025, 595000, 595000, 1190000);

-- TIEN_ICH cho tháng 1/2025 (ví dụ một số căn hộ)
INSERT INTO tien_ich (id_can_ho, id_thoi_gian_thu, tien_dien, tien_nuoc, tien_internet, tong_tien_ich) VALUES
(101, 12025, 850000, 120000, 200000, 1170000),
(102, 12025, 720000, 100000, 200000, 1020000),
(201, 12025, 980000, 150000, 200000, 1330000),
(202, 12025, 650000, 90000, 200000, 940000),
(301, 12025, 1200000, 180000, 200000, 1580000),
(302, 12025, 950000, 140000, 200000, 1290000),
(401, 12025, 1100000, 170000, 200000, 1470000),
(402, 12025, 750000, 110000, 200000, 1060000),
(501, 12025, 1500000, 220000, 200000, 1920000),
(502, 12025, 1800000, 250000, 200000, 2250000),
(103, 12025, 900000, 130000, 200000, 1230000),
(104, 12025, 780000, 110000, 200000, 1090000),
(203, 12025, 1050000, 160000, 200000, 1410000),
(204, 12025, 690000, 95000, 200000, 985000),
(303, 12025, 1150000, 175000, 200000, 1525000),
(304, 12025, 980000, 145000, 200000, 1325000),
(403, 12025, 1080000, 165000, 200000, 1445000),
(404, 12025, 770000, 115000, 200000, 1085000),
(503, 12025, 1650000, 230000, 200000, 2080000),
(504, 12025, 1900000, 260000, 200000, 2360000),
(105, 12025, 820000, 125000, 200000, 1145000),
(106, 12025, 740000, 105000, 200000, 1045000),
(205, 12025, 920000, 155000, 200000, 1275000),
(206, 12025, 680000, 92000, 200000, 972000),
(305, 12025, 1180000, 178000, 200000, 1558000);

-- PHI_GUI_XE cho tháng 1/2025
INSERT INTO phi_gui_xe (id_can_ho, id_thoi_gian_thu, tien_xe_may, tien_xe_oto, tong_gui_xe) VALUES
(101, 12025, 70000, 1200000, 1270000),
(102, 12025, 140000, 0, 140000),
(201, 12025, 70000, 1200000, 1270000),
(202, 12025, 70000, 0, 70000),
(301, 12025, 140000, 1200000, 1340000),
(302, 12025, 70000, 1200000, 1270000),
(401, 12025, 70000, 1200000, 1270000),
(402, 12025, 70000, 0, 70000),
(501, 12025, 0, 2400000, 2400000),
(502, 12025, 140000, 1200000, 1340000),
(103, 12025, 70000, 1200000, 1270000),
(104, 12025, 140000, 0, 140000),
(203, 12025, 70000, 1200000, 1270000),
(204, 12025, 70000, 0, 70000),
(303, 12025, 140000, 1200000, 1340000),
(304, 12025, 70000, 1200000, 1270000),
(403, 12025, 70000, 1200000, 1270000),
(404, 12025, 70000, 0, 70000),
(503, 12025, 0, 2400000, 2400000),
(504, 12025, 140000, 1200000, 1340000),
(105, 12025, 70000, 1200000, 1270000),
(106, 12025, 140000, 0, 140000),
(205, 12025, 70000, 1200000, 1270000),
(206, 12025, 70000, 0, 70000),
(305, 12025, 140000, 1200000, 1340000);

-- TONG_THANH_TOAN cho tháng 1/2025
INSERT INTO tong_thanh_toan (id_can_ho, id_thoi_gian_thu, tong_phi_chung_cu, tong_tien_ich, tong_gui_xe, tong_phi, so_tien_da_nop, so_du, trang_thai) VALUES
(101, 12025, 917000, 1170000, 1270000, 3357000, 3357000, 0, 'DA_THANH_TOAN'),
(102, 12025, 952000, 1020000, 140000, 2112000, 2112000, 0, 'DA_THANH_TOAN'),
(201, 12025, 917000, 1330000, 1270000, 3517000, 3517000, 0, 'DA_THANH_TOAN'),
(202, 12025, 952000, 940000, 70000, 1962000, 0, -1962000, 'CHUA_THANH_TOAN'),
(301, 12025, 1190000, 1580000, 1340000, 4110000, 4110000, 0, 'DA_THANH_TOAN'),
(302, 12025, 1239000, 1290000, 1270000, 3799000, 3799000, 0, 'DA_THANH_TOAN'),
(401, 12025, 1190000, 1470000, 1270000, 3930000, 3930000, 0, 'DA_THANH_TOAN'),
(402, 12025, 1239000, 1060000, 70000, 2369000, 0, -2369000, 'CHUA_THANH_TOAN'),
(501, 12025, 1680000, 1920000, 2400000, 6000000, 6000000, 0, 'DA_THANH_TOAN'),
(502, 12025, 1750000, 2250000, 1340000, 5340000, 5340000, 0, 'DA_THANH_TOAN'),
(103, 12025, 917000, 1230000, 1270000, 3417000, 3417000, 0, 'DA_THANH_TOAN'),
(104, 12025, 952000, 1090000, 140000, 2182000, 2182000, 0, 'DA_THANH_TOAN'),
(203, 12025, 917000, 1410000, 1270000, 3597000, 3597000, 0, 'DA_THANH_TOAN'),
(204, 12025, 952000, 985000, 70000, 2007000, 0, -2007000, 'CHUA_THANH_TOAN'),
(303, 12025, 1190000, 1525000, 1340000, 4055000, 4055000, 0, 'DA_THANH_TOAN'),
(304, 12025, 1239000, 1325000, 1270000, 3834000, 3834000, 0, 'DA_THANH_TOAN'),
(403, 12025, 1190000, 1445000, 1270000, 3905000, 3905000, 0, 'DA_THANH_TOAN'),
(404, 12025, 1239000, 1085000, 70000, 2394000, 0, -2394000, 'CHUA_THANH_TOAN'),
(503, 12025, 1680000, 2080000, 2400000, 6160000, 6160000, 0, 'DA_THANH_TOAN'),
(504, 12025, 1750000, 2360000, 1340000, 5450000, 5450000, 0, 'DA_THANH_TOAN'),
(105, 12025, 917000, 1145000, 1270000, 3332000, 3332000, 0, 'DA_THANH_TOAN'),
(106, 12025, 952000, 1045000, 140000, 2137000, 2137000, 0, 'DA_THANH_TOAN'),
(205, 12025, 917000, 1275000, 1270000, 3462000, 0, -3462000, 'CHUA_THANH_TOAN'),
(206, 12025, 952000, 972000, 70000, 1994000, 0, -1994000, 'CHUA_THANH_TOAN'),
(305, 12025, 1190000, 1558000, 1340000, 4088000, 4088000, 0, 'DA_THANH_TOAN');