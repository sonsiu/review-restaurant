/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
//import entity.Notification;
import entity.GlobalMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.GlobalMessageMapper;

/**
 *
 * @author Vinh
 */
public class GlobalMessageDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static GlobalMessageDAO INS = new GlobalMessageDAO();

    public GlobalMessageDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public void insertNotification(String title, String message, String type, boolean show, String userId) {
        String sql = "INSERT INTO moderator_global_message (title, message, type, `is_show`, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, message);
            ps.setString(3, type);
            ps.setBoolean(4, true); // Sử dụng giá trị show được truyền vào
            ps.setString(5, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức lấy thông báo bằng ID
    public GlobalMessage getNotificationById(int id) {
        String sql = "SELECT * FROM moderator_global_message where id =?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return GlobalMessageMapper.getInstance().map(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GlobalMessage> getNotificationsByUserId(String userId) {
        List<GlobalMessage> notifications = new ArrayList<>();
        String sql = "SELECT * FROM moderator_global_message WHERE user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GlobalMessage notification = GlobalMessageMapper.getInstance().map(rs);
                    notifications.add(notification);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public List<GlobalMessage> getLatestNotifications(String userId) {
        List<GlobalMessage> notifications = new ArrayList<>();
        String sql = "SELECT * FROM moderator_global_message WHERE is_show = 1 ORDER BY id DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GlobalMessage notification = GlobalMessageMapper.getInstance().map(rs);
                    notifications.add(notification);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public List<GlobalMessage> getAllNotifications() {
        List<GlobalMessage> notifications = new ArrayList<>();
        String sql = "SELECT * FROM moderator_global_message";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GlobalMessage notification = GlobalMessageMapper.getInstance().map(rs);
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    public void deleteNotification(int id) {
        String sql = "UPDATE moderator_global_message SET is_show = 0 WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GlobalMessage getLatestNotificationByUserId(String userId) {
        String sql = "SELECT * FROM moderator_global_message WHERE user_id = ? AND is_show = 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return GlobalMessageMapper.getInstance().map(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<GlobalMessage> getGlobalMessages() {
        List<GlobalMessage> notifications = new ArrayList<>();
        String sql = "SELECT * FROM moderator_global_message WHERE is_show = 1";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                notifications.add(GlobalMessageMapper.getInstance().map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

//    public Notification getActiveNotificationByUserId(String userId) {
//        String sql = "SELECT * FROM notifications WHERE user_id = ? AND is_show = 1";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setString(1, userId);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return NotificationMapper.getInstance().map(rs);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void updateIsShow(int id, boolean isShow) {
        String sql = "UPDATE moderator_global_message SET is_show = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, isShow);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   

}
