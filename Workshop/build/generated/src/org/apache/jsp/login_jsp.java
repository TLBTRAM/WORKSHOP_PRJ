package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import utils.AuthUtils;
import dto.UserDTO;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/header.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Đăng nhập</title>\n");
      out.write("        <style>\n");
      out.write("            .login-container {\n");
      out.write("                min-height: 500px;\n");
      out.write("                display: flex;\n");
      out.write("                justify-content: center;\n");
      out.write("                align-items: center;\n");
      out.write("                background-color: #f5f5f5;\n");
      out.write("                padding: 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .login-form {\n");
      out.write("                background: white;\n");
      out.write("                padding: 30px;\n");
      out.write("                border-radius: 8px;\n");
      out.write("                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n");
      out.write("                width: 100%;\n");
      out.write("                max-width: 400px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-group {\n");
      out.write("                margin-bottom: 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-group label {\n");
      out.write("                display: block;\n");
      out.write("                margin-bottom: 8px;\n");
      out.write("                font-weight: 500;\n");
      out.write("                color: #333;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-group input {\n");
      out.write("                width: 100%;\n");
      out.write("                padding: 10px;\n");
      out.write("                border: 1px solid #ddd;\n");
      out.write("                border-radius: 4px;\n");
      out.write("                font-size: 16px;\n");
      out.write("                transition: border-color 0.3s;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-group input:focus {\n");
      out.write("                border-color: #4CAF50;\n");
      out.write("                outline: none;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .submit-btn {\n");
      out.write("                background-color: #4CAF50;\n");
      out.write("                color: white;\n");
      out.write("                padding: 12px 20px;\n");
      out.write("                border: none;\n");
      out.write("                border-radius: 4px;\n");
      out.write("                cursor: pointer;\n");
      out.write("                width: 100%;\n");
      out.write("                font-size: 16px;\n");
      out.write("                transition: background-color 0.3s;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .submit-btn:hover {\n");
      out.write("                background-color: #45a049;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-title {\n");
      out.write("                text-align: center;\n");
      out.write("                margin-bottom: 30px;\n");
      out.write("                color: #333;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<style>\n");
      out.write("    *{\n");
      out.write("        margin: 0;\n");
      out.write("        padding: 0;\n");
      out.write("        box-sizing: border-box;\n");
      out.write("        font-family: 'Poppins', sans-serif;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Header Styling */\n");
      out.write("    .header {\n");
      out.write("        background: linear-gradient(135deg, #1a1a2e, #16213e);\n");
      out.write("        padding: 1.2rem 0;\n");
      out.write("        width: 100%;\n");
      out.write("        top: 0;\n");
      out.write("        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .container {\n");
      out.write("        max-width: 1200px;\n");
      out.write("        margin: 0 auto;\n");
      out.write("        padding: 0 1.5rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .nav {\n");
      out.write("        display: flex;\n");
      out.write("        justify-content: space-between;\n");
      out.write("        align-items: center;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Logo */\n");
      out.write("    .logo {\n");
      out.write("        color: #f1c40f;\n");
      out.write("        font-size: 1.8rem;\n");
      out.write("        font-weight: bold;\n");
      out.write("        text-decoration: none;\n");
      out.write("        letter-spacing: 1px;\n");
      out.write("        transition: transform 0.3s;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .logo:hover {\n");
      out.write("        transform: scale(1.1);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Navigation Menu */\n");
      out.write("    .menu {\n");
      out.write("        display: flex;\n");
      out.write("        list-style: none;\n");
      out.write("        gap: 2.5rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu-item a {\n");
      out.write("        color: #ecf0f1;\n");
      out.write("        text-decoration: none;\n");
      out.write("        font-size: 1rem;\n");
      out.write("        transition: color 0.3s ease-in-out;\n");
      out.write("        position: relative;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu-item a::after {\n");
      out.write("        content: '';\n");
      out.write("        position: absolute;\n");
      out.write("        left: 0;\n");
      out.write("        bottom: -3px;\n");
      out.write("        width: 0;\n");
      out.write("        height: 2px;\n");
      out.write("        background: #f1c40f;\n");
      out.write("        transition: width 0.3s ease-in-out;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu-item a:hover::after {\n");
      out.write("        width: 100%;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .menu-item a:hover {\n");
      out.write("        color: #f1c40f;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Search Bar */\n");
      out.write("    .search-bar {\n");
      out.write("        display: flex;\n");
      out.write("        align-items: center;\n");
      out.write("        background: #fff;\n");
      out.write("        border-radius: 25px;\n");
      out.write("        padding: 0.6rem 1rem;\n");
      out.write("        box-shadow: 0 2px 10px rgba(255, 255, 255, 0.1);\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    .search-bar:hover {\n");
      out.write("        box-shadow: 0 2px 15px rgba(255, 255, 255, 0.2);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .search-input {\n");
      out.write("        border: none;\n");
      out.write("        outline: none;\n");
      out.write("        padding: 0.3rem;\n");
      out.write("        width: 220px;\n");
      out.write("        font-size: 0.9rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .search-button {\n");
      out.write("        background: none;\n");
      out.write("        border: none;\n");
      out.write("        cursor: pointer;\n");
      out.write("        color: #16213e;\n");
      out.write("        font-size: 1.2rem;\n");
      out.write("        transition: color 0.3s;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .search-button:hover {\n");
      out.write("        color: #f1c40f;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* User Section */\n");
      out.write("    .user-section {\n");
      out.write("        display: flex;\n");
      out.write("        align-items: center;\n");
      out.write("        gap: 1.2rem;\n");
      out.write("        margin-left: 2rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .welcome-text {\n");
      out.write("        color: #ecf0f1;\n");
      out.write("        font-size: 1rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .user-name {\n");
      out.write("        color: #f1c40f;\n");
      out.write("        font-weight: bold;\n");
      out.write("        font-size: 1.1rem;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .logout-btn {\n");
      out.write("        background: linear-gradient(135deg, #e74c3c, #c0392b);\n");
      out.write("        color: white;\n");
      out.write("        border: none;\n");
      out.write("        border-radius: 5px;\n");
      out.write("        padding: 0.5rem 1rem;\n");
      out.write("        font-size: 0.9rem;\n");
      out.write("        cursor: pointer;\n");
      out.write("        transition: background 0.3s, transform 0.2s;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .logout-btn:hover {\n");
      out.write("        background: #e74c3c;\n");
      out.write("        transform: translateY(-2px);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Right Section */\n");
      out.write("    .right-section {\n");
      out.write("        display: flex;\n");
      out.write("        align-items: center;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<header class=\"header\">\n");
      out.write("    ");


    
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <nav class=\"nav\">\n");
      out.write("            <a href=\"#\" class=\"logo\">PROJECT</a>\n");
      out.write("            <ul class=\"menu\">\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Home</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">|</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Project</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">|</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Manager</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">|</a></li>\n");
      out.write("                <li class=\"menu-item\"><a href=\"#\">Contact</a></li>\n");
      out.write("            </ul>\n");
      out.write("            <div class=\"right-section\">\n");
      out.write("                <div class=\"search-bar\">\n");
      out.write("                    <input type=\"text\" class=\"search-input\" placeholder=\"Tìm kiếm...\">\n");
      out.write("                    <button class=\"search-button\">🔍</button>\n");
      out.write("                </div>\n");
      out.write("                ");
  if (AuthUtils.isLoggedIn(session)) {
                        UserDTO userHeader = AuthUtils.getUser(session);

                
      out.write("\n");
      out.write("                <div class=\"user-section\">\n");
      out.write("                    <span class=\"welcome-text\">Welcome, <span class=\"user-name\">");
      out.print(userHeader.getName());
      out.write("</span>!</span>\n");
      out.write("                    <form action=\"MainController\" method=\"post\" style=\"margin: 0;\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"logout\"/>\n");
      out.write("                        <input type=\"submit\" value=\"Đăng xuất\" class=\"logout-btn\"/>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("    </div>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("        <div class=\"login-container\">\n");
      out.write("            <div class=\"login-form\">\n");
      out.write("                <h2 class=\"form-title\">Đăng nhập</h2>\n");
      out.write("                <form action=\"MainController\" method=\"post\">\n");
      out.write("                    <input type=\"hidden\" name=\"action\" value=\"login\" />\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"Username\">Tên đăng nhập</label>\n");
      out.write("                        <input type=\"text\" id=\"Username\" name=\"txtUsername\" required />\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"form-group\">\n");
      out.write("                        <label for=\"Password\">Mật khẩu</label>\n");
      out.write("                        <input type=\"password\" id=\"Password\" name=\"txtPassword\" required />\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <button type=\"submit\" class=\"submit-btn\">Đăng nhập</button>\n");
      out.write("                    \n");
      out.write("                    ");

                        String message = request.getAttribute("message")+"";
                    
      out.write("\n");
      out.write("                    ");
      out.print(message.equals("null")?"":message);
      out.write("\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
