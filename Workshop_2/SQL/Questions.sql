/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Admin
 * Created: Mar 10, 2025
 */
CREATE TABLE tblQuestions (
    question_id INT PRIMARY KEY,
    exam_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a NVARCHAR(100) NOT NULL,
    option_b NVARCHAR(100) NOT NULL, 
    option_c NVARCHAR(100) NOT NULL,
    option_d NVARCHAR(100) NOT NULL,
    correct_option CHAR(1) NOT NULL CHECK (correct_option IN ('A', 'B', 'C', 'D')),
    CONSTRAINT FK_exam FOREIGN KEY (exam_id) REFERENCES tblExams(exam_id)
);
GO

INSERT INTO tblQuestions (question_id, exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
(1, 1, N'Số nào là số nguyên tố?', '4', '6', '7', '9', 'C'), -- SỐ nào là số nguyên tố: 7 là số nguyên tố nên đáp án C
(2, 1, N'Kết quả của 5 + 3 × 2 là?', '16', '11', '10', '13', 'B'), -- 
(3, 2, N'Vật lý: Đơn vị đo lực là gì?', 'Newton', 'Joule', 'Watt', 'Pascal', 'A'),
(4, 3, N'Nguyên tố nào có ký hiệu hóa học là O?', N'Vàng', N'Oxi', N'Bạc', N'Sắt', 'B'),
(5, 4, N'Which is the synonym of "happy"?', 'Sad', 'Joyful', 'Angry', 'Bored', 'B'),
(6, 5, N'[Hello]?', N'Yêu bạn', N'Tôi khỏe', N'Xin chào', N'Tạm biệt', 'C');
GO