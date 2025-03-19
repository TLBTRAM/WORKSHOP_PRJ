/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;


import java.time.LocalDate;

/**
 *
 * @author mah
 */
public class ProjectDTO {
    private int project_id;
    private String project_name;
    private String Description;
    private String Status;
    private LocalDate estimated_launch;

    public ProjectDTO() {
        this.project_id = 0;
        this.project_name = "";
        this.Description = "";
        this.Status = "";
        this.estimated_launch = null;
    }

    public ProjectDTO(int project_id, String project_name, String Description, String Status, LocalDate estimated_launch) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.Description = Description;
        this.Status = Status;
        this.estimated_launch = estimated_launch;
    }
    
    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public LocalDate getEstimated_launch() {
        return estimated_launch;
    }

    public void setEstimated_launch(LocalDate estimated_launch) {
        this.estimated_launch = estimated_launch;
    }

}
