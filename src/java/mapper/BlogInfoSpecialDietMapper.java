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
public class BlogInfoSpecialDietMapper {
    private static BlogInfoSpecialDietMapper INS;

    private BlogInfoSpecialDietMapper() {
    }

    public static BlogInfoSpecialDietMapper getInstance() {
        if (INS == null) {
            INS = new BlogInfoSpecialDietMapper();
        }

        return INS;
    }

    public BlogInfo map(ResultSet rs) throws SQLException {
        return BlogInfo.builder()
                .blogID(rs.getInt("blog_id"))
                .infoValue(Service.translateToVietnamese(rs.getString("special_diet_name")))
                .build();
    }
}
