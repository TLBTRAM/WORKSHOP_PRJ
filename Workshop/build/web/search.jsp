<%-- 
    Document   : search
    Created on : 04-03-2025, 01:06:25
    Author     : Admin
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.ProjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-16WWW">
        <title>JSP Page</title>

        <style>
            .book-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 14px;
                font-family: Arial, sans-serif;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .book-table thead th {
                background-color: #EF9A12;
                color: #ffffff;
                text-align: left;
                font-weight: bold;
                padding: 12px 15px;
            }

            .book-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }

            .book-table tbody tr:nth-of-type(even) {
                background-color: #ffebcc;
            }

            .book-table tbody tr:last-of-type {
                border-bottom: 2px solid #EF9A12;
            }

            .book-table tbody td {
                padding: 12px 15px;
            }

            .book-table tbody tr:hover {
                background-color: #ffd699; 
                transition: 0.3s ease;
            }

            /* Nút tìm kiếm và thêm */
            .search-btn, .add-btn {
                background-color: #EF9A12; 
                color: white;
                border: none;
                border-radius: 4px;
                padding: 10px 15px;
                cursor: pointer;
                font-weight: bold;
                transition: background-color 0.3s;
            }

            .search-btn:hover, .add-btn:hover {
                background-color: #e65c00; 
            }

            /* Responsive */
            @media screen and (max-width: 600px) {
                .book-table {
                    font-size: 12px;
                }

                .book-table thead th,
                .book-table tbody td {
                    padding: 8px 10px;
                }
            }
        </style> 
    </head>
    <body> 
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 10px">
            <%                if (session.getAttribute("user") != null) {
                    UserDTO user = (UserDTO) session.getAttribute("user");
            %>

            <%
                String searchTerm = request.getAttribute("searchTerm") + "";
                searchTerm = searchTerm.equals("null") ? "" : searchTerm;
            %>
            <div class="search-section">
                <form action="MainController">
                    <input type="hidden" name="action" value="search"/>
                    <label for="searchInput">Search Project:</label>
                    <input type="text" id="searchInput" name="searchTerm" value="<%=searchTerm%>" class="search-input" placeholder="Enter project ..."/>
                    <input type="submit" value="Search" class="search-btn"/>
                </form>
            </div>
            <% if (AuthUtils.isAdmin(session)) {
            %>
            <a href="projectFrom.jsp" class="add-btn">
                Add New Projects
            </a> 
            <%}%>

            <%
                List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("searchpj");
                if (projects != null) {
            %>
            <table class="book-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Project</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Estimated Launch</th>
                            <% if (AuthUtils.isAdmin(session)) {
                            %>
                        <th>Action</th>
                            <%}%>
                    </tr>
                </thead>
                <tbody>
                    <%            for (ProjectDTO pdto : projects) {
                    %>
                    <tr>
                        <td><%=pdto.getProject_id()%></td>
                        <td><%=pdto.getProject_name()%></td>
                        <td><%=pdto.getDescription()%></td>
                        <td><%=pdto.getStatus()%></td>
                        <td><%=pdto.getEstimated_launch()%></td>
                        <%
                            if (AuthUtils.isAdmin(session)) {
                        %>
                        <td>
                            <form action ="MainController" method="post" style ="display:flex; align-items:center;gap:5px;">
                                <input type ="hidden" name="action" value="update">
                                <input type ="hidden" name="id" value="<%=pdto.getProject_id()%>">

                                <select name="status_update" style="padding: 5px;">
                                    <option value="Ideation" <%=pdto.getStatus().equals("Ideation") ? "selected" : ""%>>Ideation </option>
                                    <option value="Development" <%=pdto.getStatus().equals("Development") ? "selected" : ""%>>Development </option>
                                    <option value="Launch" <%=pdto.getStatus().equals("Launch") ? "selected" : ""%>>Launch </option>
                                    <option value="Scaling" <%=pdto.getStatus().equals("Scaling") ? "selected" : ""%>>Scaling </option>
                                </select>
                                <button type="submit" style="border:none;background:none;cursor:pointer;">
                                    <img src ="assets/images/update.png"style="height: 25px; display:block; margin:auto;">
                                </button>
                            </form>
                        </td>

                        <%}%>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%    }%>

            <%} else { %>
            <p>You do not have permission to access this content.</p>
            <%}%>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>