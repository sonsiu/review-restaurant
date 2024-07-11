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
 * @author ACER
 */
@WebServlet(name = "ReportBlogController", urlPatterns = {"/reportblog"})
public class ReportBlogController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String blogIDParam = request.getParameter("blogID");
        String reportReason = request.getParameter("reportReason");
        boolean isOtherReason = Boolean.parseBoolean(request.getParameter("isOtherReason"));

        try {
            int blogID = Integer.parseInt(blogIDParam);

            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");

            if (currentUser != null) {
                String blogReporterID = currentUser.getId();
                Blog blog = BlogDAO.INS.getBlogByID(blogID);
                String blogAuthorID = blog.getUserID();
                int reportReasonID;
                if (isOtherReason) {
                    
                    reportReasonID = BlogDAO.INS.addReportReason(reportReason);
                } else {
                    reportReasonID = BlogDAO.INS.getReportReasonID(reportReason);
                }

                BlogDAO.INS.reportBlog(blogID, reportReasonID, blogAuthorID, blogReporterID);

                String reportMessage = IConstants.TO_REPORTED_REPORT_MESSAGE + blog.getTitle() + " với lí do: " + reportReason;
                NotificationDAO.INS.makeNotification(IConstants.TO_REPORTED_REPORT_TITLE,reportMessage, null, blogAuthorID, blogID);
                response.setStatus(HttpServletResponse.SC_OK);
            } 
            else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
