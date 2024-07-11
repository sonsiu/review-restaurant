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
public class BlogInfoFoodTypeMapper {
     private static BlogInfoFoodTypeMapper INS;

    private BlogInfoFoodTypeMapper() {
    }

    public static BlogInfoFoodTypeMapper getInstance() {
        if (INS == null) {
            INS = new BlogInfoFoodTypeMapper();
        }

        return INS;
    }

    public BlogInfo map(ResultSet rs) throws SQLException {
        return BlogInfo.builder()
                .blogID(rs.getInt("blog_id"))
                .infoValue(Service.translateToVietnamese(rs.getString("food_type_name")))
                .build();
    }
}
