/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.Blog;
import entity.BlogInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mapper.BlogInfoFoodTypeMapper;
import mapper.BlogInfoHashTagMapper;
import mapper.BlogInfoMealTypeMapper;
import mapper.BlogInfoPriceRangeMapper;
import mapper.BlogInfoSpecialDietMapper;
import mapper.BlogMapper;

/**
 *
 * @author legion
 */
public class BlogInfoDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static BlogInfoDAO INS = new BlogInfoDAO();

    public BlogInfoDAO() {
        if (this.INS == null)
            try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public void addCategoryType(String blogID, String name, String type) {
        String sql = "";
        switch (type) {
            case "meal" ->
                sql = "INSERT INTO `blog_meal_type`\n"
                        + "(`blog_id`,\n"
                        + "`meal_type_name`)\n"
                        + "VALUES\n"
                        + "(?,?);";
            case "food" ->
                sql = "INSERT INTO `blog_food_type`\n"
                        + "(`blog_id`,\n"
                        + "`food_type_name`)\n"
                        + "VALUES\n"
                        + "(?,?);";
            case "special" ->
                sql = "INSERT INTO `blog_special_diet_type`\n"
                        + "(`blog_id`,\n"
                        + "`special_diet_name`)\n"
                        + "VALUES\n"
                        + "(?,?);";
            case "price" ->
                sql = "INSERT INTO `blog_price_range`\n"
                        + "(`blog_id`,\n"
                        + "`price_range_value`)\n"
                        + "VALUES\n"
                        + "(?,?);";
            case "hashtag" ->
                sql = "INSERT INTO `blog_hash_tag`\n"
                        + "(`blog_id`,\n"
                        + "`hash_tag_name`)\n"
                        + "VALUES\n"
                        + "(?,?);";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(blogID));
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public boolean removeType(String blogID, String type) {
        String sql = "";
        switch (type) {
            case "meal" ->
                sql = "DELETE FROM `blog_meal_type`\n"
                        + "WHERE blog_id = ?;";
            case "food" ->
                sql = "DELETE FROM `blog_food_type`\n"
                        + "WHERE blog_id = ?;";
            case "special" ->
                sql = "DELETE FROM `blog_special_diet_type`\n"
                        + "WHERE blog_id = ?;";
            case "price" ->
                sql = "DELETE FROM `blog_price_range`\n"
                        + "WHERE blog_id = ?;";
            case "hashtag" ->
                sql = "DELETE FROM `blog_hash_tag`\n"
                        + "WHERE blog_id = ?;";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(blogID));
            int rowsAffected = ps.executeUpdate();
            return rowsAffected >= 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public ArrayList<BlogInfo> getType(int blogID, String type) {
        String sql = "";
        switch (type) {
            case "meal" ->
                sql = "SELECT * FROM `blog_meal_type`\n"
                        + "WHERE blog_id = ?;";
            case "food" ->
                sql = "SELECT * FROM `blog_food_type`\n"
                        + "WHERE blog_id = ?;";
            case "special" ->
                sql = "SELECT * FROM `blog_special_diet_type`\n"
                        + "WHERE blog_id = ?;";
            case "price" ->
                sql = "SELECT * FROM `blog_price_range`\n"
                        + "WHERE blog_id = ?;";
            case "hashtag" ->
                sql = "SELECT * FROM `blog_hash_tag`\n"
                        + "WHERE blog_id = ?;";
        }

        ArrayList<BlogInfo> list = new ArrayList<BlogInfo>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                switch (type) {
                    case "meal" ->
                        list.add(BlogInfoMealTypeMapper.getInstance().map(rs));
                    case "food" ->
                        list.add(BlogInfoFoodTypeMapper.getInstance().map(rs));
                    case "special" ->
                        list.add(BlogInfoSpecialDietMapper.getInstance().map(rs));
                    case "price" ->
                        list.add(BlogInfoPriceRangeMapper.getInstance().map(rs));
                    case "hashtag" ->
                        list.add(BlogInfoHashTagMapper.getInstance().map(rs));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public int countType(String type, String value) {
        String sql = "";
        switch (type) {
            case "meal" ->
                sql = "SELECT count(*) FROM `blog_meal_type` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE meal_type_name = ? AND b.blog_status IN (1,3);";
            case "food" ->
                sql = "SELECT count(*) FROM `blog_food_type` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE food_type_name = ? AND b.blog_status IN (1,3);";
            case "special" ->
                sql = "SELECT count(*) FROM `blog_special_diet_type` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE special_diet_name = ? AND b.blog_status IN (1,3);";
            case "price" ->
                sql = "SELECT count(*) FROM `blog_price_range` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE price_range_value = ? AND b.blog_status IN (1,3);";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public ArrayList<String> getTagsByBlogID(int blogID) {
        String sql = "SELECT * FROM `blog_hash_tag` WHERE blog_id = ?;";
        ArrayList<String> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("hash_tag_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public ArrayList<String> getDistinctTag(String txt) {
        ArrayList<String> tags = new ArrayList<>();
        String sql = "SELECT DISTINCT hash_tag_name\n"
                + "FROM blog_hash_tag WHERE hash_tag_name LIKE ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tags.add(rs.getString("hash_tag_name"));
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public ArrayList<BlogInfo> getAllDistinctTag() {
        ArrayList<BlogInfo> tags = new ArrayList<>();
        String sql = "SELECT DISTINCT hash_tag_name\n"
                + "FROM blog_hash_tag;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BlogInfo info = BlogInfoHashTagMapper.getInstance().map(rs);
                tags.add(info);
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public ArrayList<Blog> getCategory(String value, String type) {
        String sql = "";
        switch (type) {
            case "meal" ->
                sql = "SELECT b.* FROM `blog_meal_type` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE meal_type_name = ? AND b.blog_status IN (1,3);";
            case "food" ->
                sql = "SELECT b.* FROM `blog_food_type` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE food_type_name = ? AND b.blog_status IN (1,3);";
            case "special" ->
                sql = "SELECT b.* FROM `blog_special_diet_type` bm JOIN blog b ON b.blog_id = bm.blog_id\n"
                        + "WHERE special_diet_name = ? AND b.blog_status IN (1,3);";

        }

        ArrayList<Blog> list = new ArrayList<Blog>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(BlogMapper.getInstance().map(rs));

            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<BlogInfo> list = BlogInfoDAO.INS.getType(71, "hashtag");
        for (BlogInfo blog : list) {
            System.out.println(blog.getInfoValue());
        }

    }
}
