/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package main.moder;

import dao.UserDAO;
import dao.UserReportDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Vinh
 */
@WebServlet(name="UserReportDetailController", urlPatterns={"/moder/user-report-detail"})
public class UserReportDetailController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UserReportDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserReportDetailController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String idParam = request.getParameter("id");
        String userId;

        try {
            userId = idParam;
        } catch (NumberFormatException e) {
            response.sendRedirect("erroruser.jsp");
            return;
        }

        User user = UserDAO.INS.findUserById(userId);
        if (user == null) {
            response.sendRedirect("erroruser.jsp");
            return;
        }

        List<String> reportReasons = UserReportDAO.INS.getReportReasonsByUserId(userId);
        List<User> reporters = UserReportDAO.INS.getReportersByUserID(userId);
            int reportCount = UserReportDAO.INS.getReportCountByUserId(userId);

        request.setAttribute("user", user);
        request.setAttribute("reportReasons", reportReasons);
        request.setAttribute("reporters", reporters);
         request.setAttribute("reportCount", reportCount); // Pass report count to JSP
        request.getRequestDispatcher("reportUser.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
