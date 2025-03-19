/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.QuestionsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author mah
 */
public class QuestionsDAO implements IDAO<QuestionsDTO, String> {

    @Override
    public boolean create(QuestionsDTO entity) {
        String sql = "INSERT INTO [dbo].[tblQuestions] "
                + "([question_id], [exam_id], [question_text], [option_a], [option_b], [option_c], [option_d],[correct_option])"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//int question_id, int exam_id, String question_text, String option_a, String option_b, String option_c, String option_d, String correct_option
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entity.getQuestion_id());
            ps.setInt(2, entity.getExam_id());
            ps.setString(3, entity.getQuestion_text());
            ps.setString(4, entity.getOption_a());
            ps.setString(5, entity.getOption_b());
            ps.setString(6, entity.getOption_c());
            ps.setString(7, entity.getOption_d());
            ps.setString(8, entity.getCorrect_option());
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<QuestionsDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public QuestionsDTO readByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(QuestionsDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//[question_id], [exam_id], [question_text], [option_a], [option_b], [option_c], [option_d],[correct_option]
    @Override
    public List<QuestionsDTO> search(String searchTerm) {
        List<QuestionsDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM tblQuestions WHERE exam_id LIKE ? OR question_id LIKE ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            String param = "%" + searchTerm + "%";
            ps.setString(1, param);
            ps.setString(2, param);
            ps.setString(3, param);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionsDTO qdto = new QuestionsDTO(
                        rs.getInt("question_id"),
                        rs.getInt("exam_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option")
                );
                result.add(qdto);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

}
