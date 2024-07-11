/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import entity.BlogImage;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import mapper.BlogImageMapper;

/**
 *
 * @author legion
 */
public class BlogImageDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static BlogImageDAO INS = new BlogImageDAO();

    public BlogImageDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public void addImage(String blogID, Collection<Part> parts, Part billPart) throws IOException {
        for (Part part : parts) {
            if (part != billPart && part.getName().equals("imageLink") && part.getSize() > 0) {
                String sql = "INSERT INTO `blog_image` (`blog_id`,`image_path`)VALUES(?,?);";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    InputStream inputStream = part.getInputStream();
                    ps.setInt(1, Integer.parseInt(blogID));
                    ps.setBlob(2, inputStream);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace(System.out);
                }
            }
        }

    }

    public ArrayList<BlogImage> getImagesByBlogID(int blogID) {
        ArrayList<BlogImage> images = new ArrayList<>();
        String sql = "SELECT * FROM blog_image where blog_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BlogImage image = BlogImageMapper.getInstance().map(rs);
                images.add(image);
            }
            return images;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public ArrayList<BlogImage> getFirstImageOfAllBlog() {
        ArrayList<BlogImage> images = new ArrayList<>();
        String sql = "SELECT b.blog_id, bi.image_id, bi.image_path\n"
                + "FROM blog b\n"
                + "JOIN blog_image bi ON b.blog_id = bi.blog_id\n"
                + "WHERE bi.image_id = (\n"
                + "    SELECT MIN(bi_inner.image_id)\n"
                + "    FROM blog_image bi_inner\n"
                + "    WHERE bi_inner.blog_id = b.blog_id\n"
                + ");";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BlogImage image = BlogImageMapper.getInstance().map(rs);
                images.add(image);
            }
            return images;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public boolean removeBlogImageByBlogID(String blogID) {
        String sql = "DELETE FROM blog_image WHERE blog_id = ? ";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(blogID));
            int rowsAffected = ps.executeUpdate();
            return rowsAffected >= 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    public static void main(String[] args) {
//        ArrayList<BlogImage> list = BlogImageDAO.INS.getFirstImageOfAllBlog();
//        for (BlogImage blog : list) {
//            System.out.println(blog.getImageID());
//        }
    }

}
