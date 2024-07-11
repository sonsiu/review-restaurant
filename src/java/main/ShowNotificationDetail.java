/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.BlogDAO;
import dao.BlogImageDAO;
import dao.BlogInfoDAO;
import dao.BlogLikeDAO;
import dao.CommentDAO;
import dao.FollowDAO;
import dao.NotificationDAO;
import dao.ReplyDAO;
import dao.UserDAO;
import entity.Blog;
import entity.BlogImage;
import entity.BlogInfo;
import entity.Comment;
import entity.Notification;
import entity.Reply;
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
 * @author ADMIN
 */
@WebServlet(name = "ShowNotificationDetail", urlPatterns = {"/notification-detail"})
public class ShowNotificationDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int notifId = Integer.parseInt(request.getParameter("id"));

            User currentUser = (User) session.getAttribute("currentUser");

            if (NotificationDAO.INS.getNotificationById(notifId) == null) {
                response.sendRedirect("errorblog.jsp");
                return;
            }

            int following = FollowDAO.INS.countFollow(currentUser.getId(), "following");
            int follower = FollowDAO.INS.countFollow(currentUser.getId(), "follower");
            int totalBlogs = BlogDAO.INS.countBlog(currentUser.getId());

            List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, currentUser.getId());
            
            request.setAttribute("listNotif", listNotif);
            request.setAttribute("currentUser", currentUser);
            request.setAttribute("notification", NotificationDAO.INS.getNotificationById(notifId));

            request.setAttribute("following", following);
            request.setAttribute("follower", follower);
            request.setAttribute("totalBlogs", totalBlogs);

            request.getRequestDispatcher("notification-detail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("errorblog.jsp");
        }
    }
}
