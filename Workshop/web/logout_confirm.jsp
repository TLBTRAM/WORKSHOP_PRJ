<%-- 
    Document   : logout_confirm
    Created on : 04-03-2025, 01:05:26
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
