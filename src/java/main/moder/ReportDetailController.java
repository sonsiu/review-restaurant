/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.moder;


import dao.*;
import entity.*;
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
 * @author ACER
 */
@WebServlet(name = "ReportDetailController", urlPatterns = {"/moder/report-detail"})
public class ReportDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int blogID;

        try {
            blogID = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/errorblog.jsp");
            return;
        }

        Blog blog = BlogDAO.INS.getBlogByID(blogID);
        if (blog == null) {
            response.sendRedirect(request.getContextPath() + "/errorblog.jsp");
            return;
        }

        List<String> reportReasons = BlogReportDAO.INS.getAllReportReason(blogID);
        List<User> reporters = BlogReportDAO.INS.getReportersByBlogID(blogID);

        request.setAttribute("blog", blog);
        request.setAttribute("reportReasons", reportReasons);
        request.setAttribute("reporters", reporters);
        request.getRequestDispatcher("./reportBlog.jsp").forward(request, response);
    }

}
