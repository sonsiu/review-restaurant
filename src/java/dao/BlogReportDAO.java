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
 * @author ADMIN
 */
public class BlogReportDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static BlogReportDAO INS = new BlogReportDAO();

    public BlogReportDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public ArrayList<String> getAllReporterIds(int blogId) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT blog_reporter_id FROM blog_report WHERE blog_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("blog_reporter_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<String> getAllReportReason(int blogId) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT blog_reason_content FROM blog_report x LEFT JOIN blog_report_reason y ON x.report_reason_id = y.blog_reason_id WHERE x.blog_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("blog_reason_content"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public List<User> getReportersByBlogID(int blogId) {
        List<User> reporters = new ArrayList<>();
        String sql = "SELECT DISTINCT blog_reporter_id FROM blog_report WHERE blog_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User reporter = UserDAO.INS.findUserById(rs.getString("blog_reporter_id"));
                reporters.add(reporter);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return reporters;
    }
}
