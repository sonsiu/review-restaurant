package main.moder;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import service.SendEmail;

@WebServlet(name = "UserStatusServlet", urlPatterns = {"/moder/user-status"})
public class UserStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            SendEmail sendEmail = new SendEmail();
            String userID = request.getParameter("userId");
            int status = Integer.parseInt(request.getParameter("status"));
            User reportedUser = UserDAO.INS.findUserById(userID);

            if (reportedUser != null && reportedUser.getStatus() == 3) { // Ensure user exists and status is pending (3)
                String title;
                String content;
                List<String> reportReasons = UserDAO.INS.getReportReasonsByUserId(userID);

                if (status == 2) {
                    String reasonContent = UserDAO.INS.getReportReasonContentByUserId(userID);
                    title = "Your account @" + reportedUser.getId() + " banned";
                    content = "Your account @" + reportedUser.getId() + " has been reported for the following reasons:\n";
                    
                    for (String reason : reportReasons) {
                        content += "- " + reason + "\n";
                    }
                    sendEmail.sendEmailAfterChangeUserStatus(reportedUser.getEmail(), reportedUser.getId(), title, content);
                    UserDAO.INS.deleteReportsByUserId(userID);
                } else if (status == 1) {
                    //Handle
                    UserDAO.INS.deleteReportsByUserId(userID);
                } else {
                    // Invalid status received
                    response.getWriter().write("Invalid status received");
                    return;
                }

                // Update user status
                UserDAO.INS.handleUserReport(userID, status);

                // Return success response
                response.getWriter().write("User status updated successfully");
            } else {
                // Either user does not exist or status is not pending
                response.getWriter().write("User status update failed. User may not exist or status is not pending");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Failed to handle user status update");
        }
    }
}
