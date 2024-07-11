/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.moder;

import consts.IConstants;
import dao.BlogDAO;
import dao.NotificationDAO;
import dao.UserDAO;
import entity.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class VerifyBlog extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyBlog</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyBlog at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {

            String id = request.getParameter("id");
            String status = request.getParameter("status");
            int bid = Integer.parseInt(id);
            int bstatus = Integer.parseInt(status);
            String userId = UserDAO.INS.findUserByBlogID(bid).getId();

            Blog blog = BlogDAO.INS.getBlogByID(bid);
            String approveMess = "Tiêu đề: " + blog.getTitle() + " | " + IConstants.APPROVED_MESSAGE_NOTIFICATION;

            BlogDAO.INS.updateBlogStatus(bid, bstatus);
            
            if (bstatus == 1) {
                NotificationDAO.INS.makeNotification(IConstants.APPROVED_TITLE_NOTIFICATION , approveMess, null, userId, bid);
            }
//        else if(bstatus==2){
//            BlogDAO.INS.deleteBlogRejected(bid);
//        }
        } catch (Exception e) {
            response.sendRedirect("errorpage.jsp");
            return;
        }
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
        try {
            String id = request.getParameter("id");
            String mess = request.getParameter("rejectMessage");
            int bid = Integer.parseInt(id);
            String userId = UserDAO.INS.findUserByBlogID(bid).getId();

            Blog blog = BlogDAO.INS.getBlogByID(bid);
            String rejectMess = "Tiêu đề: " + blog.getTitle() + " | Lí do : " + mess;

            BlogDAO.INS.updateBlogStatus(bid, 2);
            NotificationDAO.INS.makeNotification(IConstants.REJECTED_TITLE_NOTIFICATION, rejectMess, null, userId, bid);
            response.sendRedirect("listBlog");
        } catch (Exception e) {
            response.sendRedirect("errorpage.jsp");
            return;
        }
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
