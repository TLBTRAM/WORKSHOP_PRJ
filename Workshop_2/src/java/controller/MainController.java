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

    protected String processFilter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = SEARCH_PAGE;
        String category_id = request.getParameter("category_id");
        System.out.println(category_id);
        if (category_id != null && !category_id.isEmpty()) {
            int category_idInt = Integer.parseInt(category_id);
            List<ExamsDTO> listExamDTO = ExamsDAO.getExamCategoryByID(category_idInt);
            String category_name = ecdao.getCategoryNameById(category_idInt);
            request.setAttribute("listExamDTO", listExamDTO);
            request.setAttribute("category_name", category_name);
        } else {
            request.setAttribute("message_Filter", "Please choose Category Name!");
        }
        List<ExamCategoriesDTO> list = ecdao.readAll();
        request.setAttribute("list", list);
        url = SEARCH_PAGE;
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

    protected String processAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isInstructor(session)) {
            try {
                boolean checkError = false;

                int exam_id = Integer.parseInt(request.getParameter("txtExamID"));
                String exam_title = request.getParameter("txtExamTitle");
                String Subject = request.getParameter("txtSubject");
                int category_id = Integer.parseInt(request.getParameter("txtCategoryID"));
                int total_marks = Integer.parseInt(request.getParameter("txtTotalMarks"));
                int Duration = Integer.parseInt(request.getParameter("txtDuration"));

                if (exam_id < 0) {
                    checkError = true;
                    request.setAttribute("txtExamID_error", "ID of Exam must be greater than zero !");
                }
                if (exam_title == null || exam_title.trim().isEmpty()) {
                    checkError = true;
                    request.setAttribute("txtExamTitle_error", "Title cannot be empty !");
                }
                if (Subject == null || Subject.trim().isEmpty()) {
                    checkError = true;
                    request.setAttribute("txtSubject_error", "Subject Just only (EnglishFunny,EnglishSame,EnglishMix) !");
                }
                if (category_id < 0 || category_id > 7) {
                    checkError = true;
                    request.setAttribute("txtCategoryID_error", "Category ID must be greater than zero and Just enter [1-7] !");
                }
                if (total_marks < 0) {
                    checkError = true;
                    request.setAttribute("txtTotalMarks_error", "Score must be greater than zero !");
                }
                if (Duration < 0 || Duration > 200) {
                    checkError = true;
                    request.setAttribute("txtDuration_error", "Duration is less than 200 minutes !");
                }

                ExamsDTO exam = new ExamsDTO(exam_id, exam_title, Subject, category_id, total_marks, Duration);

                if (!checkError) {
                    edao.create(exam);
                    // search
                    url = SEARCH_PAGE;
                    processSearch(request, response);
                } else {
                    request.setAttribute("exam", exam);
                    url = EXAM_FORM_PAGE;
                }
            } catch (Exception e) {
            }
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
                } else if (action.equals("filter")) {
                    url = processFilter(request, response);
                } else if (action.equals("logout")) {
                    request.getSession().invalidate(); // Hủy session = Hủy 1 phiên làm việc
                    url = LOGIN_PAGE;
                } else if (action.equals("add")) {
                    url = processAdd(request, response);
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
