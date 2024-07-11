/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.UserSetting;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class UserSettingMapper {
    
    private static UserSettingMapper INS;

    private UserSettingMapper() {
    }

    public static UserSettingMapper getInstance() {
        if (INS == null) {
            INS = new UserSettingMapper();
        }
        return INS;
    }
    
    public UserSetting getUserSetting(ResultSet rs) throws SQLException {
        return UserSetting.builder()
                .userId(rs.getString("user_id"))
                .twoFactor(rs.getBoolean("is_2fa"))
                .rememberMe(rs.getBoolean("is_remember_me"))
                .build();
    }
}
