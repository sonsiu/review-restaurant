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
public class CommentLikeDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static CommentLikeDAO INS = new CommentLikeDAO();

    public CommentLikeDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public boolean addLike(int commentID, String userID) {
        String insertLikeSQL = "INSERT INTO comment_like (comment_id, user_id) VALUES (?, ?)";
        String updateCommentLikeSQL = "UPDATE comment SET comment_like = comment_like + 1 WHERE comment_id = ?";
        try (PreparedStatement psInsert = con.prepareStatement(insertLikeSQL);
             PreparedStatement psUpdate = con.prepareStatement(updateCommentLikeSQL)) {
            psInsert.setInt(1, commentID);
            psInsert.setString(2, userID);
            psInsert.executeUpdate();

            psUpdate.setInt(1, commentID);
            psUpdate.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public int getLikeCount(int commentID) {
        String sql = "SELECT COUNT(*) AS like_count FROM comment_like WHERE comment_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, commentID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("like_count");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean hasUserLikedComment(int commentID, String userID) {
        String sql = "SELECT COUNT(*) AS like_count FROM comment_like WHERE comment_id = ? AND user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, commentID);
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

    public boolean removeLike(int commentID, String userID) {
        String deleteLikeSQL = "DELETE FROM comment_like WHERE comment_id = ? AND user_id = ?";
        String updateCommentLikeSQL = "UPDATE comment SET comment_like = comment_like - 1 WHERE comment_id = ?";
        try (PreparedStatement psDelete = con.prepareStatement(deleteLikeSQL);
             PreparedStatement psUpdate = con.prepareStatement(updateCommentLikeSQL)) {
            psDelete.setInt(1, commentID);
            psDelete.setString(2, userID);
            int rowsAffected = psDelete.executeUpdate();

            if (rowsAffected > 0) {
                psUpdate.setInt(1, commentID);
                psUpdate.executeUpdate();
            }

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public boolean removeLikeByCommentID(int commentID) {
        String sql = "DELETE FROM comment_like WHERE comment_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, commentID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}
