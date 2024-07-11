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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author legion
 */
@WebServlet(name = "SearchUsersByAJAX", urlPatterns = {"/moder/search-users"})
public class SearchUsersByAJAX extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String status = request.getParameter("status"); //Status , name
        String txt = request.getParameter("txt");
        String action = request.getParameter("action");
        ArrayList<User> users = new ArrayList<User>();

        try {
            switch (status) {
                case "all" -> {
                    switch (action) {
                        case "all":
                            users = UserDAO.INS.getAllNormalUsers();
                            break;

                        case "search":
                            users = UserDAO.INS.searchNormalUser(txt);
                            break;
                    }
                }

                case "activated" ->
                    users = UserDAO.INS.searchNormalUserByStatus(txt, status, action);

                case "deactivated" ->
                    users = UserDAO.INS.searchNormalUserByStatus(txt, status, action);

            }
            for (User user : users) {
                int reportSum = UserDAO.INS.getReportSumByUserId(user.getId());
                user.setReportSum(reportSum);
            }
        } catch (Exception e) {

        }
        PrintWriter out = response.getWriter();
        for (User e : users) {

            String image = e.getProfilePicture();
            String showBlogURL = request.getContextPath() + "/showblog?ID=" + e.getId();
            String htmlContext = "";
            htmlContext = "<tr class=\"user-info\">\n"
                    + "   <td class=\"inbox-small-cells\">\n"
                    + " <img src=\"data:image/*;base64," + image + "\" style=\"width:30px;\">\n"
                    + "   </td>\n"
                    + "   <td id=\"userID\" class=\"view-message dont-show\">" + e.getId() + "</td>\n"
                    + "   <td class=\"view-message\">" + e.getDisplayName() + " </td>\n"
                    + "  <td class=\"view-message inbox-small-cells\">" + e.getEmail() + "</td>\n"
                    + "<td class=\"inbox-small-cells\"> ";

            if (e.getStatus() == 0) {
                htmlContext += "<button type=\"button\" class=\"btn btn-warning btn-xs\" id=\"toggleButton\"\">\n"
                        + "                                                                                            Unverified\n"
                        + "                                                                                        </button>";
            }
            if (e.getStatus() == 1 || e.getStatus() == 3) {
                htmlContext += "<button id=\"\" type=\"button\" class=\"btn btn-success btn-xs\">\n"
                        + "                                                                                            Activated\n"
                        + "                                                                                        </button>";
            }
            if (e.getStatus() == 2) {
                htmlContext += "<button id=\"statusBtn\" type=\"button\" class=\"btn btn-danger btn-xs\">\n"
                        + "                                                                                            Deactivated\n"
                        + "                                                                                        </button>";
            }

            htmlContext += "</td> \n"
                    + "<td class=\"view-message inbox-small-cells\">\n"
                    + "<a href=\"" + showBlogURL + "\" >\n"
                    + "<button type=\"button\" class=\"btn btn-primary btn-xs action-btn\">\n"
                    + "  <i class=\"fa fa-user\"> </i> View\n"
                    + "  </button>\n"
                    + "</a>\n"
                    + " </td>"
                    + "  <input type=\"hidden\" id=\"userIDInfo\" value=\"" + e.getId() + "\">\n";

            htmlContext += "<td>";
            if (e.getStatus() == 2) {
                htmlContext += "<button type=\"button\" class=\"btn btn-success btn-xs\" id=\"toggleButton\" onclick=\"changeUserStatus('" + e.getId() + "'); changeButton(this)\">\n"
                        + "     Re-activate\n"
                        + "     </button>";
            }
            htmlContext += "</td>";
            htmlContext += "<td>" + e.getReportSum() + "</td>\n"
                    + "                                                                                <td>";
            if (e.getReportSum() != 0) {
                String showReportURL = request.getContextPath() + "/moder/user-report-detail?id=" + e.getId();
                htmlContext += " <a href='" + showReportURL + "'>\n"
                        + " <button type=\"button\" class=\"btn btn-info btn-xs\">View Report</button>\n"
                        + " </a>";
            }
            htmlContext += "</td>\n";
            htmlContext += "</td>\n"
                    + "<tr> \n";

            out.println(htmlContext);
        }

        out.println("<script>"
                + "function changeButton(btn) {\n"
                + "                                                                                                if (btn.classList.contains('btn-success')) {\n"
                + "                                                                                                    btn.style.display = \"none\";\n"
                + "\n"
                + "                                                                                                    const statusBtn = document.getElementById(\"statusBtn\");\n"
                + "                                                                                                    if (statusBtn.classList.contains('btn-danger')) {\n"
                + "                                                                                                        statusBtn.classList.remove('btn-danger');\n"
                + "                                                                                                        statusBtn.classList.add('btn-success');\n"
                + "                                                                                                        statusBtn.textContent =\"Activated\";\n"
                + "                                                                                                    }\n"
                + "\n"
                + "                                                                                                }\n"
                + "                                                                                            }"
                + "</script>");
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
