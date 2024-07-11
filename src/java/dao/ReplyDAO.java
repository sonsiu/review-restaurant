/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;

import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import mapper.ReplyMapper;

/**
 *
 * @author ACER
 */
public class ReplyDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static ReplyDAO INS = new ReplyDAO();

    public ReplyDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public boolean createReply(String content, String userID, int commentID) {
        String sql = "INSERT INTO `reply` (\n"
                + "`content`,\n"
                + "`reply_status`,\n"
                + "`user_id`,\n"
                + "`comment_id`,\n"
                + "`reply_like`\n"
                + ") VALUES\n"
                + "(?, 1, ?, ?, 0);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, content);
            ps.setString(2, userID);
            ps.setInt(3, commentID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public List<Reply> getRepliesByCommentID(int commentID) {
        List<Reply> list = new ArrayList<>();
        String sql = "SELECT r.*, u.user_image AS userPicture, u.display_name AS userName, "
                + "(SELECT COUNT(*) FROM reply_like rl WHERE rl.reply_id = r.reply_id) AS replyLike "
                + "FROM reply r "
                + "JOIN user u ON r.user_id = u.user_id "
                + "WHERE r.comment_id = ? "
                + "ORDER BY r.reply_date DESC;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, commentID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reply reply = ReplyMapper.getInstance().map(rs);
                list.add(reply);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public boolean deleteReply(int replyID) {
        String deleteLikesSQL = "DELETE FROM reply_like WHERE reply_id = ?";
        String deleteReplySQL = "DELETE FROM reply WHERE reply_id = ?";

        try (PreparedStatement deleteLikesPS = connection.prepareStatement(deleteLikesSQL); PreparedStatement deleteReplyPS = connection.prepareStatement(deleteReplySQL)) {

            connection.setAutoCommit(false);

            deleteLikesPS.setInt(1, replyID);
            deleteLikesPS.executeUpdate();

            deleteReplyPS.setInt(1, replyID);
            int rowsAffected = deleteReplyPS.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            if (rowsAffected > 0) {
                System.out.println("Successfully deleted reply with ID: " + replyID);
                return true;
            } else {
                System.out.println("No reply found with ID: " + replyID);
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            try {
                // Rollback transaction in case of error
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(System.out);
            }
            return false;
        }
    }

    public boolean deleteRepliesByCommentID(int commentID) {
        String deleteLikesSQL = "DELETE FROM reply_like WHERE reply_id IN (SELECT reply_id FROM reply WHERE comment_id = ?)";
        String deleteRepliesSQL = "DELETE FROM reply WHERE comment_id = ?";

        try (PreparedStatement deleteLikesPS = con.prepareStatement(deleteLikesSQL); PreparedStatement deleteRepliesPS = con.prepareStatement(deleteRepliesSQL)) {

            con.setAutoCommit(false);

            deleteLikesPS.setInt(1, commentID);
            deleteLikesPS.executeUpdate();

            deleteRepliesPS.setInt(1, commentID);
            deleteRepliesPS.executeUpdate();

            con.commit();
            con.setAutoCommit(true);

            return true;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace(System.out);
            }
            return false;
        }
    }

    public Reply getLatestReplyByUserAndComment(String userID, int commentID) {
        String sql = "SELECT r.*, u.user_image AS userPicture, u.display_name AS userName "
                + "FROM reply r "
                + "JOIN user u ON r.user_id = u.user_id "
                + "WHERE r.user_id = ? AND r.comment_id = ? "
                + "ORDER BY r.reply_date DESC "
                + "LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ps.setInt(2, commentID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Reply.builder()
                        .replyID(rs.getInt("reply_id"))
                        .content(rs.getString("content"))
                        .replyStatus(rs.getInt("reply_status"))
                        .userID(rs.getString("user_id"))
                        .commentID(rs.getInt("comment_id"))
                        .replyLike(rs.getInt("reply_like"))
                        .replyDate(rs.getTimestamp("reply_date"))
                        .userPicture(Base64.getEncoder().encodeToString(rs.getBytes("userPicture"))) // Set user picture
                        .userName(rs.getString("userName")) // Set user name
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int countRepliesByBlogID(int blogID) {
        String sql = "SELECT COUNT(*) AS replyCount FROM reply r JOIN comment c ON r.comment_id = c.comment_id WHERE c.blog_id = ?";
        int replyCount = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                replyCount = rs.getInt("replyCount");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return replyCount;
    }

    public static void main(String[] args) {
        // Test data
        String content = "Test reply content check func";
        String userID = "haidanght291003";
        int commentID = 75; // Example comment ID

        // Create an instance of ReplyDAO
        ReplyDAO replyDAO = new ReplyDAO();

        // Call the method to create a reply
        boolean replyCreated = replyDAO.createReply(content, userID, commentID);

        // Check if the reply was created successfully
        if (replyCreated) {
            System.out.println("Reply created successfully.");

            // Optional: Retrieve the latest reply to verify
            Reply latestReply = replyDAO.getLatestReplyByUserAndComment(userID, commentID);
            if (latestReply != null) {
                // Print out the details of the latest reply
                System.out.println("Latest Reply:");
                System.out.println("Reply ID: " + latestReply.getReplyID());
                System.out.println("Content: " + latestReply.getContent());
                System.out.println("User ID: " + latestReply.getUserID());
                System.out.println("Comment ID: " + latestReply.getCommentID());
                System.out.println("Reply Date: " + latestReply.getReplyDate());
                System.out.println("User Name: " + latestReply.getUserName());
                System.out.println("User Picture: " + latestReply.getUserPicture());
                System.out.println("Reply Like: " + latestReply.getReplyLike());
                System.out.println("Reply Status: " + latestReply.getReplyStatus());
            } else {
                System.out.println("No latest reply found for the given user and comment.");
            }
        } else {
            System.out.println("Failed to create reply.");
        }
    }
}
