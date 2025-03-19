<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }

    /* Header Styling */
    .header {
        background: linear-gradient(135deg, #1a1a2e, #16213e);
        padding: 1.2rem 0;
        width: 100%;
        top: 0;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 1.5rem;
    }

    .nav {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    /* Logo */
    .logo {
        color: #f1c40f;
        font-size: 1.8rem;
        font-weight: bold;
        text-decoration: none;
        letter-spacing: 1px;
        transition: transform 0.3s;
    }

    .logo:hover {
        transform: scale(1.1);
    }

    /* Navigation Menu */
    .menu {
        display: flex;
        list-style: none;
        gap: 2rem;
    }

    .menu-item a {
        color: #ecf0f1;
        text-decoration: none;
        font-size: 1rem;
        transition: color 0.3s ease-in-out;
        position: relative;
    }

    .menu-item a:hover {
        color: #f1c40f;
    }

    .separator {
        color: #f1c40f;
        font-weight: bold;
    }

    /* Search Bar */
    .search-bar {
        display: flex;
        align-items: center;
        background: #fff;
        border-radius: 25px;
        padding: 0.6rem 1rem;
        box-shadow: 0 2px 10px rgba(255, 255, 255, 0.1);
    }

    .search-input {
        border: none;
        outline: none;
        padding: 0.3rem;
        width: 200px;
        font-size: 0.9rem;
    }

    .search-button {
        background: none;
        border: none;
        cursor: pointer;
        color: #16213e;
        font-size: 1.1rem;
        transition: color 0.3s;
    }

    .search-button:hover {
        color: #f1c40f;
    }

    /* User Section */
    .user-section {
        display: flex;
        align-items: center;
        gap: 1rem;
        margin-left: 1.5rem;
    }

    .welcome-text {
        color: #ecf0f1;
        font-size: 1rem;
    }

    .user-name {
        color: #f1c40f;
        font-weight: bold;
        font-size: 1.1rem;
    }

    .logout-btn {
        background: linear-gradient(135deg, #e74c3c, #c0392b);
        color: white;
        border: none;
        border-radius: 5px;
        padding: 0.5rem 1rem;
        font-size: 0.9rem;
        cursor: pointer;
        transition: background 0.3s, transform 0.2s;
    }

    .logout-btn:hover {
        background: #e74c3c;
        transform: translateY(-2px);
    }

    .login-btn {
        background: linear-gradient(135deg, #2980b9, #3498db);
        color: white;
        padding: 0.5rem 1rem;
        border-radius: 5px;
        text-decoration: none;
        font-size: 0.9rem;
        transition: background 0.3s, transform 0.2s;
    }

    .login-btn:hover {
        background: #3498db;
        transform: translateY(-2px);
    }

    /* Right Section */
    .right-section {
        display: flex;
        align-items: center;
    }
</style>

<header class="header">
    <div class="container">
        <nav class="nav">
            <a href="#" class="logo">Online Examination</a>
            <ul class="menu">
                <li class="menu-item"><a href="#">Home</a></li>
                <li class="separator">|</li>
                <li class="menu-item"><a href="#">Categories</a></li>
                <li class="separator">|</li>
                <li class="menu-item"><a href="#">Exam</a></li>
            </ul>
            <div class="right-section">
                <!-- Thanh tìm kiếm -->
                <div class="search-bar">
                    <input type="text" class="search-input" placeholder="Searching...">
                    <button class="search-button">🔍</button>
                </div>

                <!-- Kiểm tra đăng nhập -->
                <% 
                    boolean isLoggedIn = AuthUtils.isLoggedIn(session);
                    UserDTO userHeader = isLoggedIn ? AuthUtils.getUser(session) : null;
                %>

                <% if (isLoggedIn && userHeader != null) { %>
                <div class="user-section">
                    <span class="welcome-text">
                        Welcome, <span class="user-name"><%= userHeader.getName() %></span>!
                    </span>
                    <form action="MainController" method="post" class="logout-form">
                        <input type="hidden" name="action" value="logout"/>
                        <input type="submit" value="Logout" class="logout-btn"/>
                    </form>
                </div>
                <% } else { %>
                <a href="login.jsp" class="login-btn">Đăng nhập</a>
                <% } %>
            </div>
        </nav>
    </div>
</header>
