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
import java.util.List;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CreateCommentController", urlPatterns = {"/createcomment"})
public class CreateCommentController extends HttpServlet {
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
            String userID = user.getId();
            int blogid = 0;
            try {
                blogid = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("errorblog.jsp");
                return;
            }

            boolean isCommentCreated = CommentDAO.INS.createComment(content, userID, blogid);

            if (isCommentCreated) {
                Comment newComment = CommentDAO.INS.getLatestCommentByUserAndBlog(userID, blogid);
                if (newComment != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<div class=\"media-block\" id=\"comment-" + newComment.getCommentID() + "\">");
                        out.println("<a class=\"media-left\" href=\"#\"><img class=\"img-circle img-sm\" alt=\"Profile Picture\" src=\"" + newComment.getUserPicture() + "\"></a>");
                        out.println("<div class=\"media-body\">");
                        out.println("<div class=\"mar-btm\">");
                        out.println("<a href=\"#\" class=\"btn-link text-semibold media-heading box-inline\">" + newComment.getUserName() + "</a>");
                        out.println("<p class=\"text-muted text-sm\"> - Commented Date: " + newComment.getCommentDate() + "</p>");
                        out.println("</div>");
                        out.println("<p>" + newComment.getContent() + "</p>");
                        out.println("<div class=\"pad-ver\">");
                        out.println("<span class=\"tag tag-sm\" id=\"like-count-" + newComment.getCommentID() + "\">");
                        out.println("<i class=\"fa fa-heart text-danger\"></i> " + newComment.getCommentLike() + " Lượt thích");
                        out.println("</span>");
                        out.println("<div class=\"btn-group\">");
                        out.println("<a class=\"btn btn-sm btn-default btn-hover-success\" href=\"#\"><i class=\"fa fa-thumbs-up\"></i></a>");
                        out.println("</div>");
                        if (session.getAttribute("currentUser") != null && ((User) session.getAttribute("currentUser")).getId().equals(newComment.getUserID())) {
                            out.println("<a class=\"btn btn-sm btn-default btn-hover-primary\" href=\"#\" onclick=\"deleteComment(event, " + newComment.getCommentID() + ", " + blogid + ")\">Delete</a>");
                        }
                        out.println("</div>");
                        out.println("<hr>");
                        out.println("</div>");
                        out.println("</div>");
                    }
                } 
            } 
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
