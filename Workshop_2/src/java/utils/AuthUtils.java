/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.UserDAO;
import dto.UserDTO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mah
 */
//'Instructor', 'Student'
public class AuthUtils {

    private static final String INSTRUCTOR_ROLE = "Instructor";
    private static final String STU_ROLE = "Student";

    public static UserDTO getUser(String strUserID) {
        UserDAO udao = new UserDAO();
        UserDTO user = udao.readByID(strUserID);
        return user;
    }

    public static boolean isValidLogin(String strUsername, String strPassword) {
        UserDTO user = getUser(strUsername);
        System.out.println(user);
        System.out.println(strPassword);
        return user != null && user.getPassword().equals(strPassword);
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    public static UserDTO getUser(HttpSession session) {
        if (!isLoggedIn(session)) {
            return null;
        }
        return (UserDTO) session.getAttribute("user");
    }

    public static boolean isInstructor(HttpSession session) {
        if (!isLoggedIn(session)) {
            return false;
        }
        UserDTO user = (UserDTO) session.getAttribute("user");
        return user.getRole().equals(INSTRUCTOR_ROLE);
    }

    public static boolean isStudent(HttpSession session) {
        if (isLoggedIn(session) == false) {
            return false;
        }
        UserDTO user = (UserDTO) session.getAttribute("user");
        return user.getRole().equals(STU_ROLE);
    }
}
