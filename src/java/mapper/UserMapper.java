/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import service.Service;

/**
 *
 * @author legion
 */
public class UserMapper {

    private static UserMapper INS;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (INS == null) {
            INS = new UserMapper();
        }
        return INS;
    }

    public User getUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getString("user_id"))
                .displayName(rs.getString("display_name"))
                .username(rs.getString("username"))
                .passsword(rs.getString("password"))
                .profilePicture(Base64.getEncoder().encodeToString(rs.getBytes("user_image")))
                .email(rs.getString("user_email"))
                .role(rs.getInt("role_id"))
                .status(rs.getInt("user_status"))
                .verifyCode(rs.getString("verify_code"))
                .expDate(rs.getTimestamp("expire_date"))
                .loginBy(rs.getInt("login_by"))
                .phone(rs.getString("user_phone"))
                .location(rs.getString("location"))
                .build();
    }

    public void updateUser(PreparedStatement ps, String email, String username, String password, String id) throws SQLException {
        ps.setString(1, Service.extractUsername(email));
        ps.setString(2, username);
        ps.setString(3, Service.getMd5(password));
        ps.setString(4, email);
        ps.setString(5, Service.genRandSixDigit());
        ps.setTimestamp(6, Service.getNowAfter10Minutes());
        ps.setString(7, id);
    }
}
