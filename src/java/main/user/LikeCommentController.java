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
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "LikeCommentController", urlPatterns = {"/likecomment"})
public class LikeCommentController extends HttpServlet {
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            int commentID = Integer.parseInt(request.getParameter("commentID"));

            try {
                boolean hasLiked = CommentLikeDAO.INS.hasUserLikedComment(commentID, user.getId());
                boolean success;
                if (hasLiked) {
                    success = CommentLikeDAO.INS.removeLike(commentID, user.getId());
                } else {
                    success = CommentLikeDAO.INS.addLike(commentID, user.getId());
                }

                if (success) {
                    int likeCount = CommentLikeDAO.INS.getLikeCount(commentID);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"likeCount\": " + likeCount + "}");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
