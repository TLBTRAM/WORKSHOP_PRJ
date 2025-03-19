/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Admin
 * Created: Mar 10, 2025
 */

CREATE DATABASE prj301_1820_workshop2
GO

USE prj301_1820_workshop2
GO

CREATE TABLE tblUsers (
    username NVARCHAR(50) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    Role VARCHAR(20) NOT NULL CHECK (Role IN ('Instructor', 'Student'))
);
GO

INSERT INTO tblUsers (username, Name, password, Role) VALUES
('nguyenan', N'Nguyễn An', 'password123', 'Student'),
('tranbinh', N'Trần Bình', 'securepass', 'Instructor'),
('phamcuong', N'Phạm Cường', '123456', 'Student'),
('lehoa', N'Lê Hoa', 'hoa789', 'Student'),
('dangthuy', N'Đặng Thúy', 'thuysecure', 'Instructor');
GO