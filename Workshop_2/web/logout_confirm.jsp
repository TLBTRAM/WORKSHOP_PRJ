<%-- 
    Document   : logout_confirm
    Created on : Mar 15, 2025, 12:42:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 10px">
         <h4>You are logged out!</h1>
        <a href="MainController">Back to login</a>
         </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
