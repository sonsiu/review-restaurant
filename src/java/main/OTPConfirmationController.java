/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.SendEmail;
import service.Service;

/**
 *
 * @author ADMIN
 */
public class OTPConfirmationController extends HttpServlet {

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
        HttpSession session = request.getSession();

        if (session.getAttribute("currentUser") != null) {
            request.getRequestDispatcher("otp-confirmation.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
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

        HttpSession session = request.getSession();
        SendEmail sm = new SendEmail();

        if (session.getAttribute("currentUser") != null) {
            User user = (User) session.getAttribute("currentUser");

            String userInputCode = "";
            for (int i = 1; i < 7; i++) {
                userInputCode += request.getParameter("otp" + i);
            }

            if (Service.compareTime(user.getExpDate()) >= 1) {
                String verifyCode = Service.genRandSixDigit();
                UserDAO.INS.updateVerifyCode(verifyCode, Service.getNowAfter10Minutes(), user.getEmail());
                sm.sendEmail(user.getEmail(), verifyCode);

                session.setAttribute("currentUser", user);
                request.setAttribute("mess", "Mã đã hết hạn, mã mới đã được gửi tới bạn.");
                request.getRequestDispatcher("otp-confirmation").forward(request, response);
            }

            if (userInputCode.equals(user.getVerifyCode())) {
                if (session.getAttribute("isSignUp") != null) {
                    UserDAO.INS.updateStatus(1, user.getId());

                    session.removeAttribute("currentUser");
                    session.removeAttribute("isSignUp");
                    request.setAttribute("successMess", "Đăng ký thành công, hãy đăng nhập lại.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else if (session.getAttribute("isForgot") != null) {
                    session.setAttribute("currentUser", user);
                    session.removeAttribute("isForgot");
                    request.setAttribute("isReset", true);
                    request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
                } else if (session.getAttribute("isUpdate") != null) {
                    UserDAO.INS.updatePassword((String)session.getAttribute("newPass"), user.getEmail());
                    session.removeAttribute("isUpdate");
                    session.removeAttribute("newPass");
                    request.setAttribute("mess", "Cập nhật mật khẩu thành công!");
                    request.getRequestDispatcher("update-password.jsp").forward(request, response);
                } else {
                    UserDAO.INS.updateStatus(1, user.getId());
                    request.setAttribute("mess", "Đăng ký thành công, hãy đăng nhập lại.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                session.setAttribute("currentUser", user);

                request.setAttribute("mess", "Sai mã!");
                request.getRequestDispatcher("otp-confirmation.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login");
        }
    }

    public static void main(String[] args) {
        String action = "forget";
        System.out.println(action.equals("forget"));
    }

}
