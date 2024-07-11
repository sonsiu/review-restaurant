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
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ListBlogReport", urlPatterns = {"/moder/listblogreport"})
public class ListBlogReport extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Blog> blogList = BlogDAO.INS.getReportedBlogs();
        request.setAttribute("blogReportList", blogList);
        request.getRequestDispatcher("./reportBlog.jsp").forward(request, response);
    }

}
