/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package main.user;

import entity.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebServlet(name="LikeBlogController", urlPatterns={"/likeblog"})
public class LikeBlogController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/likeblog")) {
            handleLikeBlog(request, response);
        } else if (path.equals("/checkLikeStatus")) {
            handleCheckLikeStatus(request, response);
        }
    }

    private void handleLikeBlog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            int blogID = Integer.parseInt(request.getParameter("blogID"));

            try {
                boolean hasLiked = BlogLikeDAO.INS.hasUserLikedBlog(blogID, user.getId());
                boolean success;
                if (hasLiked) {
                    success = BlogLikeDAO.INS.removeLike(blogID, user.getId());
                } else {
                    success = BlogLikeDAO.INS.addLike(blogID, user.getId());
                }

                if (success) {
                    int likeCount = BlogLikeDAO.INS.getLikeCount(blogID);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"likeCount\": " + likeCount + ", \"hasLiked\": " + !hasLiked + "}");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCheckLikeStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            int blogID = Integer.parseInt(request.getParameter("blogID"));

            try {
                boolean hasLiked = BlogLikeDAO.INS.hasUserLikedBlog(blogID, user.getId());
                response.setContentType("application/json");
                response.getWriter().write("{\"hasLiked\": " + hasLiked + "}");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
