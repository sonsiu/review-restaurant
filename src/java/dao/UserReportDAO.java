/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinh
 */
public class UserReportDAO extends DBContext{
    
    private Connection con;
    private String status = "OK";
    public static UserReportDAO INS = new UserReportDAO();

    public UserReportDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }
    
    public ArrayList<String> getAllReporterIds(String userID){
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT user_reporter_id FROM user_report WHERE user_reported_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("user_reporter_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
  public List<User> getReportersByUserID(String userId) {
    List<User> reporters = new ArrayList<>();
    String sql = "SELECT user_reporter_id FROM user_report WHERE user_id = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, userId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            User reporter = UserDAO.INS.findUserById(rs.getString("user_reporter_id"));
            if (reporter != null) {
                reporters.add(reporter);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    }
    return reporters;
}

     public List<String> getReportReasonsByUserId(String userId) {
        List<String> reportReasons = new ArrayList<>();
        String sql = "SELECT urr.user_reason_content " +
                     "FROM user_report ur " +
                     "JOIN user_report_reason urr ON ur.report_reason_id = urr.user_reason_id " +
                     "WHERE ur.user_id = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                reportReasons.add(rs.getString("user_reason_content"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return reportReasons;
    }

   public int getReportCountByUserId(String userId) {
    int reportCount = 0;
    String sql = "SELECT COUNT(*) AS total_reports FROM user_report WHERE user_id = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, userId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            reportCount = rs.getInt("total_reports");
        }
    } catch (SQLException e) {
        e.printStackTrace(System.out);
    }
    
    return reportCount;
}

}
