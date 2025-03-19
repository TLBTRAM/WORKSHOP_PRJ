/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ExamCategoriesDAO;
import dao.ExamsDAO;
import dto.ExamCategoriesDTO;
import dto.ExamsDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
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

    private final ExamCategoriesDAO ecdao = new ExamCategoriesDAO();
    private final ExamsDAO edao = new ExamsDAO();
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String SEARCH_PAGE = "search.jsp";
    private static final String EXAM_FORM_PAGE = "examFrom.jsp";

    protected String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;

        HttpSession session = request.getSession();
        String strUsername = request.getParameter("txtUsername");
        String strPassword = request.getParameter("txtPassword");
        if (AuthUtils.isValidLogin(strUsername, strPassword)) {
            UserDTO user = AuthUtils.getUser(strUsername);
            session.setAttribute("user", user);

            List<ExamCategoriesDTO> listecdao = ecdao.readAll();
            request.setAttribute("list", listecdao);
            url = SEARCH_PAGE;
        } else {
            request.setAttribute("message", "Incorrect Username or Password!");
            url = LOGIN_PAGE;
        }
        return url;
    }

    protected String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SEARCH_PAGE;
        List<ExamCategoriesDTO> listecdao = ecdao.readAll();
        request.setAttribute("list", listecdao);
        return url;
    }

    protected String processViewExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;

        // Cần đăng nhập mới cho view
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {

            List<ExamsDTO> listExams = edao.viewExam();
            request.setAttribute("listExams", listExams);

        }
        return url;
    }
    
    protected String processViewExamCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        
        // Cần đăng nhập mới cho view
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            
            List<ExamCategoriesDTO> listCategories = ecdao.viewExamCategory();
            request.setAttribute("listCategories", listCategories);
            
        }
        return url;
    }
    
    protected String processCreateExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SEARCH_PAGE;
        String exam_title = request.getParameter("exam_title");
        String subject = request.getParameter("Subject");
        String strcategory_id = request.getParameter("category_id");
        String strtotal_marks = request.getParameter("total_marks");
        String strDuration = request.getParameter("Duration");

        boolean check = false;
        if (exam_title == null || exam_title.trim().equals("")) {
            check = true;
            request.setAttribute("Error_exam_title", "Exam Title can't empty!");
        }
        if (subject == null || subject.trim().equals("")) {
            check = true;
            request.setAttribute("Error_subject", "Subject can't empty!");
        }

        int category_id = 0;
        if (strcategory_id == null || strcategory_id.trim().isEmpty()) {
            check = true;
            request.setAttribute("Error_category_id", "Category can't be empty!");
        } else {
            try {
                category_id = Integer.parseInt(strcategory_id);
            } catch (NumberFormatException e) {
                check = true;
                request.setAttribute("Error_category_id", "Invalid Category!");
            }
        }

        int total_marks = 0;
        if (strtotal_marks == null || strtotal_marks.trim().isEmpty()) {
            check = true;
            request.setAttribute("Error_total_marks", "Total Marks can't be empty!");
        } else {
            try {
                total_marks = Integer.parseInt(strtotal_marks);
                if (total_marks <= 0) {
                    check = true;
                    request.setAttribute("Error_total_marks", "Total Marks must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                check = true;
                request.setAttribute("Error_total_marks", "Invalid Total Marks!");
            }
        }

        int Duration = 0;
        if (strDuration == null || strDuration.trim().isEmpty()) {
            check = true;
            request.setAttribute("Error_duration", "Duration can't be empty!");
        } else {
            try {
                Duration = Integer.parseInt(strDuration);
                if (Duration <= 0) {
                    check = true;
                    request.setAttribute("Error_duration", "Duration must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                check = true;
                request.setAttribute("Error_duration", "Invalid Duration!");
            }
        }

        ExamsDTO exam = new ExamsDTO(0, exam_title, subject, category_id, total_marks, Duration);
        request.setAttribute("exam", exam);
        List<ExamCategoriesDTO> list = ecdao.readAll();
        request.setAttribute("list", list);
        if (check) {
            url = EXAM_FORM_PAGE;
        } else {
            boolean result = edao.create(exam);
            if (result) {
                request.setAttribute("message", "Create Exam Successfully!");
            } else {
                request.setAttribute("message", "Create Exam Fail!");
            }
            url = EXAM_FORM_PAGE;
        }
        return url;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                    url = processViewExam(request, response);
                    url = processViewExamCategory(request, response);
                    url = processSearch(request, response);
                } else if (action.equals("search")) {
                    url = processViewExam(request, response);
                    url = processViewExamCategory(request, response);
                    url = processSearch(request, response);
                } else if (action.equals("logout")) {
                    request.getSession().invalidate();
                    url = LOGIN_PAGE;
                } else if (action.equals("goToCreateExam")) {
                    List<ExamCategoriesDTO> list = ecdao.readAll();
                    request.setAttribute("list", list);
                    url = EXAM_FORM_PAGE;
                } else if (action.equals("createExam")) {
                    url = processCreateExam(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at ControllerLogin : " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
