/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package main.moder;

import com.google.gson.Gson;
import dao.BlogDAO;
import dao.CommentDAO;
import dao.UserDAO;
import entity.Blog;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class moderatorDashboardController extends HttpServlet {
   
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
        // them may cái hàm trong từng dao có trong servlet này
        //sửa moderator.jsp
        //sửa sidebar.jsp để trả về dashboard
        // thêm script ở 
       int sCus=UserDAO.INS.getStaticCustomers();
        int sBlog=BlogDAO.INS.getStaticBlog();
        int sCom=CommentDAO.INS.getStaticComment();
        Float sRate=BlogDAO.INS.getRateStaticBlog();
        ArrayList<User> get5User=UserDAO.INS.get5LasterUser();
        ArrayList<Blog> listBlog=BlogDAO.INS.getBlog();
        String[][] data = BlogDAO.INS.getDataCharts();
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);
            
        request.setAttribute("dataChart", jsonData);
        request.setAttribute("listBlog", listBlog);
         request.setAttribute("get5User", get5User);
         request.setAttribute("sCom", sCom);
        request.setAttribute("sRate", sRate);
        request.setAttribute("sCus", sCus);
        request.setAttribute("sBlog", sBlog);
        request.getRequestDispatcher("moderator.jsp").forward(request, response);
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
        processRequest(request, response);
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
