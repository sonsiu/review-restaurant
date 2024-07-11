/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.EateryDAO;
import entity.Eatery;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author legion
 */
@MultipartConfig
@WebServlet(name="SearchEateryByAJAX", urlPatterns={"/search-eatery"})
public class SearchEateryByAJAX extends HttpServlet {

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
        String txt = request.getParameter("txt");
        ArrayList<Eatery> listEatery = EateryDAO.INS.getListBySearch(txt);
        PrintWriter out = response.getWriter();
        for (Eatery e : listEatery) {

            String image = e.getImage();
            out.println("<div>\n"
                    + "                            <div style=\"display:flex\">\n"
                    + "                                <img src=\"data:image/*;base64,"+image+"\" style=\"width: 60px; height: auto\" alt=\"Cat\">\n"
                    + "                            </div>\n"
                    + "                            <div class=\"text-wrapper\">\n"
                    + "                                <button type=\"button\" data-eatery-id=\"" + e.getEateryID() + "\" data-eatery-name=\"" + e.getName() + "\" data-eatery-location=\"" + e.getLocation() + "\" onclick=\"handlePickButtonClick(this)\" style=\"float:right\">Pick</button>\n"
                    + "                                <h3>" + e.getName() + " - <span>" + e.getLocation() + "</span></h3>\n"
                    + "                            </div>\n"
                    + "                        </div>");
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
