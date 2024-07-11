/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package consts;

/**
 *
 * @author legion
 */
public interface IConstants {

    public static int COOKIE_MAX_AGE = 3600 * 24 * 30;

    String IMAGE_USER_FOLDER_NAME = "images-user";
    String IMAGE_BLOG_FOLDER_NAME = "images-blog";

    //Default image for new user
    String IMAGE_USER_DEFAULT_PATH = "img/user-profile-default-image.png";
    //Default image for new eatery
    String IMAGE_EATERY_DEFAULT_PATH = "img/location-default-image.jpeg";

    //Contact Email\
    public static String CONTACT_EMAIL = "@gmail.com";
    public static String CONTACT_EMAIL_PASS = "";

    //^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$
    String PASSWORD_REGEX = ".*?";

    

    public static String APPROVED_TITLE_NOTIFICATION = "Bài Viết Của Bạn Đã Được Duyệt!";

    public static String REJECTED_TITLE_NOTIFICATION = "Bài Viết Của Bạn Đã Bị Từ Chối!";

    public static String APPROVED_MESSAGE_NOTIFICATION = "Cảm ơn bạn vì những đóng góp có ích và công tâm tới cộng đồng";
    
    public static String TO_REPORTER_APPROVE_TITLE = "Báo cáo của bạn đã được xử lí!";
    
    public static String TO_REPORTED_REPORT_TITLE = "Bài viết của của bạn đã bị báo cáo!";
    
    public static String TO_REPORTED_HIDDEN_TITLE = "Bài viết của bạn đã bị ẩn! ";
    
    public static String TO_REPORTER_APPROVE_MESSAGE = "Báo cáo của bạn đã được được tiếp nhận và xử lí, bài viết bạn báo cáo đã bị ẩn!";
    
    public static String TO_REPORTED_REPORT_MESSAGE = "Đã có ai đó báo cáo bài viết có tựa đề: ";
    
    public static String TO_REPORTED_HIDDEN_MESSAGE = "Bài viết của bạn đã bị ẩn vì: ";
    
}
