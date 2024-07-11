/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author legion
 */
public class CommentMapper {

    private static CommentMapper INS;

    private CommentMapper() {
    }

    public static CommentMapper getInstance() {
        if (INS == null) {
            INS = new CommentMapper();
        }

        return INS;
    }

    public Comment map(ResultSet rs) throws SQLException {
        return Comment.builder()
                .commentID(rs.getInt("comment_id"))
                .content(rs.getString("content"))
                .userID(rs.getString("user_id"))
                .blogID(rs.getInt("blog_id"))
                .commentLike(rs.getInt("commentLike")) // Fetch and set the like count
                .commentDate(rs.getTimestamp("comment_date"))
                .userPicture(Base64.getEncoder().encodeToString(rs.getBytes("userPicture")))
                .userName(rs.getString("userName"))
                .build();
    }
}
