/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.BlogInfo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author legion
 */
public class BlogInfoHashTagMapper {
     private static BlogInfoHashTagMapper INS;

    private BlogInfoHashTagMapper() {
    }

    public static BlogInfoHashTagMapper getInstance() {
        if (INS == null) {
            INS = new BlogInfoHashTagMapper();
        }

        return INS;
    }

    public BlogInfo map(ResultSet rs) throws SQLException {
        return BlogInfo.builder()
                .blogID(rs.getInt("blog_id"))
                .infoValue(separateHashtag(rs.getString("hash_tag_name")))
                //.status(rs.getInt("status"))
                .build();
    }
    
    public static String separateHashtag(String input) {
        if (input.charAt(0) == '#') {
            return input.substring(1);
        }
        return input;
    }
}
