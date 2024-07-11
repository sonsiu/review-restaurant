/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.BlogImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author legion
 */
public class BlogImageMapper {

    private static BlogImageMapper INS;

    private BlogImageMapper() {
    }

    public static BlogImageMapper getInstance() {
        if (INS == null) {
            INS = new BlogImageMapper();
        }
        return INS;
    }

    public BlogImage map(ResultSet rs) throws SQLException {
        return BlogImage.builder()
                .imageID(Integer.toString(rs.getInt("image_id")))
                .blogID(Integer.toString(rs.getInt("blog_id")))
                .imageLink(Base64.getEncoder().encodeToString(rs.getBytes("image_path")))
                .build();
    }

}
