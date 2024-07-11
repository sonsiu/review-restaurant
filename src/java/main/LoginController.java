/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import consts.IConstants;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.User;
import dao.UserDAO;
import dao.UserSettingDAO;
import entity.UserGoogle;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import service.SendEmail;
import service.Service;

/**
 *
 * @author ADMIN
 */
public class LoginController extends HttpServlet {

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
        Cookie cookieArray[] = request.getCookies();
        HttpSession session = request.getSession();
        ArrayList<User> userList = UserDAO.INS.getAll();

//        if (cookieArray != null) {
//            for (Cookie o : cookieArray) {
//                if (o.getName().equals("cookieUsername")) {
//                    request.setAttribute("cookieUsername", o.getValue());
//                }
//                if (o.getName().equals("cookiePassword")) {
//                    request.setAttribute("cookiePassword", o.getValue());
//                }
//            }
//        }
        if (request.getParameter("code") != null) {
            String code = request.getParameter("code");
            String accessToken = Service.getToken(code);
            UserGoogle userGoogle = Service.getUserInfo(accessToken);

            System.out.println(userGoogle);

            boolean foundMatch = false;

            for (User u : userList) {
                if (userGoogle.getEmail().equals(u.getEmail())) {

                    User user = UserDAO.INS.findUserByEmail(userGoogle.getEmail());
                    session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(userGoogle.getEmail()));

                    if (user != null) {
                        foundMatch = true;
                        if (user.getStatus() == 0) { //unverified user
                            session.setAttribute("currentUser", user);
                            request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
                            request.setAttribute("mess", "Tài khoản này chưa được xác thực, hãy đăng ký lại!");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                            return;

                        } else if (user.getStatus() == 1 || user.getStatus() == 3) {
                            session.setAttribute("currentUser", user);
                            session.setMaxInactiveInterval(600);

//                            if (user.getRole() == 2) {
//                                response.sendRedirect(request.getContextPath() + "/homepage");
//                            } else if (user.getRole() == 1) {
//                                response.sendRedirect("moder/dashboard");
//                            } else {
//                                response.sendRedirect("admin/dashboard");
//                            }
//                            return;
                            
                            request.getRequestDispatcher("homepage").forward(request, response);
                        } else if (user.getStatus() == 2) {
                            request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
                            request.setAttribute("loginMess", "Tài khoản này đã bị vô hiệu hóa, kiểm tra Gmail để biết thêm chi tiết!");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                            return;
                        }
                    } else {
                        request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
                        request.setAttribute("loginMess", "Tên đăng nhập hoặc mật khẩu không đúng!");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
            }
            if (!foundMatch) {
                ServletContext context = getServletContext();
                String relativePath = IConstants.IMAGE_USER_DEFAULT_PATH;
                String fullPath = context.getRealPath(relativePath);
                UserDAO.INS.signUpByGmail(userGoogle, fullPath);

                session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(userGoogle.getEmail()));
                session.setMaxInactiveInterval(600);

                response.sendRedirect(request.getContextPath() + "/homepage");
            } else {
                request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
            }
        } else {
            request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        User user = UserDAO.INS.authenticate(username, password);
        SendEmail sm = new SendEmail();

        if (user != null) {
            if (user.getStatus() == 0) { //unverified user
                UserDAO.INS.updateVerifyCode(Service.genRandSixDigit(), Service.getNowAfter10Minutes(), user.getEmail());
                sm.sendEmail(user.getEmail(), UserDAO.INS.findUserByEmail(user.getEmail()).getVerifyCode());
                session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(user.getEmail()));
                request.setAttribute("mess", "Tài khoản này chưa được xác thực, hãy nhập lại mã OTP!");
                request.getRequestDispatcher("otp-confirmation.jsp").forward(request, response);
            } else if (user.getStatus() == 1 || user.getStatus() == 3) {
                session.setAttribute("currentUser", user);
                session.setMaxInactiveInterval(600);

                Cookie cookieUsername = new Cookie("cookieUsername", username);
                Cookie cookiePassword = new Cookie("cookiePassword", password);
                cookieUsername.setMaxAge(IConstants.COOKIE_MAX_AGE);
                if (remember != null) {
                    UserSettingDAO.INS.updateRememberMe(true, user.getId());
                    cookiePassword.setMaxAge(IConstants.COOKIE_MAX_AGE);
                    response.addCookie(cookiePassword);
                } else {
                    UserSettingDAO.INS.updateRememberMe(false, user.getId());
                    cookiePassword.setMaxAge(0);
                }
                response.addCookie(cookieUsername);
//                if (user.getRole() == 2) {
//                    response.sendRedirect(request.getContextPath() + "/homepage");
//                } else if (user.getRole() == 1) {
//                    response.sendRedirect("moder/dashboard");
//                } else {
//                    response.sendRedirect("admin/dashboard");
//                }
                session.setAttribute("from", "login");
                request.getRequestDispatcher("homepage").forward(request, response);
            } else if (user.getStatus() == 2) {
                request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
                request.setAttribute("loginMess", "Tài khoản này đã bị vô hiệu hóa, kiểm tra Gmail để biết thêm chi tiết");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
            request.setAttribute("loginMess", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
