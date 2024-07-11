/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.*;
import entity.*;
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
import java.sql.SQLException;

/**
 *
 * @author legion
 */
@WebServlet(name = "ShowBlogDetailController", urlPatterns = {"/blog-detail"})
public class ShowBlogDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String idParam = request.getParameter("id");
            int blogID;

            User currentUser = (User) session.getAttribute("currentUser");
            boolean hasLiked = false; // Check if the user has liked the blog
            boolean hasReported = false; // Check if the user has already reported the blog

            blogID = Integer.parseInt(idParam);
            ArrayList<BlogImage> images = BlogImageDAO.INS.getImagesByBlogID(blogID);
            User user = UserDAO.INS.findUserByBlogID(blogID);
            if (!BlogDAO.INS.blogIDExists(blogID)) {
                response.sendRedirect("errorblog.jsp");
                return;
            }
            Blog blog = BlogDAO.INS.getBlogDetailByID(blogID);

            // If blog is not verified or removed or waiting for verification and user is not mod or admin
            if (blog.getStatus() == 0 || blog.getStatus() == 4 || blog.getStatus() == 2) {
                if (currentUser == null || currentUser.getRole() == 2) {
                    response.sendRedirect("errorblog.jsp");
                    return;
                }
            }

            List<Comment> comments = CommentDAO.INS.getCommentsByBlogID(blogID);

            for (Comment comment : comments) {
                List<Reply> replies = ReplyDAO.INS.getRepliesByCommentID(comment.getCommentID());
                comment.setReplies(replies);
            }

            int totalComments = CommentDAO.INS.countCommentsByBlogID(blogID);
            int totalReplies = ReplyDAO.INS.countRepliesByBlogID(blogID);
            blog.setCommentCount(totalComments + totalReplies);

            ArrayList<BlogInfo> mealType = BlogInfoDAO.INS.getType(blogID, "meal");
            ArrayList<BlogInfo> foodType = BlogInfoDAO.INS.getType(blogID, "food");
            ArrayList<BlogInfo> specialDiet = BlogInfoDAO.INS.getType(blogID, "special");
            ArrayList<BlogInfo> priceRange = BlogInfoDAO.INS.getType(blogID, "price");
            ArrayList<BlogInfo> hashTag = BlogInfoDAO.INS.getType(blogID, "hashtag");

            int following = FollowDAO.INS.countFollow(user.getId(), "following");
            int follower = FollowDAO.INS.countFollow(user.getId(), "follower");
            int totalBlogs = BlogDAO.INS.countBlog(user.getId());

            // Check if the current user has liked the blog
            boolean isBookmarked = false;
            if (currentUser != null) {
                isBookmarked = BookMarkDAO.INS.isBookmarked(blogID, currentUser.getId());
                String userID = currentUser.getId();
                hasLiked = BlogLikeDAO.INS.hasUserLikedBlog(blogID, userID); // Check if the user has liked the blog
                hasReported = BlogDAO.INS.hasUserReportedBlog(userID, blogID); // Check if the user has reported the blog

                boolean isBookmarkedByCurrentUser = BookMarkDAO.INS.isBookmarked(blogID, userID);

                // Set attributes for use in JSP
                request.setAttribute("isBookmarkedByCurrentUser", isBookmarkedByCurrentUser);
            }

            List<String> allReportReasons = BlogDAO.INS.getAllReportReasons();
            List<String> topReportReasons = allReportReasons.subList(0, Math.min(10, allReportReasons.size()));

            if (currentUser != null) {
                List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, currentUser.getId());
                request.setAttribute("listNotif", listNotif);
            }

            request.setAttribute("blog", blog);
            request.setAttribute("images", images);
            request.setAttribute("user", user);
            request.setAttribute("listComment", comments);
            request.setAttribute("listBlogReportReason", topReportReasons);
            request.setAttribute("hasLiked", hasLiked); // Add this attribute
            request.setAttribute("hasReported", hasReported); // Add this attribute
            request.setAttribute("isBookmarked", isBookmarked); // Add this attribute

            request.setAttribute("following", following);
            request.setAttribute("follower", follower);
            request.setAttribute("totalBlogs", totalBlogs);

            request.setAttribute("mealType", mealType);
            request.setAttribute("foodType", foodType);
            request.setAttribute("specialDiet", specialDiet);
            request.setAttribute("priceRange", priceRange);
            request.setAttribute("hashTag", hashTag);

            int blogLikes = BlogLikeDAO.INS.getLikeCount(blogID);
            blog.setLike(blogLikes);

            request.getRequestDispatcher("blogdetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("errorblog.jsp");
        }

    }
}
