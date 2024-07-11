/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.UserSetting;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.UserSettingMapper;

/**
 *
 * @author ADMIN
 */
public class UserSettingDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static UserSettingDAO INS = new UserSettingDAO();

    public UserSettingDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public void makeNewUserSetting(String userId) {
        String sql = "INSERT INTO `user_setting` (`user_id`) VALUES (?)";
         try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userId);
            
            ps.execute();
        } catch (SQLException e) {
        }
    }
    
    public void updateRememberMe(boolean status, String userId){
        String sql = "UPDATE `user_setting` SET `is_remember_me` = ? WHERE (`user_id` = ?);";
         try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, userId);
            
            ps.execute();
        } catch (SQLException e) {
        }
    }
    
    public UserSetting findSettingByUserId(String userId){
        UserSetting setting = new UserSetting();
        String sql = "SELECT * FROM `user_setting` WHERE `user_id` = ?";
        
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return UserSettingMapper.getInstance().getUserSetting(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setting;
    }
}
