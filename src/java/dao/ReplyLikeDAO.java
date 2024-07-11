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
import java.util.List;

/**
 *
 * @author ACER
 */
public class ReplyLikeDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static ReplyLikeDAO INS = new ReplyLikeDAO();

    public ReplyLikeDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

   public boolean addLike(int replyID, String userID) {
        String insertLikeSQL = "INSERT INTO reply_like (reply_id, user_id) VALUES (?, ?)";
        String updateReplyLikeSQL = "UPDATE reply SET reply_like = reply_like + 1 WHERE reply_id = ?";
        try (PreparedStatement psInsert = con.prepareStatement(insertLikeSQL);
             PreparedStatement psUpdate = con.prepareStatement(updateReplyLikeSQL)) {
            psInsert.setInt(1, replyID);
            psInsert.setString(2, userID);
            psInsert.executeUpdate();

            psUpdate.setInt(1, replyID);
            psUpdate.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public int getLikeCount(int replyID) {
        String sql = "SELECT COUNT(*) AS like_count FROM reply_like WHERE reply_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, replyID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("like_count");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean hasUserLikedReply(int replyID, String userID) {
        String sql = "SELECT COUNT(*) AS like_count FROM reply_like WHERE reply_id = ? AND user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, replyID);
            ps.setString(2, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("like_count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public boolean removeLike(int replyID, String userID) {
        String deleteLikeSQL = "DELETE FROM reply_like WHERE reply_id = ? AND user_id = ?";
        String updateReplyLikeSQL = "UPDATE reply SET reply_like = reply_like - 1 WHERE reply_id = ?";
        try (PreparedStatement psDelete = con.prepareStatement(deleteLikeSQL);
             PreparedStatement psUpdate = con.prepareStatement(updateReplyLikeSQL)) {
            psDelete.setInt(1, replyID);
            psDelete.setString(2, userID);
            int rowsAffected = psDelete.executeUpdate();

            if (rowsAffected > 0) {
                psUpdate.setInt(1, replyID);
                psUpdate.executeUpdate();
            }

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public boolean removeLikeByReplyID(int replyID) {
        String sql = "DELETE FROM reply_like WHERE reply_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, replyID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

}
