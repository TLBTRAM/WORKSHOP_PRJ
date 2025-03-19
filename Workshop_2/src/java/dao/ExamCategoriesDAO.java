/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ExamCategoriesDTO;
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
public class ExamCategoriesDAO implements IDAO<ExamCategoriesDTO, Integer> {

    @Override
    public boolean create(ExamCategoriesDTO entity) {
        String sql = "INSERT INTO [dbo].[tblExamCategories] "
                + "([category_id], [category_name], [description]) "
                + "VALUES (?, ?, ?)";
//int category_id, String category_name, String description
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, entity.getCategory_id());
            psmt.setString(2, entity.getCategory_name());
            psmt.setString(3, entity.getDescription());
            int n = psmt.executeUpdate();
            return n > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<ExamCategoriesDTO> readAll() {
        List<ExamCategoriesDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM tblExamCategories";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamCategoriesDTO ecdto = new ExamCategoriesDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description")
                );
                result.add(ecdto);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public boolean update(ExamCategoriesDTO entity) {
        String sql = "UPDATE tblExamCategories SET"
                + "[category_name] = ?,"
                + "[description] = ?,"
                + "WHERE [category_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getCategory_name());
            ps.setString(2, entity.getDescription());
            ps.setInt(3, entity.getCategory_id());
            int n = ps.executeUpdate();
            return n > 0;
        
        } catch (SQLException ex) {
            Logger.getLogger(ExamCategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamCategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//int category_id, String category_name, String description
    @Override
    public List<ExamCategoriesDTO> search(String searchTerm) {
        String sql = "SELECT * FROM tblExamCategories WHERE title category_name ? OR category_id ?";
        List<ExamCategoriesDTO> list = new ArrayList<ExamCategoriesDTO>();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ps.setString(2, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ExamCategoriesDTO ecdto = new ExamCategoriesDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description"));
                list.add(ecdto);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    @Override
    public ExamCategoriesDTO readByID(Integer id) {
        String sql = "SELECT * FROM tblExamCategories WHERE [category_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();;
            while (rs.next()) {
                ExamCategoriesDTO edto = new ExamCategoriesDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description"));
                return edto;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM tblExamCategories WHERE [category_id] = ?";
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
    
    public static String getCategoryNameById(int category_id) {
        String sql = "SELECT [category_name] "
                + "FROM tblExamCategories "
                + "WHERE [category_id] = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("category_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
