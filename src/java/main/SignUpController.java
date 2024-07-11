/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import consts.IConstants;
import dao.UserDAO;
import dao.UserSettingDAO;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.*;

/**
 *
 * @author ADMIN
 */
public class SignUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        SendEmail sm = new SendEmail();
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

//        User userByEmail = userDao.findUserByEmail(email);
//        User userByUsername = userDao.findUserByUsername(username);
        if (email.isBlank() || username.isBlank() || password.isBlank()) {
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("signUpMess", "Hãy điền tất cả các trường!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!Service.isValidatedPassword(password)) {
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("signUpMess", "Mật khẩu quá yếu! Phải bắt đầu bằng chữ hoa và chứa cả số và chữ.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (!password.equals(confirmPassword)) {
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("signUpMess", "Xác nhận mật khẩu sai!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (UserDAO.INS.checkEmailExist(email) && !UserDAO.INS.findUserByEmail(email).getUsername().equals(username)) {//chỉ trùng email
//            if (UserDAO.INS.findUserByEmail(email).getStatus() == 0) {//chỉ trùng email và chưa verified
//                UserDAO.INS.updateAtEmail(username, password, email);
//                sm.sendEmail(email, UserDAO.INS.findUserByEmail(email).getVerifyCode());
//                session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(email));
//                session.setAttribute("isSignUp", true);
//                request.getRequestDispatcher("otp-confirmation.jsp").forward(request, response);
//            } else {//chỉ trùng email nhưng đã verified
                request.setAttribute("username", username);
                request.setAttribute("signUpMess", "Email đã tồn tại!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
        } else if (UserDAO.INS.checkUsernameExist(username) && UserDAO.INS.findUserByUsername(username).getEmail().compareTo(email) != 0) {//chỉ trùng username
//            if (UserDAO.INS.findUserByUsername(username).getStatus() == 0) {//chỉ trùng username và chưa verified
//                UserDAO.INS.updateAtUsername(username, password, email);
//                sm.sendEmail(UserDAO.INS.findUserByUsername(username).getEmail(), UserDAO.INS.findUserByUsername(username).getVerifyCode());
//                session.setAttribute("currentUser", UserDAO.INS.findUserByUsername(username));
//                session.setAttribute("isSignUp", true);
//                request.getRequestDispatcher("otp-confirmation.jsp").forward(request, response);
//            } else {//chỉ trùng username những đã verified
                request.setAttribute("email", email);
                request.setAttribute("signUpMess", "Tên đăng nhập đã tồn tại!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
        } else if (UserDAO.INS.checkEmailExist(email) && UserDAO.INS.findUserByEmail(email).getUsername().equals(username)) {//trùng cả email và username
//            if (UserDAO.INS.findUserByUsername(username).getStatus() == 0) {//trùng cả 2 và chưa verified
//                UserDAO.INS.updateAtUsername(username, password, email);
//                sm.sendEmail(email, UserDAO.INS.findUserByEmail(email).getVerifyCode());
//                session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(email));
//                session.setAttribute("isSignUp", true);
//                request.getRequestDispatcher("otp-confirmation.jsp").forward(request, response);
//            } else {//trùng cả 2 những đã verified
                request.setAttribute("signUpMess", "Tài khoản đã tồn tại!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
        } else {//chả trùng cái gì
            ServletContext context = getServletContext();
            String relativePath = IConstants.IMAGE_USER_DEFAULT_PATH;
            String fullPath = context.getRealPath(relativePath);

            UserDAO.INS.signUp(username, password, fullPath, email, Service.genRandSixDigit(), Service.getNowAfter10Minutes());
            UserSettingDAO.INS.makeNewUserSetting(UserDAO.INS.findUserByEmail(email).getId());
            boolean isSend = sm.sendEmail(email, UserDAO.INS.findUserByEmail(email).getVerifyCode());

            if (isSend) {
                session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(email));
                session.setAttribute("isSignUp", true);
                response.sendRedirect("otp-confirmation");
//                UserSettingDAO.INS.makeNewUserSetting(Service.extractUsername(email));
            } else {
                request.setAttribute("mess", "Invalid email!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
//        if (UserDAO.INS.checkUsernameExist(username)) {
//            request.setAttribute("mess", "Username exitsed!");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//
//        if (!password.equals(confirmPassword)) {
//            request.setAttribute("mess", "Passwords didn't match!");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }

//        ServletContext context = getServletContext();
//        String relativePath = IConstants.IMAGE_USER_DEFAULT_PATH;
//        String fullPath = context.getRealPath(relativePath);
//
//        UserDAO.INS.signUp(username, password, fullPath, email, Service.genRandSixDigit(), Service.getNowAfter10Minutes());
//        boolean isSend = sm.sendEmail(email, UserDAO.INS.findUserByEmail(email).getVerifyCode());
//
//        if (isSend) {
//            session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(email));
//
//            response.sendRedirect("otp-confirmation");
//        } else {
//            request.setAttribute("mess", "Invalid email!");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
    }

    public static void main(String[] args) {
        String email1 = "bansonvtpt@gmail.com";
        String email2 = "bansonvtpt@gmail.com";
        System.out.println(Service.isValidatedPassword("1"));
    }
}
