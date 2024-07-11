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
@WebServlet(name = "CreateReplyController", urlPatterns = {"/createreply"})
public class CreateReplyController extends HttpServlet {
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            String userID = user.getId();
            int blogID = 0;
            int commentID = 0;
            try {
                blogID = Integer.parseInt(request.getParameter("blogID"));
                commentID = Integer.parseInt(request.getParameter("commentID"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("errorblog.jsp");
                return;
            }
            boolean isReplyCreated = ReplyDAO.INS.createReply(content, userID, commentID);
            if (isReplyCreated) {
                Reply newReply = ReplyDAO.INS.getLatestReplyByUserAndComment(userID, commentID);
                if (newReply != null) {
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<hr>");
                        out.println("<div class=\"media-block\" id=\"reply-" + newReply.getReplyID() + "\">");
                        out.println("<a class=\"media-left\" href=\"#\"><img class=\"img-circle img-sm\" alt=\"Profile Picture\" src=\"data:image/*;base64," + newReply.getUserPicture() + "\"></a>");
                        out.println("<div class=\"media-body\">");
                        out.println("<div class=\"mar-btm\">");
                        out.println("<a href=\"showblog?ID=" + newReply.getUserID() + "\" class=\"btn-link text-semibold media-heading box-inline\">" + newReply.getUserName() + "</a>");
                        out.println("<p class=\"text-muted text-sm\">" + newReply.getReplyDate() + "</p>");
                        out.println("</div>");
                        out.println("<p>" + newReply.getContent() + "</p>");
                        out.println("<div class=\"pad-ver\">");
                        out.println("<span class=\"tag tag-sm\" id=\"like-count-" + newReply.getReplyID() + "\">");
                        out.println("<i class=\"fa fa-heart text-danger\"></i> " + newReply.getReplyLike() + " Lượt thích");
                        out.println("</span>");
                        out.println("<div class=\"btn-group\">");
                        out.println("<a class=\"btn btn-sm btn-default btn-hover-success\" href=\"#\" onclick=\"likeReply(event, " + newReply.getReplyID() + ")\">");
                        out.println("<i class=\"fa fa-thumbs-up\"></i>");
                        out.println("</a>");
                        out.println("</div>");
                        if (userID.equals(newReply.getUserID())) {
                            out.println("<a class=\"btn btn-sm btn-default btn-hover-primary\" href=\"#\" onclick=\"deleteReply(event, " + newReply.getReplyID() + ", " + commentID + ", " + blogID + ")\">");
                            out.println("<i class=\"fa fa-trash-o\" style=\"font-size:12px; color:red;\"></i>");
                            out.println("</a>");
                        }
                        out.println("</div>");
                        out.println("<hr>");
                        out.println("</div>");
                        out.println("</div>");
                    }
                } 
            } 
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

}
