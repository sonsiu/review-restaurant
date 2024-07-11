/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.Eatery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author legion
 */
public class EateryMapper {

    private static EateryMapper INS;

    private EateryMapper() {
    }

    public static EateryMapper getInstance() {
        if (INS == null) {
            INS = new EateryMapper();
        }
        return INS;
    }

    public Eatery map(ResultSet rs) throws SQLException {
        return Eatery.builder()
                .eateryID(rs.getInt("eatery_id"))
                .name(rs.getString("eatery_name"))
                .image(convertToString(rs.getBytes("eatery_head_image")))
                .rate(rs.getFloat("eatery_rate"))
                .status(rs.getInt("eatery_status"))
                .phone(rs.getString("eatery_phone"))
                .location(rs.getString("location"))
                .mealID(rs.getInt("meal_id"))
                .priceRangeID(rs.getInt("pricerange_id"))
                .sizeID(rs.getInt("size_id"))
                .serviceID(rs.getInt("service_id"))
                .updatedByUserID(rs.getString("updated_by_mod_id"))
                .build();

    }

    public String convertToString(byte[] rs) {
        return Base64.getEncoder().encodeToString(rs);
    }
}
