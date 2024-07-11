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
public class BlogInfoPriceRangeMapper {

    private static BlogInfoPriceRangeMapper INS;

    private BlogInfoPriceRangeMapper() {
    }

    public static BlogInfoPriceRangeMapper getInstance() {
        if (INS == null) {
            INS = new BlogInfoPriceRangeMapper();
        }

        return INS;
    }

    public BlogInfo map(ResultSet rs) throws SQLException {
        return BlogInfo.builder()
                .blogID(rs.getInt("blog_id"))
                .infoValue(rs.getString("price_range_value"))
                .build();
    }
}
