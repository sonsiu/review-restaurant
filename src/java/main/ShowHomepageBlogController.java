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
import dao.UserSettingDAO;
import entity.Blog;
import entity.BlogImage;
import entity.GlobalMessage;
import entity.Notification;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author legion
 */
@WebServlet(name = "ShowHomepageBlogController", urlPatterns = {"/homepage"})
public class ShowHomepageBlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cookieArray[] = request.getCookies();
        HttpSession session = request.getSession();
        String cookieUsername = "";
        String cookiePassword = "";
        String from = (String) session.getAttribute("from");

        // Retrieve current user from session
        User user = new User();
        if (session.getAttribute("currentUser") != null) {
            user = (User) session.getAttribute("currentUser");
        }
        String action = request.getParameter("action");
        String cate = request.getParameter("cate");
        // Check if user is logged in

        ArrayList<Blog> list = BlogDAO.INS.getListOfBlogApproved();
        ArrayList<Blog> listB = BlogDAO.INS.getFeatruredBlog(null);
        if (action == null || action.equals("")) {
            listB = list;
        } else if (action.equals("follow")) {
            listB = BlogDAO.INS.getFollowBlog(user.getId());
        } else if (action.equals("near")) {
            listB = BlogDAO.INS.getNearestBlog(user.getId());
        }
        ArrayList<BlogImage> listImage = BlogImageDAO.INS.getFirstImageOfAllBlog();

        //Get Count by Category 
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

        request.setAttribute("listB", listB);
        request.setAttribute("listBlog", list);
        request.setAttribute("listBlogImage", listImage);
        GlobalMessageDAO notificationDAO = GlobalMessageDAO.INS;
        List<GlobalMessage> shownNotifications = notificationDAO.getGlobalMessages();

        if (user != null) {
            List<Notification> listNotif = NotificationDAO.INS.getNLastDayNotificationByUserId(2, user.getId());
            request.setAttribute("listNotif", listNotif);
        }

        // Đặt danh sách thông báo vào request
        request.setAttribute("shownNotifications", shownNotifications);

//        if (cookieArray != null) {
//            for (Cookie o : cookieArray) {
//                if (o.getName().equals("cookieUsername")) {
////                    request.setAttribute("cookieUsername", o.getValue());
//                    cookieUsername = o.getValue();
//                }
//                if (o.getName().equals("cookiePassword")) {
////                    request.setAttribute("cookiePassword", o.getValue());
//                    cookiePassword = o.getValue();
//                }
//            }
//        }
//
//        User cookieUser = UserDAO.INS.authenticate(cookieUsername, cookiePassword);
//        if (cookieUser != null && session.getAttribute("currentUser") != null) {
//            if (UserSettingDAO.INS.findSettingByUserId(cookieUser.getId()).isRememberMe()) {
//                if (cookieUser.getStatus() == 1) {
//                    request.getRequestDispatcher("index.jsp").forward(request, response);
//                } else if (cookieUser.getStatus() == 2) {
//                    request.setAttribute("loginMess", "Tài khoản này đã bị vô hiệu hóa, kiểm tra Gmail để biết thêm chi tiết");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
//                }
//            } else {
//                System.out.println("1");
//                session.removeAttribute("currentUser");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            }
//        } else if (cookieUser == null && session.getAttribute("currentUser") != null) {
//            user = (User) session.getAttribute("currentUser");
//            if (UserSettingDAO.INS.findSettingByUserId(user.getId()).isRememberMe()) {
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                return;
//            } else {
//                session.removeAttribute("currentUser");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                return;
//            }
//        } else if (cookieUser != null && session.getAttribute("currentUser") == null) {
//            System.out.println("3");
//            session.setAttribute("currentUser", cookieUser);
//            session.setMaxInactiveInterval(600);
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        } else {
//            System.out.println("4");
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
//--------------------------------------------------------------------------------------
//        if (cookieUser != null) {
//            System.out.println("1");
//            if (UserSettingDAO.INS.findSettingByUserId(cookieUser.getId()).isRememberMe()) {
//                System.out.println("2");
//                if (cookieUser.getStatus() == 1) {
//                    System.out.println("3");
//                    session.setAttribute("currentUser", cookieUser);
//                    session.setMaxInactiveInterval(600);
//                    request.getRequestDispatcher("index.jsp").forward(request, response);
//                } else if (cookieUser.getStatus() == 2) {
//                    System.out.println("4");
//                    request.setAttribute("loginMess", "Tài khoản này đã bị vô hiệu hóa, kiểm tra Gmail để biết thêm chi tiết");
//                    request.getRequestDispatcher("login.jsp").forward(request, response);
//                    System.out.println("3");
//                }
//            } else {
//                System.out.println("5");
//                session.removeAttribute("currentUser");
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            }
//        } else if (cookieUser == null && session.getAttribute("currentUser") != null) {
//            if (UserSettingDAO.INS.findSettingByUserId(user.getId()).isRememberMe()) {
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            } else {
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            }
//        } else {
//            System.out.println("7");
//            session.removeAttribute("currentUser");
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//        }
//-------------------------------------------------------------------------------------
        if (from == null) {//vao tu duong link
            if (cookieArray != null) {
                for (Cookie o : cookieArray) {
                    if (o.getName().equals("cookieUsername")) {
                        cookieUsername = o.getValue();
                    }
                    if (o.getName().equals("cookiePassword")) {
                        cookiePassword = o.getValue();
                    }
                }

                if (!cookiePassword.equals("")) {
                    User cookieUser = UserDAO.INS.authenticate(cookieUsername, cookiePassword);
                    if (cookieUser.getStatus() == 0) { //unverified user
                        session.setAttribute("currentUser", cookieUser);
                        request.getRequestDispatcher("login.jsp").forward(request, response);

                    } else if (cookieUser.getStatus() == 1) {
                        session.setAttribute("currentUser", cookieUser);
                        session.setMaxInactiveInterval(600);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else if (cookieUser.getStatus() == 2) {
                        request.setAttribute("loginMess", "Your account has been deactivated . Check Gmail for info");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                } else {
                    session.removeAttribute("currentUser");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }       
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {// vao tu login
            session.removeAttribute("from");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/homepage");
    }

}
