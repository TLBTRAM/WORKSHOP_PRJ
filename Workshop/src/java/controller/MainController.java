/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

/**
 *
 * @author mah
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private ProjectDAO prdao = new ProjectDAO();

    private static final String LOGIN_PAGE = "login.jsp";
    
    

    public UserDTO getUser(String strUsername) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readById(strUsername);
        return user;
    }

   public boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        System.out.println(user);
//        System.out.println(user.getPassword());
        System.out.println(strPassword);
        return user != null && user.getPassword().equals(strPassword);
    } 

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<ProjectDTO> searchpj = prdao.searchByName(searchTerm);
        request.setAttribute("searchpj", searchpj);
        request.setAttribute("searchTerm", searchTerm);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            System.out.println("action: " + action);
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    String strUsername = request.getParameter("txtUsername");
                    String strPassword = request.getParameter("txtPassword");
                    if (isValidLogin(strUsername, strPassword)) {
                        url = "search.jsp";
                        UserDTO user = getUser(strUsername);
                        request.getSession().setAttribute("user", user);
                        // search
                        search(request, response);
                    } else {
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url = "login.jsp";
                    }
                } else if (action.equals("logout")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        request.getSession().invalidate(); 
                        url = "login.jsp";
                    }
                } else if (action.equals("search")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        // search
                        search(request, response);
                        url = "search.jsp";
                    }
                } else if (action.equals("update")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            int id = Integer.parseInt(request.getParameter("id"));
                            String Status = request.getParameter("status_update");
                            prdao.updateStatus(id, Status);
                            // search
                            search(request, response);
                            url = "search.jsp";
                        }
                    }
                } else if (action.equals("add")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            try {
                                boolean checkError = false;

                                int project_id = Integer.parseInt(request.getParameter("txtProjectID"));
                                String project_name = request.getParameter("txtProjectName");
                                String Description = request.getParameter("txtDescription");
                                String Status = request.getParameter("txtStatus");
                                String estimatedLaunchStr = request.getParameter("txtEstimatedLaunch");                                
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng phù hợp với dữ liệu đầu vào
                                LocalDate estimated_launch = LocalDate.parse(estimatedLaunchStr, formatter);

                                if (project_id < 0) {
                                    checkError = true;
                                    request.setAttribute("txtProjectID_error", "Project ID cannot be empty.");
                                }

                                 if (project_name == null || project_name.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("txtProjectName_error", "Name of Project cannot be empty!");
                                }

                                ProjectDTO prj = new ProjectDTO(project_id, project_name, Description, Status, estimated_launch);

                                if (!checkError) {
                                    prdao.create(prj);
                                    // search
                                    search(request, response);
                                    url = "search.jsp";
                                } else {
                                    url = "projectForm.jsp";
                                    request.setAttribute("projects", prj);
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
