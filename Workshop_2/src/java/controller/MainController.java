/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamCategoriesDAO;
import dao.ExamsDAO;
import dao.UserDAO;
import dto.ExamsDTO;
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

    private ExamCategoriesDAO ecdao = new ExamCategoriesDAO();
    private ExamsDAO edao = new ExamsDAO();

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String SEARCH_PAGE = "search.jsp";
    

    public UserDTO getUser(String strUsername) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByID(strUsername);
        return user;
    }

   public boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        System.out.println(user);
        System.out.println(strPassword);
        return user != null && user.getPassword().equals(strPassword);
    } 

    public void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null) {
            searchTerm = "";
        }
        List<ExamsDTO> searchexam = edao.search(searchTerm);
        request.setAttribute("searchexam", searchexam);
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
                        url = SEARCH_PAGE;
                        UserDTO user = getUser(strUsername);
                        request.getSession().setAttribute("user", user);
                        // search
                        search(request, response);
                    } else {
                        request.setAttribute("message", "Incorrect UserID or Password");
                        url = LOGIN_PAGE;
                    }
                } else if (action.equals("logout")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        request.getSession().invalidate(); 
                        url = LOGIN_PAGE;
                    }
                } else if (action.equals("search")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        // search
                        search(request, response);
                        url = SEARCH_PAGE;
                    }
                } else if (action.equals("update")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Founder")) {
                            int id = Integer.parseInt(request.getParameter("id"));
//                            
//                            prdao.updateStatus(id, Status);
//                            // search
//                            search(request, response);
//                            url = SEARCH_PAGE;
                        }
                    }
                } else if (action.equals("add")) {
                    HttpSession session = request.getSession();
                    if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        if (user.getRole().equals("Instructor")) {
                            try {
                                boolean checkError = false;
                                //exam_id, exam_title, Subject, category_id, total_marks, Duration
                                int exam_id = Integer.parseInt(request.getParameter("txtExamID"));
                                String exam_title = request.getParameter("txtExamTitle");
                                String Subject = request.getParameter("txtSubject");
                                int category_id = Integer.parseInt(request.getParameter("txtCategoryID"));
                                int total_marks = Integer.parseInt(request.getParameter("txtTotalMarks"));    
                                int Duration = Integer.parseInt(request.getParameter("txtDuration"));  
                                if (exam_id < 0) {
                                    checkError = true;
                                    request.setAttribute("txtExamID_error", "Exam ID cannot be empty.");
                                }

                                 if (exam_title == null || exam_title.trim().isEmpty()) {
                                    checkError = true;
                                    request.setAttribute("txtExamTitle_error", "Name of Exam cannot be empty!");
                                }

                                ExamsDTO edto = new ExamsDTO(exam_id, exam_title, Subject, category_id, total_marks, Duration);

                                if (!checkError) {
                                    edao.create(edto);
                                    // search
                                    search(request, response);
                                    url = SEARCH_PAGE;
                                } else {
                                    url = "examForm.jsp";
                                    request.setAttribute("projects", edto);
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
