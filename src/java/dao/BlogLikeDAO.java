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
public class BlogLikeDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static BlogLikeDAO INS = new BlogLikeDAO();

    public BlogLikeDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public boolean addLike(int blogID, String userID) {
        String insertLikeSQL = "INSERT INTO blog_like (blog_id, user_id) VALUES (?, ?)";
        String updateBlogLikeSQL = "UPDATE blog SET blog_like = blog_like + 1 WHERE blog_id = ?";
        try (PreparedStatement psInsert = con.prepareStatement(insertLikeSQL); PreparedStatement psUpdate = con.prepareStatement(updateBlogLikeSQL)) {
            psInsert.setInt(1, blogID);
            psInsert.setString(2, userID);
            psInsert.executeUpdate();

            psUpdate.setInt(1, blogID);
            psUpdate.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public int getLikeCount(int blogID) {
        String sql = "SELECT COUNT(*) AS like_count FROM blog_like WHERE blog_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("like_count");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public boolean hasUserLikedBlog(int blogID, String userID) {
        String sql = "SELECT COUNT(*) AS like_count FROM blog_like WHERE blog_id = ? AND user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogID);
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

    public boolean removeLike(int blogID, String userID) {
        String deleteLikeSQL = "DELETE FROM blog_like WHERE blog_id = ? AND user_id = ?";
        String updateBlogLikeSQL = "UPDATE blog SET blog_like = blog_like - 1 WHERE blog_id = ?";
        try (PreparedStatement psDelete = con.prepareStatement(deleteLikeSQL); PreparedStatement psUpdate = con.prepareStatement(updateBlogLikeSQL)) {
            psDelete.setInt(1, blogID);
            psDelete.setString(2, userID);
            int rowsAffected = psDelete.executeUpdate();

            psUpdate.setInt(1, blogID);
            psUpdate.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public boolean removeLikeByBlogID(int blogID) {
        String sql = "DELETE FROM blog_like WHERE blog_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

}
