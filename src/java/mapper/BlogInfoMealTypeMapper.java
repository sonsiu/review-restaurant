/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.BlogInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import service.Service;

/**
 *
 * @author legion
 */
public class BlogInfoMealTypeMapper {
     private static BlogInfoMealTypeMapper INS;

    private BlogInfoMealTypeMapper() {
    }

    public static BlogInfoMealTypeMapper getInstance() {
        if (INS == null) {
            INS = new BlogInfoMealTypeMapper();
        }

        return INS;
    }

    public BlogInfo map(ResultSet rs) throws SQLException {
        return BlogInfo.builder()
                .blogID(rs.getInt("blog_id"))
                .infoValue(Service.translateToVietnamese(rs.getString("meal_type_name")))
                .build();
    }
}
