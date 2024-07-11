/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import consts.IConstants;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author legion
 */
@WebServlet(name = "LogOutController", urlPatterns = {"/log-out"})
public class LogOutController extends HttpServlet {

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
        HttpSession sess = request.getSession();

        Cookie cookieArray[] = request.getCookies();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Cookie cookieUsername = new Cookie("cookieUsername", username);
        Cookie cookiePassword = new Cookie("cookiePassword", password);
        Cookie cookieRemember = new Cookie("cookieRemember", remember);
        cookieRemember.setMaxAge(0);
        cookieUsername.setMaxAge(0);
        cookiePassword.setMaxAge(0);
        response.addCookie(cookieRemember);
        response.addCookie(cookieUsername);
        response.addCookie(cookiePassword);
        sess.removeAttribute("currentUser");

        if (cookieArray != null) {
            for (Cookie o : cookieArray) {
                if (o.getName().equals("cookieRemember")) {
                    request.setAttribute("remember", o.getValue());
                }
            }
        }

//        response.sendRedirect("homepage");
//        response.sendRedirect("login.jsp");
        request.setAttribute("GOOGLE_LOGIN_HREF", IConstants.GOOGLE_LOGIN_HREF);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
