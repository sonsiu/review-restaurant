/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.admin;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author legion
 */
@WebServlet(name = "SearchUserByAJAXController", urlPatterns = {"/admin/search-mod"})
public class SearchModByAJAXController extends HttpServlet {

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
        String status = request.getParameter("status"); //Status , name
        String txt = request.getParameter("txt");
        String action = request.getParameter("action");
        List<User> users = new ArrayList<User>();

        switch (status) {
            case "all" -> {
                switch (action) {
                    case "all" ->
                        users = UserDAO.INS.getAllNormalModerators();
                    case "search" ->
                        users = UserDAO.INS.searchNormalModerators(txt);
                }
            }

            case "activated" ->
                users = UserDAO.INS.searchNormalModByStatus(txt, status, action);

            case "deactivated" ->
                users = UserDAO.INS.searchNormalModByStatus(txt, status, action);

        }
        PrintWriter out = response.getWriter();
        for (User e : users) {

            String image = e.getProfilePicture();
            String showBlogURL = request.getContextPath() + "/showblog?ID=" + e.getId();
            String htmlContext = "";
            htmlContext = "<tr class=\"user-info\">"
                    + "<td class=\"inbox-small-cells\">" + e.getStatus() + "</td>\n"
                    + "                                                                                <td class=\"inbox-small-cells\">\n"
                    + "                                                                                    <img src=\"data:image/*;base64," + image + "\" style=\"width:30px;\">\n"
                    + "                                                                                </td>\n"
                    + "                                                                                <td id=\"userID\" class=\"view-message dont-show\">" + e.getId() + "</td>\n"
                    + "                                                                                <td class=\"view-message\">" + e.getDisplayName() + " </td>\n"
                    + "                                                                                <td class=\"view-message inbox-small-cells\">" + e.getEmail() + "</td>\n"
                    + "                                                                                <td class=\"view-message text-right\">Date Add Later</td>\n"
                    + "                                                                                <td>\n"
                    + "                                                                                    <a href=\"" + showBlogURL + "\"/>\n"
                    + "                                                                                        <button type=\"button\" class=\"btn btn-primary btn-xs action-btn\">\n"
                    + "                                                                                            <i class=\"fa fa-user\"> </i> View\n"
                    + "                                                                                        </button>\n"
                    + "                                                                                    </a>\n"
                    + "                                                                                    <input type=\"hidden\" id=\"userIDInfo\" value=\"" + e.getId() + "\">\n";

            if (e.getStatus() == 1) {
                htmlContext += "<button id=\"rejectBtn\" type=\"button\" class=\"btn btn-danger btn-xs\" data-toggle=\"modal\" data-target=\"#myModal" + e.getId() + "\">\n"
                        + "                                                                                            Deactivate\n"
                        + "                                                                                        </button>";
            }
            if (e.getStatus() == 2) {
                htmlContext += "<button type=\"button\" class=\"btn btn-success btn-xs\" id=\"toggleButton\" onclick=\"changeUserStatus('" + e.getId() + "'); changeButton(this)\">\n"
                        + "                                                                                            Activate\n"
                        + "                                                                                        </button>";
            }
            if (e.getStatus() == 0) {
                htmlContext += "<button type=\"button\" class=\"btn btn-warning btn-xs\" id=\"toggleButton\"\">\n"
                        + "                                                                                            Unverified\n"
                        + "                                                                                        </button>";
            }

            htmlContext += "<td> \n"
                    + "<tr> \n";

            out.println(htmlContext);
        }

        out.println("<script>"
                + "const buttonContainer = document.querySelectorAll('.user-info'); // Select all rows\n"
                + "\n"
                + "                                                                                            buttonContainer.forEach(row => {\n"
                + "                                                                                                row.addEventListener('click', function (event) {\n"
                + "                                                                                                    const clickedButton = event.target;  // Get the clicked element\n"
                + "//                                                                                                     if (clickedButton.classList.contains('btn-danger')) {\n"
                + "//                                                                                                         clickedButton.classList.remove('btn-danger');\n"
                + "//                                                                                                         clickedButton.classList.add('btn-success');\n"
                + "//                                                                                                         clickedButton.textContent = 'Activate';\n"
                + "//\n"
                + "//\n"
                + "//                                                                                                     } \n"
                + "                                                                                                    if (clickedButton.classList.contains('btn-success')) {\n"
                + "                                                                                                        // Activate logic (if applicable)...\n"
                + "                                                                                                        clickedButton.classList.remove('btn-success'); // Assuming this is for another button state\n"
                + "                                                                                                        clickedButton.classList.add('btn-danger');\n"
                + "                                                                                                        clickedButton.textContent = 'Deactivate';\n"
                + "\n"
                + "\n"
                + "                                                                                                    }\n"
                + "                                                                                                });\n"
                + "                                                                                            });</script>");
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
