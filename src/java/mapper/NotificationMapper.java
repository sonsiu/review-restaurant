/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.Notification;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class NotificationMapper {
    
    private static NotificationMapper instance;

    private NotificationMapper() {
    }

    public static NotificationMapper getInstance() {
        if (instance == null) {
            instance = new NotificationMapper();
        }
        return instance;
    }

    public Notification getNotification(ResultSet rs) throws SQLException {
        return Notification.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .message(rs.getString("message"))
                .type(rs.getString("type"))
                .userId(rs.getString("send_to_user_id"))
                .timestamp(rs.getTimestamp("notification_date"))
                .blogId(rs.getInt("blog_id"))
                .build();
    }
}
