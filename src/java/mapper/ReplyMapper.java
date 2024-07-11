/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import entity.Reply;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author ACER
 */
public class ReplyMapper {

    private static ReplyMapper INS;

    private ReplyMapper() {
    }

    public static ReplyMapper getInstance() {
        if (INS == null) {
            INS = new ReplyMapper();
        }

        return INS;
    }

    public Reply map(ResultSet rs) throws SQLException {
        return Reply.builder()
                .replyID(rs.getInt("reply_id"))
                .content(rs.getString("content"))
                .userID(rs.getString("user_id"))
                .commentID(rs.getInt("comment_id"))
                .replyLike(rs.getInt("replyLike")) // Fetch and set the like count
                .replyDate(rs.getTimestamp("reply_date"))
                .userPicture(Base64.getEncoder().encodeToString(rs.getBytes("userPicture")))
                .userName(rs.getString("userName"))
                .build();
    }
}
