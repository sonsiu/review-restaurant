/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package main.user;

import dao.ReplyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "DeleteReplyController", urlPatterns = {"/deletereply"})
public class DeleteReplyController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String replyIDStr = request.getParameter("id");
        if (replyIDStr != null) {
            try {
                int replyID = Integer.parseInt(replyIDStr);
                boolean isDeleted = ReplyDAO.INS.deleteReply(replyID);

                if (isDeleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

}
