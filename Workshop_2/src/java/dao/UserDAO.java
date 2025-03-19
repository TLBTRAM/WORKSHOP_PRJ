/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
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
public class UserDAO implements IDAO<UserDTO, String> {

    @Override
    public boolean create(UserDTO entity) {
        String sql = "INSERT [dbo].[tblUsers] "
                + "([username],[Name],[password],[Role]) "
                + "VALUES (?, ?, ?, ?)";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getPassword());
            ps.setString(4, entity.getRole());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (Exception e) {
        }
        return false;
    }
//[username],[Name],[password],[Role]
    @Override
    public List<UserDTO> readAll() {
        List<UserDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblUsers";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("username"),
                        rs.getString("Name"),
                        rs.getString("password"),
                        rs.getString("Role"));
                list.add(user);
            }
        } catch (Exception e) {
        }
        return list;
    }
//[username],[Name],[password],[Role]
    @Override
    public UserDTO readByID(String id) {
        String sql = "SELECT * FROM tblUsers WHERE [username] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("username"),
                        rs.getString("Name"),
                        rs.getString("password"),
                        rs.getString("Role"));
                return user;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//[username],[Name],[password],[Role]
    @Override
    public boolean update(UserDTO entity) {
        String sql = "UPDATE tblUsers SET "
                + "[Name] = ?, "
                + "[password] = ?, "
                + "[Role] = ? "
                + "WHERE [username] = ?";

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getPassword());
            pstmt.setString(3, entity.getRole());
            pstmt.setString(4, entity.getUsername());
            int n = pstmt.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM tblUsers WHERE [username] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int n = ps.executeUpdate(sql);
            return n > 0;
        } catch (Exception e) {
        }
        return false;
    }
//[username],[Name],[password],[Role]
    @Override
    public List<UserDTO> search(String searchTerm) {
        List<UserDTO> list = new ArrayList<>();
        String sql = "SELECT [username], [Name], [password] FROM [tblUsers] "
                + "WHERE [username] LIKE ? "
                + "OR [Name] LIKE ? "
                + "OR [role] like ?";

        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    UserDTO user = new UserDTO(
                            rs.getString("username"),
                            rs.getString("Name"),
                            rs.getString("password"),
                            rs.getString("Role")
                    );
                    list.add(user);
                }
            }
        }  catch (Exception e) {
        }
        return list;
    }
}
