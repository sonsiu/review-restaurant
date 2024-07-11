/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.Blog;
import entity.User;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mapper.BlogMapper;

/**
 *
 * @author legion
 */
public class BlogDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static BlogDAO INS = new BlogDAO();

    public BlogDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public int getStaticBlog() {
        int value = 0;

        String sql = " select count(*) from blog";
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

    private static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }

    public String[][] getDataCharts() {
        String[][] data = null;

        String sql = " SELECT MONTH(blog_date) AS month, COUNT(*) AS blog_count FROM blog GROUP BY MONTH(blog_date) ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            ArrayList<String[]> dataList = new ArrayList<>();
            while (rs.next()) {
                int month = rs.getInt("month");
                String monthName = getMonthName(month);
                String postCount = String.valueOf(rs.getInt("blog_count"));
                String[] row = {monthName, postCount};
                dataList.add(row);
            }

            data = new String[dataList.size()][];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }

//    public ArrayList<Blog> getBlog() {
//        ArrayList<Blog> list = new ArrayList();
//        String sql = "SELECT * FROM `blog`";
//        try {
//            PreparedStatement stm = connection.prepareStatement(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                list.add(BlogMapper.getInstance().map(rs));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//            return null;
//        }
//        return list;
//    }
    public ArrayList<Blog> getBlog() {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT b.*, COUNT(br.blog_id) AS report_count "
                + "FROM blog b "
                + "LEFT JOIN blog_report br ON b.blog_id = br.blog_id "
                + "GROUP BY b.blog_id";

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Blog blog = BlogMapper.getInstance().map(rs);
                blog.setReportSum(rs.getInt("report_count"));
                list.add(blog);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }

        return list;
    }

    public boolean createBlog(String userID, String title, String description, InputStream billImage,
            float rate, String eateryName, String eateryLocation, String eateryAddress,
            int foodQuality, int enviroment, int service, int pricing, int hygiene) {
        String sql = "INSERT INTO `blog`\n"
                + "(\n"
                + "`user_id`,\n"
                + "`blog_title`,\n"
                + "`blog_content`,\n"
                + "`blog_bill_image`,\n"
                + "`blog_rate`,\n"
                + "`blog_like`,\n"
                + "`eatery_id`,\n"
                + "`blog_status`,\n"
                + "`eatery_name_detail`,\n"
                + "`eatery_location_detail`,\n"
                + "`eatery_address_detail`,\n"
                + "`food_quality_rate`,\n"
                + "`environment_rate`,\n"
                + "`service_rate`,\n"
                + "`pricing_rate`,\n"
                + "`hygiene_rate`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,0,1,0,?,?,?,?,?,?,?,?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setBlob(4, billImage);
            ps.setFloat(5, rate);
            ps.setString(6, eateryName);
            ps.setString(7, eateryLocation);
            ps.setString(8, eateryAddress);
            ps.setInt(9, foodQuality);
            ps.setInt(10, enviroment);
            ps.setInt(11, service);
            ps.setInt(12, pricing);
            ps.setInt(13, hygiene);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public boolean updateBlog(String blogID, String userID, String title, String description, InputStream billImage,
            float rate, String eateryLocation, String eateryAddress,
            int foodQuality, int enviroment, int service, int pricing, int hygiene) {
        String sql = "UPDATE `swp391`.`blog`\n"
                + "SET\n"
                + "`user_id` = ?,\n"
                + "`blog_title` = ?,\n"
                + "`blog_content` = ?,\n"
                + "`blog_bill_image` = ?,\n"
                + "`eatery_location_detail` = ?,\n"
                + "`eatery_address_detail` = ?,\n"
                + "`food_quality_rate` = ?,\n"
                + "`environment_rate` = ?,\n"
                + "`pricing_rate` = ?,\n"
                + "`service_rate` = ?,\n"
                + "`hygiene_rate` = ?,\n"
                + "`blog_rate` = ?\n"
                + "WHERE `blog_id` = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setBlob(4, billImage);
            ps.setString(5, eateryLocation);
            ps.setString(6, eateryAddress);
            ps.setInt(7, foodQuality);
            ps.setInt(8, enviroment);
            ps.setInt(9, pricing);
            ps.setInt(10, service);
            ps.setInt(11, hygiene);
            ps.setFloat(12, rate);
            ps.setInt(13, Integer.parseInt(blogID));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public float getRateStaticBlog() {
        float value = 0;

        String sql = " select avg(blog_rate) from blog";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                value = rs.getFloat(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return value;
    }

    //Return the newest blog id that was just created before (for the insertion of blog_imgae table)
    public int getNewestBlogID() {
        String sql = "SELECT blog_id FROM blog \n"
                + "order by blog_id desc limit 1;";
        int value = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            //ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                value = rs.getInt("blog_id");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return value;
    }

    public ArrayList<Blog> getListOfBlog() {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM blog where blog_status=0;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            //ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog a = BlogMapper.getInstance().map(rs);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public int countBlog(String userID) {
        String sql = "SELECT COUNT(*) as count FROM blog WHERE user_id = ? and blog_status = 1";
        int total = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return total;
    }

    public void deleteBlogRejected(int id) {

        try {
            Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "delete from blog_image where blog_id=" + id;
            stm.executeUpdate(sql);
            sql = "delete from blog where blog_id= " + id;
            stm.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println("del Error:" + e.getMessage());
        }

    }

  public ArrayList<Blog> getListOfBlogByUserID(String userID,String action) {
        String whereSql="AND blog_status IN (1,3) ;";
        ArrayList<Blog> list = new ArrayList<>();
        if ("approved".equals(action)) {
        whereSql = " AND blog_status = 1";
    } else if ("unapproved".equals(action)) {
        whereSql = " AND blog_status = 0";
    }else if ("report".equals(action)) {
        whereSql = " AND blog_status = 3";
    }
        
        String sql = "SELECT * FROM blog WHERE user_id = ?"
                +whereSql;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog a = BlogMapper.getInstance().map(rs);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public ArrayList<Blog> getListOfBlogByAnotherUserID(String userID) {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM blog WHERE user_id = ? AND blog_status IN (1,3);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog a = BlogMapper.getInstance().map(rs);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public List<Blog> searchBlogByTitle(String text) {
        List<Blog> blogList = new ArrayList<>();
        String sql = "SELECT * FROM blog WHERE blog_title LIKE ? AND blog_status IN (1, 3)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = BlogMapper.getInstance().map(rs);
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return blogList;
    }

    public Blog getBlogByID(int blogID) {
        String sql = "SELECT b.blog_id, b.user_id AS blog_author_id, b.blog_title, b.blog_content, b.blog_date, b.blog_bill_image, "
                + "b.blog_rate, b.blog_like, b.eatery_id, b.blog_status, b.eatery_name_detail, b.eatery_location_detail, "
                + "GROUP_CONCAT(brr.blog_reason_content) AS report_reasons, "
                + "GROUP_CONCAT(br.blog_reporter_id) AS reporter_ids, COUNT(br.blog_id) AS report_count "
                + "FROM blog b "
                + "LEFT JOIN blog_report br ON b.blog_id = br.blog_id "
                + "LEFT JOIN blog_report_reason brr ON br.report_reason_id = brr.blog_reason_id "
                + "WHERE b.blog_id = ? "
                + "GROUP BY b.blog_id;";
        Blog blog = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<String> reportReasons = rs.getString("report_reasons") != null ? Arrays.asList(rs.getString("report_reasons").split(",")) : new ArrayList<>();
                List<User> reporters = rs.getString("reporter_ids") != null ? getReporters(rs.getString("reporter_ids")) : new ArrayList<>();
                blog = Blog.builder()
                        .blogID(rs.getInt("blog_id"))
                        .userID(rs.getString("blog_author_id"))
                        .title(rs.getString("blog_title"))
                        .description(rs.getString("blog_content"))
                        .date(rs.getDate("blog_date"))
                        .billLink(rs.getString("blog_bill_image"))
                        .rate(rs.getFloat("blog_rate"))
                        .like(rs.getInt("blog_like"))
                        .eateryID(rs.getInt("eatery_id"))
                        .status(rs.getInt("blog_status"))
                        .eateryName(rs.getString("eatery_name_detail"))
                        .eateryLocation(rs.getString("eatery_location_detail"))
                        .reportReason(reportReasons)
                        .reportSum(rs.getInt("report_count"))
                        .reporter(reporters)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return blog;
    }

    public Blog getBlogDetailByID(int blogId) {
        String sql = "SELECT * FROM blog where blog_id = ?";
        Blog blog = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                blog = BlogMapper.getInstance().map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return blog;
    }

    public boolean blogIDExists(int blogID) {
        String sql = "SELECT 1 FROM blog WHERE blog_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public void updateBlogStatus(int blogID, int status) {

        String sql = "UPDATE `blog`\n"
                + "SET\n"
                + "`blog_status` = ?\n"
                + "WHERE `blog_id` = ? ;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, blogID);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public boolean blogIDVerfied(int blogID) {
        String sql = "SELECT 1 FROM blog WHERE blog_id = ? and blog_status IN (1, 3)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public ArrayList<Blog> getListOfBlogApproved() {
        ArrayList<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM blog WHERE blog_status IN (1,3) ORDER BY blog_id DESC;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            //ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog a = BlogMapper.getInstance().map(rs);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public boolean removeBlog(int blogID) {
        String sql = "DELETE FROM blog WHERE blog_id = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public boolean setBlogToRemoveStatus(int blogID) {
        //Set to 3 (removed)
        String sql = "UPDATE blog SET blog_status = 4 WHERE blog_id = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public ArrayList<Blog> getReportedBlogs() {
        ArrayList<Blog> reportedBlogs = new ArrayList<>();
        String sql = "SELECT b.blog_id, b.user_id AS blog_author_id, b.blog_title, b.blog_content, b.blog_date, b.blog_bill_image, "
                + "b.blog_rate, b.blog_like, b.eatery_id, b.blog_status, b.eatery_name_detail, b.eatery_location_detail, "
                + "GROUP_CONCAT(brr.blog_reason_content) AS report_reasons, "
                + "GROUP_CONCAT(br.blog_reporter_id) AS reporter_ids, COUNT(br.blog_id) AS report_count "
                + "FROM blog b "
                + "JOIN blog_report br ON b.blog_id = br.blog_id "
                + "JOIN blog_report_reason brr ON br.report_reason_id = brr.blog_reason_id "
                + "GROUP BY b.blog_id "
                + "HAVING b.blog_status = 3;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> reportReasons = Arrays.asList(rs.getString("report_reasons").split(","));
                List<User> reporters = getReporters(rs.getString("reporter_ids"));
                Blog blog = Blog.builder()
                        .blogID(rs.getInt("blog_id"))
                        .userID(rs.getString("blog_author_id"))
                        .title(rs.getString("blog_title"))
                        .description(rs.getString("blog_content"))
                        .date(rs.getDate("blog_date"))
                        .billLink(rs.getString("blog_bill_image"))
                        .rate(rs.getFloat("blog_rate"))
                        .like(rs.getInt("blog_like"))
                        .eateryID(rs.getInt("eatery_id"))
                        .status(rs.getInt("blog_status"))
                        .eateryName(rs.getString("eatery_name_detail"))
                        .eateryLocation(rs.getString("eatery_location_detail"))
                        .reportReason(reportReasons)
                        .reportSum(rs.getInt("report_count"))
                        .reporter(reporters)
                        .build();
                reportedBlogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return reportedBlogs;
    }

    public ArrayList<User> getReporters(String reporterIds) {
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

    public List<String> getAllReportReasons() {
        List<String> reportReasons = new ArrayList<>();
        String sql = "SELECT blog_reason_content FROM blog_report_reason;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reportReasons.add(rs.getString("blog_reason_content"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return reportReasons;
    }

    public int getReportReasonID(String reportReason) throws SQLException {
        String sql = "SELECT blog_reason_id FROM blog_report_reason WHERE blog_reason_content = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reportReason);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("blog_reason_id");
            } else {
                throw new SQLException("Report reason not found");
            }
        }
    }

    public void reportBlog(int blogID, int reportReasonID, String blogAuthorID, String blogReporterID) throws SQLException {
        String insertSql = "INSERT INTO blog_report (blog_id, report_reason_id, blog_author_id, blog_reporter_id) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE blog SET blog_status = 3 WHERE blog_id = ?";
        try (PreparedStatement insertPs = connection.prepareStatement(insertSql); PreparedStatement updatePs = connection.prepareStatement(updateSql)) {
            insertPs.setInt(1, blogID);
            insertPs.setInt(2, reportReasonID);
            insertPs.setString(3, blogAuthorID);
            insertPs.setString(4, blogReporterID);
            insertPs.executeUpdate();

            updatePs.setInt(1, blogID);
            updatePs.executeUpdate();
        }
    }

    public boolean hasUserReportedBlog(String userId, int blogId) {
        String sql = "SELECT 1 FROM blog_report WHERE blog_reporter_id = ? AND blog_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.setInt(2, blogId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public int addReportReason(String reportReason) throws SQLException {
        String insertSql = "INSERT INTO blog_report_reason (blog_reason_content) VALUES (?)";
        String selectSql = "SELECT blog_reason_id FROM blog_report_reason WHERE blog_reason_content = ?";
        try (PreparedStatement insertPs = connection.prepareStatement(insertSql); PreparedStatement selectPs = connection.prepareStatement(selectSql)) {
            insertPs.setString(1, reportReason);
            insertPs.executeUpdate();

            selectPs.setString(1, reportReason);
            ResultSet rs = selectPs.executeQuery();
            if (rs.next()) {
                return rs.getInt("blog_reason_id");
            } else {
                throw new SQLException("Failed to retrieve new report reason ID");
            }
        }
    }

    public void handleBlogReport(int blogID, int status) throws SQLException {
        String updateBlogSql = "UPDATE blog SET blog_status = ? WHERE blog_id = ?";
        String deleteReportSql = "DELETE FROM blog_report WHERE blog_id = ?";

        try (PreparedStatement updateBlogStmt = connection.prepareStatement(updateBlogSql); PreparedStatement deleteReportStmt = connection.prepareStatement(deleteReportSql)) {

            connection.setAutoCommit(false); // Start transaction

            updateBlogStmt.setInt(1, status);
            updateBlogStmt.setInt(2, blogID);
            updateBlogStmt.executeUpdate();

            deleteReportStmt.setInt(1, blogID);
            deleteReportStmt.executeUpdate();

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            connection.rollback(); // Rollback transaction on error
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit mode
        }
    }

    public List<Blog> getBookmarksByUserID(String userId) {
        List<Blog> bloglist = new ArrayList<>();
        String sql = "SELECT b.* FROM bookmarks bm JOIN blog b ON bm.blog_id = b.blog_id WHERE bm.bookmark_by_user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = BlogMapper.getInstance().map(rs);
                    bloglist.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloglist;
    }

    public ArrayList<Blog> getBlogListByHashTag(String hashtag) {
        ArrayList<Blog> bloglist = new ArrayList<>();
        String sql = "SELECT b.* FROM blog b JOIN blog_hash_tag h ON b.blog_id = h.blog_id WHERE h.hash_tag_name LIKE ? AND (b.blog_status = 1 OR b.blog_status = 3)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hashtag);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = BlogMapper.getInstance().map(rs);
                    bloglist.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloglist;
    }

    public ArrayList<Blog> getFeatruredBlog(String categories) {
        String whereSql = "where b.blog_status=1 OR b.blog_status = 3 ORDER BY b.blog_id DESC;";

        if (categories != null) {
            whereSql = "where bm.meal_type_name like ? AND (b.blog_status = 1 OR b.blog_status = 3) ORDER BY b.blog_id DESC;";
        }
        ArrayList<Blog> list = new ArrayList<>();

        String sql = "select DISTINCT b.* from blog b join blog_meal_type bm on b.blog_id=bm.blog_id "
                + whereSql;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            if (categories != null && !categories.isEmpty()) {
                ps.setString(1, "%" + categories + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = BlogMapper.getInstance().map(rs);
                    list.add(blog);
                }
            }

        } catch (Exception e) {
            System.out.println("getlist Error:" + e.getMessage());
        }
        return list;
    }

    public ArrayList<Blog> getFollowBlog(String userid) {
        ArrayList<Blog> bloglist = new ArrayList<>();
        String sql = "SELECT b.* FROM blog b join follow f on b.user_id=f.following_user_id WHERE  f.follower_user_id = ? AND b.blog_status IN (1,3)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userid);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = BlogMapper.getInstance().map(rs);
                    bloglist.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloglist;
    }

    public ArrayList<Blog> getNearestBlog(String userId) {
        ArrayList<Blog> bloglist = new ArrayList<>();
        String sql = "SELECT b.* FROM blog b join `user` u on b.eatery_location_detail = u.location where u.user_id = ? AND b.blog_status IN (1,3)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = BlogMapper.getInstance().map(rs);
                    bloglist.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloglist;
    }

    public ArrayList<Blog> getBlogByAdvancedSearch(String searchInput, ArrayList<String> mealType, ArrayList<String> foodType, ArrayList<String> priceRange, ArrayList<String> specialDiet) {
        ArrayList<Blog> bloglist = new ArrayList<>();
        String sql = "WITH filter_blogs AS ( \n"
                + "SELECT distinct b.blog_id as id from blog b\n"
                + "JOIN blog_food_type bf ON bf.blog_id = b.blog_id\n"
                + "JOIN blog_meal_type bm ON bm.blog_id = b.blog_id\n"
                + "JOIN blog_price_range br on br.blog_id = b.blog_id\n"
                + "JOIN blog_special_diet_type bs on bs.blog_id = b.blog_id\n"
                + "WHERE 1=1 AND b.blog_title LIKE ? AND b.blog_status IN (1,3)  ";

        sql += insertIntoAdvancedSearchSQLQuery(mealType, "meal");
        sql += insertIntoAdvancedSearchSQLQuery(foodType, "food");
        sql += insertIntoAdvancedSearchSQLQuery(priceRange, "price");
        sql += insertIntoAdvancedSearchSQLQuery(specialDiet, "special");

        sql += " ) \n"
                + "select b.* from blog b join filter_blogs on b.blog_id = filter_blogs.id;";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + searchInput + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog blog = BlogMapper.getInstance().map(rs);
                    bloglist.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloglist;
    }

    public String insertIntoAdvancedSearchSQLQuery(ArrayList<String> stringArray, String type) {

        if (stringArray == null || stringArray.isEmpty()) {
            return ""; // Return empty string for empty input
        }

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" AND ");

        String columnName;
        switch (type) {
            case "meal":
                columnName = "bm.meal_type_name";
                break;
            case "food":
                columnName = "bf.food_type_name";
                break;
            case "price":
                columnName = "br.price_range_value";
                break;
            case "special":
                columnName = "bs.special_diet_name";
                break;
            default:
                return ""; // Return empty string for invalid type
        }

        sqlBuilder.append(columnName).append(" IN (");

        boolean firstValue = true;
        for (String value : stringArray) {
            if (!firstValue) {
                sqlBuilder.append(", ");
            }
            sqlBuilder.append("'").append(value).append("'");
            firstValue = false;
        }

        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }

    //    Check connection here 
//    public static void main(String[] args) {
//        ArrayList<Blog> list = BlogDAO.INS.getListOfBlog();
//        for (Blog blog : list) {
//            System.out.println(blog.getBillLink());
//        }
//        Blog b = BlogDAO.INS.getBlogByID(71);
//        System.out.println(b.getHygienRate());
//        
//    }
}
