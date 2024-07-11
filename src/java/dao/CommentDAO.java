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
import mapper.CommentMapper;

/**
 *
 * @author ACER
 */
public class CommentDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static CommentDAO INS = new CommentDAO();

    public CommentDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public int getStaticComment() {
        int value = 0;

        String sql = " select count(*) from comment";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return value;
    }

    public boolean createComment(String content, String userID, int blogID) {
        String sql = "INSERT INTO `comment` (\n"
                + "`content`,\n"
                + "`comment_status`,\n"
                + "`user_id`,\n"
                + "`blog_id`,\n"
                + "`comment_like`\n"
                + ") VALUES\n"
                + "(?, 1, ?, ?, 0);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, content);
            ps.setString(2, userID);
            ps.setInt(3, blogID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public List<Comment> getCommentsByBlogID(int blogID) {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT c.*, u.user_image AS userPicture, u.display_name AS userName, "
                + "(SELECT COUNT(*) FROM comment_like cl WHERE cl.comment_id = c.comment_id) AS commentLike "
                + "FROM comment c "
                + "JOIN user u ON c.user_id = u.user_id "
                + "WHERE c.blog_id = ? "
                + "ORDER BY c.comment_date DESC;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = CommentMapper.getInstance().map(rs);

                List<Reply> replies = ReplyDAO.INS.getRepliesByCommentID(comment.getCommentID());
                comment.setReplies(replies);
                list.add(comment);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public boolean deleteComment(int commentID) {
        String deleteLikesSQL = "DELETE FROM comment_like WHERE comment_id = ?";
        String deleteCommentSQL = "DELETE FROM comment WHERE comment_id = ?";

        try (PreparedStatement deleteLikesPS = con.prepareStatement(deleteLikesSQL); PreparedStatement deleteCommentPS = con.prepareStatement(deleteCommentSQL)) {

            con.setAutoCommit(false);

            if (!ReplyDAO.INS.deleteRepliesByCommentID(commentID)) {
                con.rollback();
                con.setAutoCommit(true);
                return false;
            }

            deleteLikesPS.setInt(1, commentID);
            deleteLikesPS.executeUpdate();

            deleteCommentPS.setInt(1, commentID);
            int rowsAffected = deleteCommentPS.executeUpdate();

            con.commit();
            con.setAutoCommit(true);

            if (rowsAffected > 0) {
                System.out.println("Successfully deleted comment with ID: " + commentID);
                return true;
            } else {
                System.out.println("No comment found with ID: " + commentID);
                return false;
            }

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

    public Comment getLatestCommentByUserAndBlog(String userID, int blogID) {
        String sql = "SELECT c.*, u.user_image AS userPicture, u.display_name AS userName "
                + "FROM comment c "
                + "JOIN user u ON c.user_id = u.user_id "
                + "WHERE c.user_id = ? AND c.blog_id = ? "
                + "ORDER BY c.comment_date DESC "
                + "LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ps.setInt(2, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Comment.builder()
                        .commentID(rs.getInt("comment_id"))
                        .content(rs.getString("content"))
                        .userID(rs.getString("user_id"))
                        .blogID(rs.getInt("blog_id"))
                        .commentLike(rs.getInt("comment_like"))
                        .commentDate(rs.getTimestamp("comment_date"))
                        .userPicture(Base64.getEncoder().encodeToString(rs.getBytes("userPicture"))) // Set user picture
                        .userName(rs.getString("userName")) // Set user name
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int countCommentsByBlogID(int blogID) {
        String sql = "SELECT COUNT(*) AS commentCount FROM comment WHERE blog_id = ?";
        int commentCount = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                commentCount = rs.getInt("commentCount");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return commentCount;
    }
//    public boolean editComment(int commentID, String newContent) {
//        String sql = "UPDATE comment SET content = ? WHERE comment_id = ?";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setString(1, newContent);
//            ps.setInt(2, commentID);
//            int rowsUpdated = ps.executeUpdate();
//            return rowsUpdated > 0;
//        } catch (SQLException e) {
//            e.printStackTrace(System.out);
//            return false;
//        }
//    }

    public static void main(String[] args) {
        // Assuming blogID 1 exists in your database
        int blogID = 1;

        CommentDAO commentDAO = CommentDAO.INS;
        List<Comment> comments = commentDAO.getCommentsByBlogID(blogID);

        if (comments != null) {
            for (Comment comment : comments) {
                System.out.println("Comment ID: " + comment.getCommentID());
                System.out.println("Content: " + comment.getContent());
                System.out.println("User ID: " + comment.getUserID());
                System.out.println("Blog ID: " + comment.getBlogID());
                System.out.println("Comment Like: " + comment.getCommentLike());
                System.out.println("Comment Date: " + comment.getCommentDate());
                System.out.println("User Picture: " + comment.getUserPicture());
                System.out.println("User Name: " + comment.getUserName());

                List<Reply> replies = comment.getReplies();
                if (replies != null) {
                    for (Reply reply : replies) {
                        System.out.println("Reply ID: " + reply.getReplyID());
                        System.out.println("Content: " + reply.getContent());
                        System.out.println("User ID: " + reply.getUserID());
                        System.out.println("Comment ID: " + reply.getCommentID());
                        System.out.println("Reply Like: " + reply.getReplyLike());
                        System.out.println("Reply Date: " + reply.getReplyDate());
                        System.out.println("User Name: " + reply.getUserName());
                    }
                }
            }
        } else {
            System.out.println("No comments found for blog ID: " + blogID);
        }
    }
}
