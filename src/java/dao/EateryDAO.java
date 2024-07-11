/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.Eatery;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mapper.BlogMapper;
import mapper.EateryMapper;

/**
 *
 * @author legion
 */
public class EateryDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static EateryDAO INS = new EateryDAO();

    public EateryDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public ArrayList<Eatery> getListBySearch(String txt) {
        ArrayList<Eatery> list = new ArrayList<>();
        String sql = "SELECT * FROM eatery WHERE eatery_name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Eatery eatery = EateryMapper.getInstance().map(rs);
                list.add(eatery);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public boolean addEatery(String name, String location, InputStream image, String modID) {

        String sql = "INSERT INTO `eatery`\n"
                + "(`eatery_name`,\n"
                + "`eatery_head_image`,\n"
                + "`eatery_rate`,\n"
                + "`eatery_status`,\n"
                + "`eatery_phone`,\n"
                + "`location`,\n"
                + "`meal_id`,\n"
                + "`pricerange_id`,\n"
                + "`size_id`,\n"
                + "`service_id`,\n"
                + "`updated_by_mod_id`)\n"
                + "VALUES\n"
                + "(?,?,5,1,3434343,?,1,1,1,1,?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setBlob(2, image);
            ps.setString(3, location);
            ps.setString(4, modID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public ArrayList<Eatery> getListOfEatery() {
        ArrayList<Eatery> list = new ArrayList<>();
        String sql = "SELECT * FROM eatery;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            //ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Eatery a = EateryMapper.getInstance().map(rs);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public ArrayList<Eatery> getListOfEateryByModID(String modID) {
        ArrayList<Eatery> list = new ArrayList<>();
        String sql = "SELECT * FROM eatery WHERE updated_by_mod_id = ? ;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, modID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Eatery a = EateryMapper.getInstance().map(rs);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public void removeEatery(int id) {
        String sql = "DELETE FROM `eatery`\n"
                + "WHERE eatery_id = ?;";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
//        ArrayList<Eatery> list = EateryDAO.INS.getListBySearch("f");
//        for (Eatery blog : list) {
//            System.out.println(blog.getName());
//        }
//        Blog b = BlogDAO.INS.getBlogByID(3);
//        System.out.println(b.getTitle());
//        int id = BlogDAO.INS.getNewestBlogID();
//        System.out.println(id);
    }
}
