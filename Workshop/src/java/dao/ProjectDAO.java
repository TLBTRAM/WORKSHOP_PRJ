/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author mah
 */
public class ProjectDAO implements IDAO<ProjectDTO, String> {

    @Override
    public boolean create(ProjectDTO entity) {
        LocalDate estimated_launch = entity.getEstimated_launch();
        String sql = "INSERT INTO tblStartupProjects"
                + " (project_id,project_name,Description,Status,estimated_launch) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, entity.getProject_id());
            ps.setString(2, entity.getProject_name());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getStatus());
            LocalDate estimatedLaunch = entity.getEstimated_launch();
            ps.setDate(5, (estimated_launch != null) ? Date.valueOf(estimated_launch) : null);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    @Override
    public List<ProjectDTO> readAll() {
        return null;
    }

    @Override
    public ProjectDTO readById(String id) {
        return null;
    }

    @Override
    public boolean update(ProjectDTO entity) {
        return false;
    }

    @Override
    public List<ProjectDTO> search(String searchTerm) {
        return null;
    }

    public List<ProjectDTO> searchByName(String searchTerm) {
        String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ?";
        List<ProjectDTO> list = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProjectDTO p = new ProjectDTO(
                            rs.getInt("project_id"),
                            rs.getString("project_name"),
                            rs.getString("Description"),
                            rs.getString("Status"),
                            rs.getDate("estimated_launch") != null ? rs.getDate("estimated_launch").toLocalDate() : null);
                    list.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public boolean updateStatus(int project_id, String Status) {
        String sql = "UPDATE tblStartupProjects SET Status = ? WHERE project_id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, project_id);
            ps.setString(2, Status);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
