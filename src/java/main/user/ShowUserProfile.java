/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.BlogDAO;
import dao.BlogImageDAO;
import dao.FollowDAO;
import dao.NotificationDAO;
import dao.UserDAO;
import entity.Blog;
import entity.BlogImage;
import entity.Notification;
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
 * @author legion
 */
@WebServlet(name = "ShowUserProfile", urlPatterns = {"/showblog"})
public class ShowUserProfile extends HttpServlet {

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
        try {

            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            User sessionUser = (User) session.getAttribute("currentUser");

            //may delete
            if (sessionUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            //

            String userId = request.getParameter("ID");

            //To check other user profile
            if (userId == null) {
                userId = sessionUser.getId();
            }
            String action=request.getParameter("action");
            User user = UserDAO.INS.findUserById((userId));
            ArrayList<BlogImage> listImage = BlogImageDAO.INS.getFirstImageOfAllBlog();
            ArrayList<Blog> list = BlogDAO.INS.getListOfBlogByUserID(userId,action);
            List<Blog> bookmarks = BlogDAO.INS.getBookmarksByUserID(userId);
            ArrayList<Notification> listNotification = NotificationDAO.INS.getNLastDayNotificationByUserId(2, userId);
            ArrayList<User> listFollower = UserDAO.INS.getListOfFollow(userId, "follower");
            ArrayList<User> listFollowing = UserDAO.INS.getListOfFollow(userId, "following");
            
             List<String> allReportReasons = UserDAO.INS.getAllReportReasons();
              request.setAttribute("listUserReportReason", allReportReasons);
            

            boolean isOwner = sessionUser.getId().equals(userId);
            boolean isFollowing = FollowDAO.INS.isFollowing(sessionUser.getId(), userId);
            
            //Count the follower - following of that user (not the current account)
            int follower = FollowDAO.INS.countFollow(userId, "follower");
            int following = FollowDAO.INS.countFollow(userId, "following");

            //The notification tabs above header

            request.setAttribute("listAllNotif", NotificationDAO.INS.getAllNotificationByUserId(userId));

            request.setAttribute("listImage", listImage);
            request.setAttribute("user", user);
            request.setAttribute("listBlog", list);
            request.setAttribute("blogCount", list.size());
            request.setAttribute("bookmarks", bookmarks);
            request.setAttribute("listNotif", listNotification);
            
            request.setAttribute("isOwner", isOwner);
            request.setAttribute("isFollowing", isFollowing);
            request.setAttribute("following", following);
            request.setAttribute("follower", follower);
            request.setAttribute("followerList", listFollower);
            request.setAttribute("followingList", listFollowing);
            
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            
        } catch (Exception e) {
            response.sendRedirect("errorpage.jsp");
            return;
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    public static void main(String[] args) {
        System.out.println(Integer.parseInt(null));
    }
}
