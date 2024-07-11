/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

//import entity.Notification;
import entity.GlobalMessage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GlobalMessageMapper {

    private static GlobalMessageMapper instance;

    private GlobalMessageMapper() {
    }

    public static GlobalMessageMapper getInstance() {
        if (instance == null) {
            instance = new GlobalMessageMapper();
        }
        return instance;
    }

    public GlobalMessage map(ResultSet rs) throws SQLException {
        return GlobalMessage.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .message(rs.getString("message"))
                .type(rs.getString("type"))
                .is_show(rs.getBoolean("is_show"))
                .userId(rs.getString("user_id"))
                .date(rs.getDate("notification_date"))
                .build();
    }
}
