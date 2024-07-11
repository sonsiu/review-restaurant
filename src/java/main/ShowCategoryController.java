/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main;

import dao.BlogDAO;
import dao.BlogImageDAO;
import dao.BlogInfoDAO;
import dao.GlobalMessageDAO;
import dao.NotificationDAO;
import dao.UserDAO;
import entity.Blog;
import entity.BlogImage;
import entity.BlogInfo;
import entity.GlobalMessage;
import entity.Notification;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import service.Service;

/**
 *
 * @author toanl
 */
@WebServlet(name = "ShowCategoryController", urlPatterns = {"/category"})
public class ShowCategoryController extends HttpServlet {

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
            out.println("<title>Servlet ShowCategoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowCategoryController at " + request.getContextPath() + "</h1>");
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
        Cookie cookieArray[] = request.getCookies();
        HttpSession session = request.getSession();
        String cookieUsername = "";
        String cookiePassword = "";

        String type = request.getParameter("type");
        String name = request.getParameter("name");
        ArrayList<Blog> listB = BlogInfoDAO.INS.getCategory(name, type);

        // Retrieve current user from session
        User user = (User) session.getAttribute("currentUser");
        ArrayList<BlogImage> listImage = BlogImageDAO.INS.getFirstImageOfAllBlog();

        request.setAttribute("breakfast", BlogInfoDAO.INS.countType("meal", "breakfast"));
        request.setAttribute("lunch", BlogInfoDAO.INS.countType("meal", "lunch"));
        request.setAttribute("brunch", BlogInfoDAO.INS.countType("meal", "brunch"));
        request.setAttribute("dinner", BlogInfoDAO.INS.countType("meal", "dinner"));
        request.setAttribute("latenight", BlogInfoDAO.INS.countType("meal", "late night"));
        request.setAttribute("drink", BlogInfoDAO.INS.countType("meal", "drink"));
        request.setAttribute("vietnamese", BlogInfoDAO.INS.countType("food", "vietnamese"));
        request.setAttribute("chinese", BlogInfoDAO.INS.countType("food", "chinese"));
        request.setAttribute("korean", BlogInfoDAO.INS.countType("food", "korean"));
        request.setAttribute("japanese", BlogInfoDAO.INS.countType("food", "japanese"));
        request.setAttribute("thai", BlogInfoDAO.INS.countType("food", "thai"));
        request.setAttribute("european", BlogInfoDAO.INS.countType("food", "europe"));
        request.setAttribute("american", BlogInfoDAO.INS.countType("food", "american"));
        request.setAttribute("other", BlogInfoDAO.INS.countType("food", "other"));
        request.setAttribute("vegan", BlogInfoDAO.INS.countType("special", "vegan"));
        request.setAttribute("highprotein", BlogInfoDAO.INS.countType("special", "high-protein"));
        request.setAttribute("lowprotein", BlogInfoDAO.INS.countType("special", "low-protein"));
        request.setAttribute("lowfat", BlogInfoDAO.INS.countType("special", "low-fat"));
        request.setAttribute("glutenfree", BlogInfoDAO.INS.countType("special", "glueten-free"));
        request.setAttribute("diabetic", BlogInfoDAO.INS.countType("special", "diabetic diet"));

        request.setAttribute("listBlogImage", listImage);
        request.setAttribute("listB", listB);
        request.setAttribute("name", Service.translateToVietnamese(name));
        GlobalMessageDAO notificationDAO = GlobalMessageDAO.INS;
        List<GlobalMessage> shownNotifications = notificationDAO.getGlobalMessages();

        if (user != null) {
            List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, user.getId());
            request.setAttribute("listNotif", listNotif);
        }

        // Đặt danh sách thông báo vào request
        request.setAttribute("shownNotifications", shownNotifications);

        if (cookieArray != null) {
            for (Cookie o : cookieArray) {
                if (o.getName().equals("cookieUsername")) {
//                    request.setAttribute("cookieUsername", o.getValue());
                    cookieUsername = o.getValue();
                }
                if (o.getName().equals("cookiePassword")) {
//                    request.setAttribute("cookiePassword", o.getValue());
                    cookiePassword = o.getValue();
                }
            }
            User cookieUser = UserDAO.INS.authenticate(cookieUsername, cookiePassword);
            if (cookieUser != null) {
                if (cookieUser.getStatus() == 0) { //unverified user
                    session.setAttribute("currentUser", cookieUser);
                    request.getRequestDispatcher("login.jsp").forward(request, response);

                } else if (cookieUser.getStatus() == 1) {
                    session.setAttribute("currentUser", cookieUser);
                    session.setMaxInactiveInterval(600);

                    if (cookieUser.getRole() == 2) {
//                        response.sendRedirect(request.getContextPath() + "/homepage");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        return;
                    }
                    return;
                } else if (cookieUser.getStatus() == 2) {
                    request.setAttribute("loginMess", "Your account has been deactivated . Check Gmail for info");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("category.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("category.jsp").forward(request, response);
            return;
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
        response.sendRedirect(request.getContextPath() + "/category");
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
