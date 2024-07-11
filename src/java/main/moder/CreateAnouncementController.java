/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.moder;

import dao.GlobalMessageDAO;
//import entity.Notification;
import entity.GlobalMessage;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh
 */
@WebServlet(name = "CreateAnouncementController", urlPatterns = {"/moder/create-anouncement"})
public class CreateAnouncementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy userId từ session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            // Redirect to login page or handle as appropriate
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        // Lấy danh sách thông báo của người dùng cụ thể
        List<GlobalMessage> notifications = GlobalMessageDAO.INS.getNotificationsByUserId(user.getId());
        GlobalMessage latestNotification = GlobalMessageDAO.INS.getLatestNotificationByUserId(user.getId());

        // Đặt danh sách thông báo vào thuộc tính của request
        request.setAttribute("notifications", notifications);
        request.setAttribute("latestNotification", latestNotification);
        String message = (String) request.getAttribute("successMessage");
        if (message != null) {
            request.setAttribute("successMessage", message);
        }
        // Chuyển hướng đến trang JSP để hiển thị danh sách thông báo
        request.getRequestDispatcher("createNotification.jsp").forward(request, response);
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

        // Retrieve current user from session
        User user = (User) session.getAttribute("currentUser");

        // Check if user is logged in
        if (user == null) {
            // Redirect to login page or handle as appropriate
            response.sendRedirect("login.jsp");
            return;
        }
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        String type = request.getParameter("type");
        String isShowParam = request.getParameter("is_show");
        boolean isShow = "true".equalsIgnoreCase(isShowParam) || "1".equals(isShowParam);
        if (title == null || title.trim().isEmpty()
                || message == null || message.trim().isEmpty()
                || type == null || type.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            List<GlobalMessage> notifications = GlobalMessageDAO.INS.getNotificationsByUserId(user.getId());
            request.setAttribute("notifications", notifications);
            request.getRequestDispatcher("createNotification.jsp").forward(request, response);
            return;
        }
        // Get current shown notification and update its is_show to 0
        GlobalMessage latestNotification = GlobalMessageDAO.INS.getLatestNotificationByUserId(user.getId());
        if (latestNotification != null) {
            GlobalMessageDAO.INS.updateIsShow(latestNotification.getId(), false);
        }
        // Thêm thông báo vào cơ sở dữ liệu bằng cách gọi phương thức insertNotification từ DAO
        GlobalMessageDAO.INS.insertNotification(title, message, type, isShow, user.getId());
        List<GlobalMessage> notifications = GlobalMessageDAO.INS.getNotificationsByUserId(user.getId());
        GlobalMessage newlatestNotification = GlobalMessageDAO.INS.getLatestNotificationByUserId(user.getId());
        request.setAttribute("notificationTitle", title);
        request.setAttribute("notificationMessage", message);
        request.setAttribute("notificationType", type);
        request.setAttribute("notificationShow", isShow);
        request.setAttribute("notifications", notifications);
        request.setAttribute("latestNotification", newlatestNotification);
         response.sendRedirect(request.getContextPath() + "/moder/create-anouncement?successMessage=Notification+created+successfully");
    }

}
