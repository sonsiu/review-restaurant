/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.NotificationMapper;

public class NotificationDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static NotificationDAO INS = new NotificationDAO();

    public NotificationDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public void makeNotification(String title, String message, String type, String sendToUserId, int blogId) {
        String sql = "INSERT INTO `notification` (`title`, `message`, `type`, `send_to_user_id`, `blog_id`) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, message);
            ps.setString(3, type);
            ps.setString(4, sendToUserId);
            ps.setInt(5, blogId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getNotificationByUserId(String userId) {
        List<Notification> list = new ArrayList();
        String sql = "SELECT * FROM `notification` WHERE `send_to_user_id` = ? ORDER BY notification_date DESC LIMIT 5 ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(NotificationMapper.getInstance().getNotification(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Notification> getAllNotificationByUserId(String userId) {
        ArrayList<Notification> list = new ArrayList();
        String sql = "SELECT * FROM `notification` WHERE `send_to_user_id` = ? ORDER BY notification_date DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(NotificationMapper.getInstance().getNotification(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Notification getNotificationById(int notifId) {
        String sql = "SELECT * FROM notification WHERE id = ?";
        Notification notif = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, notifId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                notif = NotificationMapper.getInstance().getNotification(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return notif;
    }
    
    public ArrayList<Notification> getNLastDayNotificationByUserId(int dayBefore, String userId) {
        ArrayList<Notification> list = new ArrayList();
        String sql = "SELECT * FROM notification where date(notification_date) BETWEEN (CURDATE() - INTERVAL ? DAY) AND CURDATE() AND `send_to_user_id` = ? ORDER BY notification_date DESC";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dayBefore);
            ps.setString(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(NotificationMapper.getInstance().getNotification(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
