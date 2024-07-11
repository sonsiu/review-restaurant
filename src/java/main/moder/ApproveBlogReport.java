/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.moder;

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
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ApproveBlogReport", urlPatterns = {"/approveblogreport"})
public class ApproveBlogReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        int blogID = Integer.parseInt(id);
        int blogStatus = Integer.parseInt(status);
        Blog blog = BlogDAO.INS.getBlogByID(blogID);
        
        List<String> listReporterId = BlogReportDAO.INS.getAllReporterIds(blogID);
        List<String> listReportReason = BlogReportDAO.INS.getAllReportReason(blogID);
        String approveMessToReporter = "Tiêu đề: " + blog.getTitle() + " | " + IConstants.TO_REPORTER_APPROVE_MESSAGE;
        String approveMessToReported = "Tiêu đề: " + blog.getTitle() + " | " + IConstants.TO_REPORTED_HIDDEN_MESSAGE + "\n";
        for (String reason : listReportReason) {
            approveMessToReported += " - " + reason + "\n";
        }

        // Handle the blog report in the DAO
        try {
            BlogDAO.INS.handleBlogReport(blogID, blogStatus);
            
            if (blogStatus == 2) {

                for (String reporterId : listReporterId) {

                    NotificationDAO.INS.makeNotification(IConstants.TO_REPORTER_APPROVE_TITLE, approveMessToReporter, null, reporterId, blogID);
                }
                NotificationDAO.INS.makeNotification(IConstants.TO_REPORTED_HIDDEN_TITLE, approveMessToReported, null, UserDAO.INS.findUserByBlogID(blogID).getId(), blogID);
            }

            // Send success response
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
