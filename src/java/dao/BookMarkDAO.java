package dao;

import connection.DBContext;
import entity.BookMark;
import entity.Notification;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author legion
 */
public class BookMarkDAO extends DBContext {

    private Connection con;
    private String status = "OK";
    public static BookMarkDAO INS = new BookMarkDAO();

    public BookMarkDAO() {
        if (this.INS == null)
        try {
            con = connection;
        } catch (Exception e) {
            status = "Error at connection" + e.getMessage();
        } else {
            INS = this;
        }
    }

    public void insertBookMark(String userID, int blogID) {
        String sql = "INSERT INTO bookmarks(bookmark_by_user_id, blog_id) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            ps.setInt(2, blogID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeBookMark(String userID, int blogID) throws SQLException {
        String sql = "DELETE FROM bookmarks WHERE bookmark_by_user_id = ? AND blog_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userID);
            statement.setInt(2, blogID);
            statement.executeUpdate();
        }
    }
//      public boolean isBookmarked(int blogID, String userID) throws SQLException {
//        String sql = "SELECT * FROM Bookmarks WHERE blogID = ? AND bookmark_by_user_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, blogID);
//            statement.setString(2, userID);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                return resultSet.next();
//            }
//        }
//    }

    public boolean isBookmarked(int blogID, String userID) {
        String sql = "SELECT COUNT(*) AS bookmark_count FROM bookmarks WHERE blog_id = ? AND bookmark_by_user_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, blogID);
            ps.setString(2, userID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("bookmark_count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
