/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.CommentLike;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author legion
 */
public class CommentLikeMapper {
    private static CommentLikeMapper INS;

    private CommentLikeMapper() {
    }

    public static CommentLikeMapper getInstance() {
        if (INS == null) {
            INS = new CommentLikeMapper();
        }

        return INS;
    }

    public CommentLike map(ResultSet rs) throws SQLException {
        return CommentLike.builder()
                .commentLikeID(rs.getInt("like_id"))
                .commentID(rs.getInt("comment_id"))
                .userID(rs.getString("user_id"))
                .build();
    }
}
