<%-- 
    Document   : search
    Created on : Mar 15, 2025, 12:42:07 PM
    Author     : Admin
--%>

<%@page import="dto.ExamCategoriesDTO"%>
<%@page import="utils.AuthUtils"%>
<%@page import="dto.ExamsDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .table{
                display: flex;
                justify-content: space-between; /* Để hai bảng nằm hai bên */
                align-items: flex-start; /* Canh trên cùng */
                gap: 20px; /* Khoảng cách giữa hai bảng */
            }

            .exam-table, .category-table {
                width: 48%; /* Điều chỉnh chiều rộng cho mỗi bảng */
                border-collapse: collapse;
                margin: 25px 1%; /* Thêm khoảng cách giữa các bảng */
                font-size: 14px;
                font-family: Arial, sans-serif;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                overflow: hidden;
                background: linear-gradient(135deg, #a3c2f2, #EDC237);
            }

            .exam-table thead th, .category-table thead th {
                background: #EDC237;
                color: #ffffff;
                text-align: left;
                font-weight: bold;
                padding: 14px 18px;
                text-transform: uppercase;
                letter-spacing: 1px;
            }

            .exam-table tbody tr, .category-table tbody tr {
                border-bottom: 1px solid #e0e0e0;
            }

            .exam-table tbody tr:nth-of-type(even), .category-table tbody tr:nth-of-type(even) {
                background-color: #f9f9f9;
            }

            .exam-table tbody td, .category-table tbody td {
                padding: 14px 18px;
                text-align: left;
                font-size: 14px;
                color: #333;
            }

            .exam-table tbody tr:hover, .category-table tbody tr:hover {
                background-color: #cfe2f3;
                transition: 0.3s ease;
                transform: translateY(-2px);
            }

            .search-section {
                background-color: #ffffff;
                border-radius: 8px;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                display: flex;
                align-items: center;
            }

            .search-section form {
                display: flex;
                align-items: center;
                flex-grow: 1;
            }

            .search-section label {
                margin-right: 10px;
                font-weight: bold;
                color: #333;
            }

            .search-input {
                flex-grow: 1;
                padding: 12px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
                margin-right: 10px;
                transition: border-color 0.3s;
            }

            .search-input:focus {
                border-color: #3B967B;
                outline: none;
                box-shadow: 0 0 0 2px rgba(237, 194, 55, 0.2);
            }

            .search-btn {
                background-color: #3B967B;
                color: white;
                border: none;
                border-radius: 4px;
                padding: 12px 18px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            .search-btn:hover {
                background-color: #d8a928;
            }

            .add-btn {
                display: inline-block;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 4px;
                padding: 12px 18px;
                margin-bottom: 20px;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            .add-btn:hover {
                background-color: #0069d9;
                text-decoration: none;
            }

            .add-btn::before {
                content: "+";
                margin-right: 5px;
                font-weight: bold;
            }

            .table-container {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
            }

            @media screen and (max-width: 600px) {
                .exam-table, .category-table {
                    width: 100%;
                    margin: 10px 0;
                }
                .exam-table thead th, .exam-table tbody td, .category-table thead th, .category-table tbody td {
                    padding: 10px 12px;
                }
            }
        </style> 
    </head>
    <body> 
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 10px">
            <% if (session.getAttribute("user") != null) {
                    UserDTO user = (UserDTO) session.getAttribute("user");
            %>

            <%
                String searchTerm = (request.getAttribute("searchTerm") != null) ? request.getAttribute("searchTerm").toString() : "";
            %>
            <div class="search-section">
                <form action="MainController">
                    <input type="hidden" name="action" value="search"/>
                    <label for="searchInput">Search:</label>
                    <input type="text" id="searchInput" name="searchTerm" value="<%=searchTerm%>" class="search-input" placeholder="Enter Exam title, Subject or ID..."/>
                    <input type="submit" value="Search" class="search-btn"/>
                </form>
            </div>

            <% if (AuthUtils.isInstructor(session)) { %>
            <a href="examForm.jsp" class="add-btn">
                Add
            </a> 
            <% } %>
            <div class = "table">
            <%
                List<ExamsDTO> edto = (List<ExamsDTO>) request.getAttribute("listExams");
                if (edto != null) {
            %>
            <h2>View All Exams</h2>

            <div class="table-container">
                <table class="exam-table">
                    <thead>
                        <tr>
                            <th>Exam ID</th>
                            <th>Exam Title</th>
                            <th>Subject</th>
                            <th>Category</th>
                            <th>Total Marks</th>
                            <th>Duration (minutes)</th>
                                <% if (AuthUtils.isInstructor(session)) { %>
                            <th>Action</th>            
                                <% } %>
                        </tr>
                    </thead>

                    <tbody>
                        <% for (ExamsDTO exam : edto) {%>
                        <tr>
                            <td><%= exam.getExam_id()%></td>
                            <td><%= exam.getExam_title()%></td>
                            <td><%= exam.getSubject()%></td>
                            <td><%= exam.getCategory_id()%></td>
                            <td><%= exam.getTotal_marks()%></td>
                            <td><%= exam.getDuration()%></td>
                            <% if (AuthUtils.isInstructor(session)) {%>
                            <td> <a href="examForm.jsp?id=<%=exam.getExam_id()%>">
                                    <img src="assets/images/add.png" style="height: 25px; display: block; margin: auto;" />
                                </a> </td>
                                <% } %>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
            <% if (request.getAttribute("listCategories") != null) {
                    List<ExamCategoriesDTO> categories = (List<ExamCategoriesDTO>) request.getAttribute("listCategories");
            %>
            <h2>View Category Exams</h2>
            <div class="table-container">
                <table class="category-table">
                    <thead>
                        <tr>
                            <th>Category ID</th>
                            <th>Category Name</th>
                            <th>Description</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <% for (ExamCategoriesDTO category : categories) {%>
                        <tr>
                            <td><%= category.getCategory_id()%></td>
                            <td><%= category.getCategory_name()%></td>
                            <td><%= category.getDescription()%></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </div>

            <% } %>

            <% } else { %>
            <p>You do not have permission to access this content.</p>
            <% }%>
        </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
