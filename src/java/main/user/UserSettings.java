/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package main.user;

import dao.FollowDAO;
import dao.NotificationDAO;
import dao.UserDAO;
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
 * @author ADMIN
 */
@WebServlet(name="UserSettings", urlPatterns={"/settings"})
public class UserSettings extends HttpServlet {
   



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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

        User user = UserDAO.INS.findUserById((userId));

        ArrayList<User> listFollower = UserDAO.INS.getListOfFollow(userId, "follower");
        ArrayList<User> listFollowing = UserDAO.INS.getListOfFollow(userId, "following");
        

        boolean isOwner = sessionUser.getId().equals(userId);
        boolean isFollowing = FollowDAO.INS.isFollowing(sessionUser.getId(), userId);
        //Count the follower - following of that user (not the current account)
        int follower = FollowDAO.INS.countFollow(userId, "follower");
        int following = FollowDAO.INS.countFollow(userId, "following");

        List<Notification> listNotif = NotificationDAO.INS.getNotificationByUserId(user.getId());
        request.setAttribute("listNotif", listNotif);

        request.setAttribute("user", user);
        request.setAttribute("isOwner", isOwner);
        request.setAttribute("isFollowing", isFollowing);
        request.setAttribute("following", following);
        request.setAttribute("follower", follower);
        request.setAttribute("followerList", listFollower);
        request.setAttribute("followingList", listFollowing);
        request.getRequestDispatcher("user-settings.jsp").forward(request, response);
    } 

    
}
