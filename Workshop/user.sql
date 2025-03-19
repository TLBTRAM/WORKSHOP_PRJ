CREATE DATABASE prj301_workshop_01
GO
-- Sử dụng database vừa tạo
USE prj301_workshop_01
GO

-- Tạo bảng tblUsers
CREATE TABLE tblUsers (
    Username VARCHAR(50) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('Founder', 'Team Member'))
);
GO

-- Thêm 10 người dùng mẫu với tên tiếng Việt
INSERT INTO tblUsers (Username, Name, Password, Role) VALUES
('admin01', 'Quản Trị Viên', 'admin123', 'Founder'),
('mem001', 'Nguyễn Văn Anh', 'mem123', 'Team Member'),
('mem002', 'Trần Thị Bình', 'mem1456', 'Team Member'),
('mem003', 'Lê Hoàng Nam', 'pass789', 'Team Member'),
('mem004', 'Phạm Thị Hồng', 'hong999', 'Team Member'),
('mem005', 'Bùi Văn Dũng', 'dung555', 'Team Member')
GO