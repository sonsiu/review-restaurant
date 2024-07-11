/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.*;
import entity.Notification;
import entity.User;
import service.*;
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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 *
 * @author legion
 */
@MultipartConfig
@WebServlet(name = "EditUserProfileController", urlPatterns = {"/edit-user"})
public class EditUserProfileController extends HttpServlet {

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
            out.println("<title>Servlet EditUserProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditUserProfileController at " + request.getContextPath() + "</h1>");
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
        try {
            String displayName = request.getParameter("displayname");
            Part bill = request.getPart("imageLink");
            InputStream inputStream;

            HttpSession session = request.getSession();
            User sessionUser = (User) session.getAttribute("currentUser");

            if (bill.getSize() == 0) {
                byte[] imageBytes = Base64.getDecoder().decode(sessionUser.getProfilePicture());
                inputStream = new ByteArrayInputStream(imageBytes);
            } else {
                inputStream = bill.getInputStream();
            }
            
            String phone = request.getParameter("phone");
            if(phone.isBlank()){
                phone = sessionUser.getPhone();
            }
            
            if(displayName.isBlank()){
                displayName = sessionUser.getDisplayName();
            }
            
            String city = request.getParameter("textCity");
            String district = request.getParameter("textDistrict");
            String ward = request.getParameter("textWard");
            String location = Service.combineLocationStrings(city, district, ward);
            
            if(city.isBlank() || district.isBlank() || ward.isBlank()){
                location = sessionUser.getLocation();
            }

            UserDAO userDAO = new UserDAO();
            boolean result = userDAO.updateUser(sessionUser.getId(), displayName, inputStream, location, phone);

            if (result) {
                User updatedUser = UserDAO.INS.findUserById(sessionUser.getId());
                session.setAttribute("currentUser", updatedUser);
                request.setAttribute("notify", Notification.builder().type("success").title("Cập nhật thành công !").message("Thông tin cá nhân cập nhập").build());
            } else {
                request.setAttribute("notify", Notification.builder().type("error").title("Cập nhật thất bại !").message("Có lỗi đã xảy ra                  ").build());
            }

            request.getRequestDispatcher("showblog").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("errorpage.jsp");
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
