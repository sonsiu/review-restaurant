/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.moder;

import consts.IConstants;
import dao.EateryDAO;
import entity.User;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author legion
 */
@MultipartConfig
@WebServlet(name = "AddEatery", urlPatterns = {"/moder/add-eatery"})
public class AddEatery extends HttpServlet {

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
        String page = "";
        try {
            String name = request.getParameter("name");
            String location = request.getParameter("location");

            //Get the image path string
            Part bill = request.getPart("image");
            InputStream inputStream;
            //Get current user_id based on account that logged in
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("currentUser");

            //Check if the size of image is 0 (meaning that the image box is empty)
            if (bill.getSize() == 0) {
                ServletContext context = getServletContext();
                String relativePath = IConstants.IMAGE_EATERY_DEFAULT_PATH;
                String fullPath = context.getRealPath(relativePath);
                File imageFile = new File(fullPath);
                FileInputStream fileStream = new FileInputStream(imageFile);
                inputStream = fileStream;
            } else {
                inputStream = bill.getInputStream();
            }
            EateryDAO.INS.addEatery(name, location, inputStream, user.getId());
            page = "list-eatery";

        } catch (Exception e) {
            page = "list-eatery";
            request.getRequestDispatcher(page).forward(request, response);
            return;
        }
        request.getRequestDispatcher(page).forward(request, response);
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
