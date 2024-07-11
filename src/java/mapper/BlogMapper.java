/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.Blog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author legion
 */
public class BlogMapper {

    private static BlogMapper INS;

    private BlogMapper() {
    }

    public static BlogMapper getInstance() {
        if (INS == null) {
            INS = new BlogMapper();
        }

        return INS;
    }

    public Blog map(ResultSet rs) throws SQLException {
        return Blog.builder()
                .blogID(rs.getInt("blog_id"))
                .userID(rs.getString("user_id"))
                .title(rs.getString("blog_title"))
                .description(rs.getString("blog_content"))
                .date(rs.getDate("blog_date"))
                .billLink(Base64.getEncoder().encodeToString(rs.getBytes("blog_bill_image")))
                .rate(rs.getFloat("blog_rate"))
                .like(rs.getInt("blog_like"))
                .eateryID(rs.getInt("eatery_id"))
                .status(rs.getInt("blog_status"))
                .eateryLocation(rs.getString("eatery_location_detail"))
                .eateryName(rs.getString("eatery_name_detail"))
                .eateryAddress(rs.getString("eatery_address_detail"))
                .foodQualityRate(rs.getInt("food_quality_rate"))
                .environmentRate(rs.getInt("environment_rate"))
                .pricingRate(rs.getInt("pricing_rate"))
                .serviceRate(rs.getInt("service_rate"))
                .hygienRate(rs.getInt("hygiene_rate"))
                .build();
    }

}
