/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.*;
import entity.*;
import java.io.IOException;
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
@WebServlet(name = "RemoveBlogController", urlPatterns = {"/remove-blog"})
public class RemoveBlogController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            int blogID = Integer.parseInt(request.getParameter("id"));
            
            //Remove entirely
            
//            String userID = request.getParameter("userID");
//            List<Comment> commentList = CommentDAO.INS.getCommentsByBlogID(blogID);
//            List<Reply> replyList = new ArrayList<Reply>();
//
//            BlogImageDAO.INS.removeBlogImageByBlogID(blogID);
//
//            for (Comment comment : commentList) {
//                List<Reply> replyListInComment = ReplyDAO.INS.getRepliesByCommentID(comment.getCommentID());
//                for (Reply reply : replyListInComment) {
//                    replyList.add(reply);
//                }
//            }
//
//            for (Reply reply : replyList) {
//                ReplyLikeDAO.INS.removeLikeByReplyID(reply.getReplyID());
//                ReplyDAO.INS.deleteReply(reply.getReplyID());
//            }
//
//            for (Comment comment : commentList) {
//                CommentLikeDAO.INS.removeLikeByCommentID(comment.getCommentID());
//                CommentDAO.INS.deleteComment(comment.getCommentID());
//            }
//            
//            BlogInfoDAO.INS.removeType(blogID, "meal");
//            BlogInfoDAO.INS.removeType(blogID, "food");
//            BlogInfoDAO.INS.removeType(blogID, "special");
//            BlogInfoDAO.INS.removeType(blogID, "price");
//            BlogInfoDAO.INS.removeType(blogID, "hashtag");
//            boolean result = BlogDAO.INS.removeBlog(blogID);


             //Set the blog to Removed Status
            boolean result = BlogDAO.INS.setBlogToRemoveStatus(blogID);
            if (result) {
                request.setAttribute("notify", Notification.builder().type("success").title("Remove blog").message("Successfully").build());
            } else {
                request.setAttribute("notify", Notification.builder().type("error").title("Remove blog").message("Failed").build());
            }
            request.getRequestDispatcher("showblog").forward(request, response);
            return;
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

}
