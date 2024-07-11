package entity;

import java.sql.Timestamp;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String displayName;
    private String username;
    private String passsword;
    private String profilePicture;
    private String email;
    private String phone;
    private String location;
    private int role; // 0 = admin, 1 = modder, 2 = user
    private int status; //0 = not verified, 1 = verified, 2 = banned
    private String verifyCode;
    private Timestamp expDate;
    private int loginBy; //0 = normal, 1 = gmail
    private String follower;
    private String following;
       private List<User> reporter;
    private List<String> reportReason;
    private int reportSum;
}
