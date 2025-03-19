<%-- 
    Document   : login
    Created on : Mar 15, 2025, 12:42:03 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <style>
            .login-container {
                min-height: 500px;
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #f5f5f5;
                padding: 20px;
            }

            .login-form {
                background: white;
                padding: 30px;
                border-radius: 8px;
                border: 3px solid #EDC237; /* Viền vàng đậm hơn */
                box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2); /* Đổ bóng mạnh hơn */
                width: 100%;
                max-width: 400px;
                transition: all 0.3s ease;
            }

            .login-form:hover {
                box-shadow: 0px 12px 25px rgba(0, 0, 0, 0.3); /* Hiệu ứng khi hover */
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: 600; /* Chữ đậm hơn */
                color: #333;
            }

            .form-group input {
                width: 100%;
                padding: 12px;
                border: 2px solid #ddd;
                border-radius: 6px;
                font-size: 16px;
                transition: all 0.3s ease;
            }

            .form-group input:focus {
                border-color: #EDC237;
                box-shadow: 0px 0px 8px rgba(237, 194, 55, 0.5);
                outline: none;
            }

            .submit-btn {
                background: linear-gradient(135deg, #EDC237, #D1A62D);
                color: white;
                padding: 14px 20px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                width: 100%;
                font-size: 18px;
                font-weight: bold;
                text-transform: uppercase;
                transition: all 0.3s ease;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }

            .submit-btn:hover {
                background: linear-gradient(135deg, #D1A62D, #B88C26);
                box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.2);
            }

            .form-title {
                text-align: center;
                margin-bottom: 30px;
                color: #333;
                font-size: 24px;
                font-weight: bold;
                text-transform: uppercase;
                letter-spacing: 1px;
            }

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="login-container">
            <div class="login-form">
                <h2 class="form-title">Đăng nhập</h2>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="login" />

                    <div class="form-group">
                        <label for="username">Tên đăng nhập</label>
                        <input type="text" id="Username" name="txtUsername" required />
                    </div>

                    <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" id="Password" name="txtPassword" required />
                    </div>

                    <button type="submit" class="submit-btn">Đăng nhập</button>

                    <%
                        String message = request.getAttribute("message") + "";
                    %>
                    <%=message.equals("null") ? "" : message%>
                </form>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
