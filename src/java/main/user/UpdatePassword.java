/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.Service;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdatePassword", urlPatterns = {"/update-password"})
public class UpdatePassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("update-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = "";
        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        User user = new User();
        if (session.getAttribute("currentUser") != null) {
            user = (User) session.getAttribute("currentUser");
            email = user.getEmail();
        } else {
            request.setAttribute("mess", "Hãy đăng nhập trước!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if (user != null) {
            if (Service.getMd5(oldPass).equals(user.getPasssword())) {
                if (!newPass.isEmpty() && !confirmPass.isEmpty()) {
                    if (!Service.isValidatedPassword(newPass)) {
                        request.setAttribute("mess", "Mật khẩu quá yếu! Phải bắt đầu bằng chữ hoa và chứa cả số và chữ.");
                        request.getRequestDispatcher("update-password.jsp").forward(request, response);
                    } else if (!newPass.equals(confirmPass)) {
                        request.setAttribute("mess", "Xác nhận mật khẩu sai!");
                        request.getRequestDispatcher("update-password.jsp").forward(request, response);
                    } else if (Service.getMd5(newPass).equals(user.getPasssword())) {
                        request.setAttribute("mess", "Mật khẩu không thay đổi!");
                        request.getRequestDispatcher("update-password.jsp").forward(request, response);
                    } else {
                        UserDAO.INS.updatePassword(newPass, user.getEmail());
                        request.setAttribute("mess", "Thay đổi mật khẩu thành công!");
                        request.getRequestDispatcher("update-password.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("mess", "Hãy điền đủ các trường!");
                    request.getRequestDispatcher("update-password.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Mật khẩu cũ không đúng!");
                request.getRequestDispatcher("update-password.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Email không tồn tại!");
            request.getRequestDispatcher("update-password.jsp").forward(request, response);
        }
    }
}
