/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;
import entity.UserGoogle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import service.*;
import mapper.UserMapper;

/**
 *
 * @author sontvhe186422
 */
public class UserDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static UserDAO INS = new UserDAO();

    public UserDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public int getStaticCustomers() {
        int value = 0;

        String sql = " select count(*) from user where role_id =2 ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return value;
    }
     public int getStaticMod() {
        int value = 0;

        String sql = " select count(*) from user where role_id =1 ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                value = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return value;
    }

    public ArrayList<User> get5LasterUser() {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM `user` WHERE role_id = 2 order by user_id desc limit 5";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(UserMapper.getInstance().getUser(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public ArrayList<User> get5LasterMod() {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM `user` WHERE role_id = 1 order by user_id desc limit 5";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(UserMapper.getInstance().getUser(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User authenticate(String username, String password) {
        String sql = "select * from `user` where `username` = ? and `password` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, Service.getMd5(password));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return UserMapper.getInstance().getUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getAll() {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM `user`";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(UserMapper.getInstance().getUser(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<User> getAllNormalUsers() {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM `user` WHERE role_id = 2";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(UserMapper.getInstance().getUser(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<User> getAllNormalModerators() {
        ArrayList<User> list = new ArrayList();
        String sql = "SELECT * FROM `user` WHERE role_id = 1";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(UserMapper.getInstance().getUser(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<User> searchNormalUser(String displayName) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM `user` WHERE `display_name` LIKE ? and role_id = 2";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + displayName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = UserMapper.getInstance().getUser(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return userList;
    }

    public ArrayList<User> searchNormalModerators(String displayName) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM `user` WHERE `display_name` LIKE ? and role_id = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + displayName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = UserMapper.getInstance().getUser(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return userList;
    }

    public ArrayList<User> searchNormalUserByStatus(String displayName, String status, String action) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "";
        switch (status) {
            case "activated":
                if (action.equals("all")) {
                    sql = "SELECT * FROM `user` WHERE role_id = 2 AND user_status IN (1,3)";
                } else if (action.equals("search")) {
                    sql = "SELECT * FROM `user` WHERE `display_name` LIKE ? AND role_id = 2 AND user_status IN (1,3)";
                }
                break;
            case "deactivated":
                if (action.equals("all")) {
                    sql = "SELECT * FROM `user` WHERE user_status IN (0,2)";
                } else if (action.equals("search")) {
                    sql = "SELECT * FROM `user` WHERE `display_name` LIKE ? AND role_id = 2 AND user_status IN (0,2)";
                }
                break;
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (action.equals("search")) {
                ps.setString(1, "%" + displayName + "%");
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = UserMapper.getInstance().getUser(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return userList;
    }

    public ArrayList<User> searchNormalModByStatus(String displayName, String status, String action) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "";
        switch (status) {
            case "activated":
                if (action.equals("all")) {
                    sql = "SELECT * FROM `user` WHERE role_id = 1 AND user_status IN (1,3)";
                } else if (action.equals("search")) {
                    sql = "SELECT * FROM `user` WHERE `display_name` LIKE ? AND role_id = 1 AND user_status IN (1,3)";
                }
                break;
            case "deactivated":
                if (action.equals("all")) {
                    sql = "SELECT * FROM `user` WHERE user_status == 2";
                } else if (action.equals("search")) {
                    sql = "SELECT * FROM `user` WHERE `display_name` LIKE ? AND role_id = 1 AND user_status == 2";
                }
                break;
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (action.equals("search")) {
                ps.setString(1, "%" + displayName + "%");
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = UserMapper.getInstance().getUser(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return userList;
    }

    public User findUserById(String userID) {
        String sql = "select * from `user` where `user_id` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return UserMapper.getInstance().getUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<User> searchUserByDisplayName(String displayName) {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM `user` WHERE `display_name` LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + displayName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = UserMapper.getInstance().getUser(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return userList;
    }
//--------------------REDUNDANT------------------------------------------------------------------------------------------------------------------------

    public User findUserByEmail(String email) {
        String sql = "select * from `user` where `user_email` = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return UserMapper.getInstance().getUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User findUserByUsername(String username) {
        String sql = "select * from `user` where `username` = ?;";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return UserMapper.getInstance().getUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//--------------------REDUNDANT------------------------------------------------------------------------------------------------------------------------

    public boolean checkEmailExist(String email) {
        String sql = "select * from `user` where `user_email` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkUsernameExist(String username) {
        String sql = "select * from `user` where `username` = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void signUp(String username, String password, String imagePath, String email, String verifyCode, Timestamp expDate) throws FileNotFoundException {
        File imageFile = new File(imagePath);
        FileInputStream inputStream = new FileInputStream(imageFile);
        int loginBy = 0;
        String sql = "INSERT INTO `user` (`user_id`, `display_name`, `username`, `password`,`user_image`, `user_email`, `role_id`, `user_status`, `verify_code`, `expire_date`, `login_by`) "
                + "VALUES (?, ?, ?, ?, ?, ?, '2', '0', ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Service.extractUsername(email));
            ps.setString(2, Service.extractUsername(email));
            ps.setString(3, username);
            ps.setString(4, Service.getMd5(password));
            ps.setBlob(5, inputStream);
            ps.setString(6, email);
            ps.setString(7, verifyCode);
            ps.setTimestamp(8, expDate);
            ps.setInt(9, loginBy);

            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void signUpByGmail(UserGoogle userGoogle, String imagePath) throws FileNotFoundException {
        File imageFile = new File(imagePath);
        FileInputStream inputStream = new FileInputStream(imageFile);
        int loginBy = 1;
        String sql = "INSERT INTO `user` (`user_id`, `display_name`, `username`, `user_image`, `user_email`, `role_id`, `user_status`, `login_by`) "
                + "VALUES (?, ?, ?, ?, ?, 2, 1, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Service.extractUsername(userGoogle.getEmail()));
            ps.setString(2, Service.extractUsername(userGoogle.getEmail()));
            ps.setString(3, Service.extractUsername(userGoogle.getEmail()));
            ps.setBlob(4, inputStream);
            ps.setString(5, userGoogle.getEmail());
            ps.setInt(6, loginBy);

            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void updateVerifyCode(String verifyCode, Timestamp expDate, String email) {
        String sql = "UPDATE `user` SET `verify_code` = ?, `expire_date` = ? WHERE (`user_email` = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, verifyCode);
            ps.setTimestamp(2, expDate);
            ps.setString(3, email);

            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void updatePassword(String password, String email) {
        String sql = "UPDATE `user` SET `password` = ? WHERE (`user_email` = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Service.getMd5(password));
            ps.setString(2, email);

            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void updateStatus(int status, String id) {
        String sql = "UPDATE `user` SET `user_status` = ? WHERE (`user_id` = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, id);

            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateUser(String id, String displayname, InputStream image, String location, String phone) {
        String sql = "UPDATE `user`\n"
                + "SET\n"
                + "`display_name` = ?,\n"
                + "`user_phone` = ?,\n"
                + "`user_image` = ?,\n"
                + "`location` = ?\n"
                + "WHERE `user_id` = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, displayname);
            ps.setString(2, phone);
            ps.setBlob(3, image);
            ps.setString(4, location);
            ps.setString(5, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void updateAtEmail(String username, String password, String email) {
        String sql = "UPDATE `user` SET `display_name` = ?, `username` = ?, `password` = ?, `user_email` = ?, `verify_code` = ?, `expire_date` = ? WHERE (`user_id` = ?)";
        String id = findUserByEmail(email).getId();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            UserMapper.getInstance().updateUser(ps, email, username, password, id);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void updateAtUsername(String username, String password, String email) {
        String sql = "UPDATE `user` SET `display_name` = ?, `username` = ?, `password` = ?, `user_email` = ?, `verify_code` = ?, `expire_date` = ? WHERE (`user_id` = ?)";
        String id = findUserByUsername(username).getId();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            UserMapper.getInstance().updateUser(ps, email, username, password, id);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public User findUserByBlogID(int blogID) {
        String sql = "SELECT * FROM blog b\n"
                + "JOIN user u ON u.user_id  = b.user_id where blog_id=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, blogID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return UserMapper.getInstance().getUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public User findUserByNotificationID(int notifId) {
//        String sql = "SELECT * FROM notification n JOIN user u ON u.user_id  = n.send_to_user_id where id = ?";
//        try {
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, notifId);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                return UserMapper.getInstance().getUser(rs);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public ArrayList<User> getListOfFollow(String userID, String action) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "";
        switch (action) {
            case "follower":
                sql = "SELECT * FROM user u JOIN follow f \n"
                        + "ON u.user_id = f.follower_user_id\n"
                        + "WHERE f.following_user_id = ?";
                break;

            case "following":
                sql = "SELECT * FROM user u JOIN follow f \n"
                        + "ON u.user_id = f.following_user_id\n"
                        + "WHERE f.follower_user_id = ?";
                break;
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = UserMapper.getInstance().getUser(rs);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return userList;
    }

    public List<String> getAllReportReasons() {
        List<String> reportReasons = new ArrayList<>();
        String sql = "SELECT user_reason_content FROM user_report_reason";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reportReasons.add(rs.getString("user_reason_content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportReasons;
    }

    public boolean hasUserReportedUser(String reporterId, String reportedUserId) {
        String sql = "SELECT * FROM user_report WHERE user_reporter_id = ? AND user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reporterId);
            ps.setString(2, reportedUserId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int reportReasonId = rs.getInt("report_reason_id");
                // Check if the reported user is currently banned (status == 3)
                User reportedUser = findUserById(reportedUserId); // Assuming findUserById retrieves User object
                if (reportedUser != null && reportedUser.getStatus() == 3) {
                    return true; // Allow reporting again if user is currently banned
                }
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addReportReason(String reportReason) throws SQLException {
        String insertSql = "INSERT INTO user_report_reason (user_reason_content) VALUES (?)";
        String selectSql = "SELECT user_reason_id FROM user_report_reason WHERE user_reason_content = ?";
        try (PreparedStatement insertPs = connection.prepareStatement(insertSql); PreparedStatement selectPs = connection.prepareStatement(selectSql)) {
            insertPs.setString(1, reportReason);
            insertPs.executeUpdate();

            selectPs.setString(1, reportReason);
            ResultSet rs = selectPs.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_reason_id");
            } else {
                throw new SQLException("Failed to retrieve new report reason ID");
            }
        }
    }

    public int getReportReasonID(String reportReason) throws SQLException {
        String sql = "SELECT user_reason_id FROM user_report_reason WHERE user_reason_content = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reportReason);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_reason_id");
            } else {
                throw new SQLException("Report reason not found");
            }
        }
    }

    public void reportUser(String userId, int reportReasonID, String userReporterId) throws SQLException {
        String insertSql = "INSERT INTO user_report (user_id, report_reason_id, user_reporter_id) VALUES (?, ?, ?)";
        String updateSql = "UPDATE user SET user_status = 3 WHERE user_id = ?";

        try (PreparedStatement insertPs = connection.prepareStatement(insertSql); PreparedStatement updatePs = connection.prepareStatement(updateSql)) {

            connection.setAutoCommit(false); // Start transaction

            // Insert the report
            insertPs.setString(1, userId);
            insertPs.setInt(2, reportReasonID);
            insertPs.setString(3, userReporterId);

            int insertCount = insertPs.executeUpdate();
            System.out.println("Inserted " + insertCount + " rows into user_report");

            // Update the user status
            updatePs.setString(1, userId);
            int updateCount = updatePs.executeUpdate();
            System.out.println("Updated " + updateCount + " rows in user");

            connection.commit(); // Commit transaction
            System.out.println("Transaction committed successfully.");
        } catch (SQLException e) {
            connection.rollback(); // Rollback transaction on error
            System.err.println("Transaction rolled back due to an error.");
            throw e;
        }
    }

    public void handleUserReport(String userId, int status) throws SQLException {
        String updateUserSql = "UPDATE user SET user_status = ? WHERE user_id = ?";
        String deleteReportSql = "DELETE FROM user_report WHERE user_id = ?";

        try (PreparedStatement updateUserStmt = connection.prepareStatement(updateUserSql); PreparedStatement deleteReportStmt = connection.prepareStatement(deleteReportSql)) {
            connection.setAutoCommit(false);

            updateUserStmt.setInt(1, status);
            updateUserStmt.setString(2, userId);
            updateUserStmt.executeUpdate();

            if (status == 1) {
                deleteReportStmt.setString(1, userId);
                deleteReportStmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    public ArrayList<User> getReportedUsers() {
        ArrayList<User> reportedUsers = new ArrayList<>();
        String sql = "SELECT u.user_id, u.display_name, u.user_status, "
                + "GROUP_CONCAT(urr.user_reason_content) AS report_reasons, "
                + "GROUP_CONCAT(ur.user_reporter_id) AS reporter_ids, COUNT(ur.user_id) AS report_count "
                + "FROM user u "
                + "JOIN user_report ur ON u.user_id = ur.user_id "
                + "JOIN user_report_reason urr ON ur.report_reason_id = urr.user_reason_id "
                + "GROUP BY u.user_id";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> reportReasons = Arrays.asList(rs.getString("report_reasons").split(","));
                List<User> reporters = getReporters(rs.getString("reporter_ids"));
                User user = new User();
                user.setId(rs.getString("user_id"));
                user.setDisplayName(rs.getString("display_name"));
                user.setStatus(rs.getInt("user_status"));
                user.setReportReason(reportReasons);
                user.setReportSum(rs.getInt("report_count"));
                user.setReporter(reporters);
                reportedUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportedUsers;
    }

    private ArrayList<User> getReporters(String reporterIds) {
        ArrayList<User> reporters = new ArrayList<>();
        if (reporterIds != null && !reporterIds.isEmpty()) {
            String[] ids = reporterIds.split(",");
            for (String id : ids) {
                User reporter = UserDAO.INS.findUserById(id);
                reporters.add(reporter);
            }
        }
        return reporters;
    }

    public void deleteReportsByUserId(String userId) {
        String sql = "DELETE FROM user_report WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
 public int getReportSumByUserId(String userId) throws SQLException {
    String sql = "SELECT COUNT(*) AS report_sum FROM user_report WHERE user_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("report_sum");
        } else {
            throw new SQLException("User not found in user_report");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
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
                String reason = rs.getString("user_reason_content");
                reportReasons.add(reason);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportReasons;
    }
    public String getReportReasonContentByUserId(String userId) throws SQLException {
    String sql = "SELECT urr.user_reason_content " +
                 "FROM user_report ur " +
                 "JOIN user_report_reason urr ON ur.report_reason_id = urr.user_reason_id " +
                 "WHERE ur.user_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("user_reason_content");
        } else {
            throw new SQLException("Report reason not found for user with ID: " + userId);
        }
    }
}
    //    Check connection here 
    public static void main(String[] args) {
//        ArrayList<Blog> list = BlogDAO.INS.getListOfBlog();
//        for(Blog blog : list){
//            System.out.println(blog.getBillLink());
//        }
        // System.out.println(b.getDisplayName());
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter myFormatMinute = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String date = LocalDateTime.now().format(myFormat);
//        String date10mAfter = LocalDateTime.now().plusMinutes(10).format(myFormat);
//        Date d = Date.valueOf(LocalDateTime.now().toLocalTime().plusMinutes(10).format(myFormatMinute));

//        java.util.Date date = new java.util.Date();
//        Object param = new java.sql.Timestamp(date.getTime());
        java.util.Date date = new java.util.Date();
//        long t = date.getTime();
//        java.sql.Date sqlDate = new java.sql.Date(t);
//        java.sql.Time sqlTime = new java.sql.Time(t);
//        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);

        long t = date.getTime();
        long m = 10 * 60 * 1000;
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t + m);
        System.out.println(sqlTimestamp);
    }

}
