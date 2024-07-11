/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.UserDAO;
import service.*;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class ForgotPasswordController extends HttpServlet {

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
            out.println("<title>Servlet ForgotPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        SendEmail sm = new SendEmail();

        String email = request.getParameter("email");
        String stepOne = request.getParameter("stepOne");
        String stepTwo = request.getParameter("stepTwo");
        String password = "";
        String confirmPassword = "";
        if (request.getParameter("password") != null || request.getParameter("confirmPassword") != null) {
            password = request.getParameter("password");
            confirmPassword = request.getParameter("confirmPassword");
        }

        User user = new User();
        if (session.getAttribute("currentUser") != null) {
            user = (User) session.getAttribute("currentUser");
            email = user.getEmail();
        } else {
            user = UserDAO.INS.findUserByEmail(email);
        }

        if (user != null) {
            if (email != null && stepOne != null) { 

                UserDAO.INS.updateVerifyCode(Service.genRandSixDigit(), Service.getNowAfter10Minutes(), email);
                boolean isSend = sm.sendEmail(email, UserDAO.INS.findUserByEmail(email).getVerifyCode());
                if (isSend) {
                    session.setAttribute("currentUser", UserDAO.INS.findUserByEmail(email));
                    session.setAttribute("isForgot", true);
                    response.sendRedirect("otp-confirmation");
                } else {
                    request.setAttribute("mess", "Invalid email!");
                    request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                }

            } else if (!password.isBlank() && !confirmPassword.isBlank() && stepTwo != null) {

                if (!Service.isValidatedPassword(password)) {
                    request.setAttribute("isReset", true);
                    request.setAttribute("mess", "Password doesn't match requirements! Must start with an uppercase and contain both word and number");
                    request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                } else if (!password.equals(confirmPassword)) {
                    request.setAttribute("isReset", true);
                    request.setAttribute("mess", "Passwords didn't match!");
                    request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                } else if (Service.getMd5(password).equals(user.getPasssword())) {
                    request.setAttribute("isReset", true);
                    request.setAttribute("mess", "Password didn't change!");
                    request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                } else {
                    UserDAO.INS.updatePassword(password, email);
                    session.removeAttribute("currentUser");
                    request.setAttribute("loginMess", "Reset password succesfully!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
               
            } else {
                
                request.setAttribute("isReset", true);
                request.setAttribute("mess", "Please fill out all field!");
                request.getRequestDispatcher("forgot-password.jsp").forward(request, response);

            }
            
        } else {
            request.setAttribute("mess", "This email is not signed up!");
            request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
