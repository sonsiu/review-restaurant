/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.BlogDAO;
import dao.BlogImageDAO;
import dao.BlogInfoDAO;
import dao.NotificationDAO;
import entity.Blog;
import entity.BlogImage;
import entity.BlogInfo;
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
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author legion
 */
@WebServlet(name = "ShowBlogByHashtagController", urlPatterns = {"/show-by-hashtag"})
public class ShowBlogByHashtagController extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, user.getId());
            request.setAttribute("listNotif", listNotif);
        }
        
        try {
            String hashtag = request.getParameter("hashtag");
            ArrayList<Blog> blogList = BlogDAO.INS.getBlogListByHashTag(hashtag);
            ArrayList<BlogImage> imageList = BlogImageDAO.INS.getFirstImageOfAllBlog();
            request.setAttribute("blogs", blogList);
            request.setAttribute("images", imageList);
            request.setAttribute("hashtag", hashtag);
            request.getRequestDispatcher("hashtag-bloglist.jsp").forward(request, response);
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
