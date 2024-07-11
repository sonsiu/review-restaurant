/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.Follow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mapper.EateryMapper;
import mapper.FollowMapper;

/**
 *
 * @author legion
 */
public class FollowDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static FollowDAO INS = new FollowDAO();

    public FollowDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public ArrayList<Follow> getListBySearch(String txt) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Follow> list = new ArrayList<>();
        String sql = "SELECT * FROM eatery WHERE eatery_name LIKE ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Follow follow = FollowMapper.getInstance().map(rs);
                list.add(follow);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return list;
    }

    public void setRealtionship(String follower, String following, String action) {
        String sql = null;
        switch (action) {
            case "follow" ->
                sql = "INSERT INTO `follow`\n"
                        + "(`follower_user_id`,\n"
                        + "`following_user_id`)\n"
                        + "VALUES\n"
                        + "(?,?);";
            case "unfollow" ->
                sql = "DELETE FROM `follow`\n"
                        + "WHERE follower_user_id = ? and following_user_id = ?;";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, follower);
            ps.setString(2, following);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean isFollowing(String follower, String following) {
        String sql = "SELECT 1 FROM follow WHERE follower_user_id = ? and following_user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, follower);
            ps.setString(2, following);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public int countFollow(String userID, String action) {
        String sql = null;
        int total = 0;
        switch (action) {
            case "following" ->
                sql = "SELECT COUNT(following_user_id) AS count  FROM follow WHERE follower_user_id = ?; ";
                
            case "follower" ->
                sql = "SELECT COUNT(follower_user_id) AS count  FROM follow WHERE following_user_id = ?; ";
        }
         try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return total;
    }
}
