-- Add new contact information columns
ALTER TABLE sinh_vien
ADD COLUMN so_dien_thoai VARCHAR(15),
ADD COLUMN email VARCHAR(100),
ADD COLUMN dia_chi TEXT,
ADD COLUMN que_quan VARCHAR(255);

-- Add personal information columns
ALTER TABLE sinh_vien
ADD COLUMN dan_toc VARCHAR(50),
ADD COLUMN ton_giao VARCHAR(50);

-- Add CCCD/CMND information columns
ALTER TABLE sinh_vien
ADD COLUMN cccd VARCHAR(20),
ADD COLUMN ngay_cap_cccd DATE,
ADD COLUMN noi_cap_cccd VARCHAR(255);
