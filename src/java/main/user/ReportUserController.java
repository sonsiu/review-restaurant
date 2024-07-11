/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package main.user;


import consts.IConstants;
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
import java.sql.*;
/**
 *
 * @author Vinh
 */
@WebServlet(name="ReportUserController", urlPatterns={"/reportuser"})
public class ReportUserController extends HttpServlet {
   
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
            out.println("<title>Servlet ReportUserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportUserController at " + request.getContextPath () + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String userIdParam = request.getParameter("userId");
        String reportReason = request.getParameter("reportReason");
        boolean isOtherReason = Boolean.parseBoolean(request.getParameter("isOtherReason"));

        try {
             HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");

            if (currentUser != null) {
                String userReportedId = userIdParam;
                String userReporterId = currentUser.getId(); // Assuming user ID is stored in session

                // Check if the user has already reported this user
                if (UserDAO.INS.hasUserReportedUser(userReporterId, userReportedId)) {
                    response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
                    return;
                }

                int reportReasonId;
                if (isOtherReason) {
                    // Insert the new reason into user_report_reason (if applicable)
                    reportReasonId = UserDAO.INS.addReportReason(reportReason);
                } else {
                    reportReasonId = UserDAO.INS.getReportReasonID(reportReason);
                }

                UserReport userReport = UserReport.builder()
                        .userReportedId(userReportedId)
                        .userReporterId(userReporterId)
                        .reportReasonId(reportReasonId)
                        .build();

                UserDAO.INS.reportUser(userIdParam, reportReasonId, userReporterId);

                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
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
