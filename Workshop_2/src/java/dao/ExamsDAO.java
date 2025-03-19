/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author mah
 */
public class ExamsDAO implements IDAO<ExamsDTO, Integer> {
//[exam_id], [exam_title], [Subject], [category_id], [total_marks]

    @Override
    public boolean create(ExamsDTO entity) {
        String sql = "INSERT INTO [dbo].[tblExams] "
                + "([exam_id], [exam_title], [Subject], [category_id], [total_marks], [Duration]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, entity.getExam_id());
            psmt.setString(2, entity.getExam_title());
            psmt.setString(3, entity.getSubject());
            psmt.setInt(4, entity.getCategory_id());
            psmt.setInt(5, entity.getTotal_marks());
            psmt.setInt(6, entity.getDuration());
            int i = psmt.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
//[exam_id], [exam_title], [Subject], [category_id], [total_marks], [Duration]

    @Override
    public List<ExamsDTO> readAll() {
        List<ExamsDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM tblExams";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamsDTO edto = new ExamsDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                result.add(edto);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public ExamsDTO readByID(Integer id) {
        String sql = "SELECT * FROM tblExams WHERE [exam_id] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ExamsDTO edto = new ExamsDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                return edto;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(ExamsDTO entity) {
        String sql = "UPDATE tblExams SET"
                + "[exam_title] = ?,"
                + "[subject] = ?,"
                + "[category_id] = ?,"
                + "[total_marks] = ?,"
                + "[duration] = ?"
                + "WHERE [exam_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getExam_title());
            ps.setString(2, entity.getSubject());
            ps.setInt(3, entity.getCategory_id());
            ps.setInt(4, entity.getTotal_marks());
            ps.setInt(5, entity.getDuration());
            ps.setInt(6, entity.getCategory_id());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ExamsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM tblExams WHERE [exam_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            return n > 0;

        } catch (Exception e) {
        }
        return false;
    }
//[exam_id], [exam_title], [Subject], [category_id], [total_marks]

    @Override
    public List<ExamsDTO> search(String searchTerm) {
        List<ExamsDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM tblExams WHERE exam_title LIKE ? OR Subject LIKE ? OR category_id LIKE ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            String param = "%" + searchTerm + "%";
            psmt.setString(1, param);
            psmt.setString(2, param);
            psmt.setString(3, param);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                ExamsDTO edto = new ExamsDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration"));
                result.add(edto);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public static List<ExamsDTO> getExamCategoryByID(int category_id) {
        String sql = "  SELECT exam_id, exam_title, subject,category_id, total_marks, duration"
                + " FROM tblExams  "
                + "WHERE category_id = ? ";
        List<ExamsDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamsDTO exam = new ExamsDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("duration"));
                list.add(exam);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
