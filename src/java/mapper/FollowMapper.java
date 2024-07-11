/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.Follow;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author legion
 */
public class FollowMapper {
    private static FollowMapper INS;

    private FollowMapper() {
    }

    public static FollowMapper getInstance() {
        if (INS == null) {
            INS = new FollowMapper();
        }
        return INS;
    }
    
     public Follow map(ResultSet rs) throws SQLException {
        return Follow.builder()
                .followerID(rs.getString("follower_user_id"))
                .followingID(rs.getString("following_user_id"))
                .build();

    }
}
