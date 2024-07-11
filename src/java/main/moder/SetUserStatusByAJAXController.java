/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.moder;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SendEmail;

/**
 *
 * @author legion
 */
@WebServlet(name = "SetUserStatusByAJAXController", urlPatterns = {"/moder/set-user-status"})
public class SetUserStatusByAJAXController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeactivateUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeactivateUserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            SendEmail sentEmail = new SendEmail();
            String userID = request.getParameter("userID");
            User user = UserDAO.INS.findUserById(userID);

            //If deactivated then reactivated , the reverse is in doPost
            if (user.getStatus() == 2) {
                String title = "Your account @"+ user.getId()+" has been re-activated";
                String content = "You can now use your account to access to our website .";
                UserDAO.INS.updateStatus(1, userID);
                sentEmail.sendEmailAfterChangeUserStatus(user.getEmail(), user.getId(),title, content);
            }
        } catch (Exception e) {
            response.sendRedirect("moderator.jsp");
        }
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
        try {
            SendEmail sentEmail = new SendEmail();
            String userID = request.getParameter("userID");
            User user = UserDAO.INS.findUserById(userID);
            String reason = request.getParameter("deactivaedReason");

            if (user.getStatus() == 1) {
                String title = "Your account @"+ user.getId()+" has been deactivated";
                String content = "Deactivated reason : "+ reason+" . If you think this decision is incorrect , please contact to us via this email";
                UserDAO.INS.updateStatus(2, userID);
                sentEmail.sendEmailAfterChangeUserStatus(user.getEmail(), user.getId(),title, content);
                
                request.setAttribute("title", "Success !");
                request.setAttribute("message", "Account @" +user.getId()+" has successfully deactivated");
                request.getRequestDispatcher("showusers").forward(request, response);
                //response.sendRedirect("showusers");
                return;
            } else {
                response.sendRedirect("moderator.jsp");
                return;
            }

        } catch (Exception e) {
            response.sendRedirect("moderator.jsp");
        }
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
