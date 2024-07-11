/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.BlogDAO;
import dao.BlogImageDAO;
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
 * @author ACER
 */
@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String txt = request.getParameter("txt");

        List<Blog> listBlog = null;
        List<User> listUser = null;

        ArrayList<BlogImage> listImage = BlogImageDAO.INS.getFirstImageOfAllBlog();
        
        User currentUser = (User) session.getAttribute("currentUser");
        switch (action) {
            case "all":
                listBlog = BlogDAO.INS.searchBlogByTitle(txt);
                listUser = UserDAO.INS.searchUserByDisplayName(txt);
                break;
            case "blog":
                listBlog = BlogDAO.INS.searchBlogByTitle(txt);
                break;
            case "user":
                listUser = UserDAO.INS.searchUserByDisplayName(txt);
                break;
        }

        if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equals("XMLHttpRequest")) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                if ("all".equals(action) || "blog".equals(action)) {
                    if (listBlog != null && !listBlog.isEmpty()) {
                        out.println("<div class=\"row featured__filter\">");
                        out.println("<div class=\"col-lg-12 section-search\"><h2>Blogs:</h2></div>");
                        for (Blog blog : listBlog) {
                            out.println("<div class=\"col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat\">");
                            out.println("<div class=\"featured__item\">");
                            for (BlogImage image : listImage) {
                                if (Integer.parseInt(image.getBlogID()) == blog.getBlogID()) {
                                    out.println("<div class=\"featured__item__pic\"><img src=\"data:image/*;base64," + image.getImageLink() + "\" alt=\"\"></div>");
                                }
                            }
                            out.println("<div class=\"featured__item__text\"><h6><a href=\"blog-detail?id=" + blog.getBlogID() + "\">" + blog.getTitle() + "</a></h6></div>");
                            out.println("</div></div>");
                        }
                        out.println("</div>");
                    }
                }

                if ("all".equals(action) || "user".equals(action)) {
                    if (listUser != null && !listUser.isEmpty()) {
                        out.println("<div class=\"row featured__filter\">");
                        out.println("<div class=\"col-lg-12 section-search\"><h2>Users:</h2></div>");
                        for (User user : listUser) {
                            out.println("<div class=\"col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat\">");
                            out.println("<div class=\"featured__item\">");
                            out.println("<div class=\"featured__item__pic\"><img src=\"data:image/*;base64," + user.getProfilePicture() + "\" alt=\"\"></div>");
                            out.println("<div class=\"featured__item__text\"><h6>" + user.getDisplayName() + "</h6></div>");
                            out.println("</div></div>");
                        }
                        out.println("</div>");
                    }
                }
            }
        } else {
            if (currentUser != null) {
                List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, currentUser.getId());
                request.setAttribute("listNotif", listNotif);
            }
            request.setAttribute("listBlogsearch", listBlog);
            request.setAttribute("blogCount", listBlog.size());
            request.setAttribute("listUsersearch", listUser);
            request.setAttribute("userCount", listUser.size());
            request.setAttribute("listBlogImage", listImage);
            request.setAttribute("txt", txt);
            request.getRequestDispatcher("search.jsp").forward(request, response);
        }
    }

}
