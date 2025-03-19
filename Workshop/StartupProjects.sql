-- Tạo bảng DỰ ÁN STARUP
CREATE TABLE tblStartupProjects(
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    Description TEXT,
    Status VARCHAR(20) NOT NULL CHECK (Status IN ('Ideation', 'Development', 'Launch', 'Scaling')),
    estimated_launch DATE NOT NULL
);

-- Thêm dữ liệu mẫu
INSERT INTO tblStartupProjects (project_id, project_name, Description, Status, estimated_launch) VALUES
('1', 'AI Assistant', 'An AI-powered virtual assistant for businesses.', 'Development', '2025-06-15'),
('3', 'E-commerce Platform', 'A multi-vendor online marketplace.', 'Launch', '2025-04-20'),
('4', 'Health Tracker', 'A wearable device for health monitoring.', 'Ideation', '2025-09-10'),
('5', 'Smart Home App', 'A mobile app to control smart home devices.', 'Scaling', '2025-07-01'),
('6', 'Blockchain Wallet', 'A secure cryptocurrency wallet.', 'Development', '2025-08-30');
