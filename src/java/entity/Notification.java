package entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Notification {

    private int id;
    private String title;
    private String message;
    private String type;
    private String userId;
    private Timestamp timestamp;
    private int blogId;

    public static long getDateDiff(Timestamp oldTs) {
        Timestamp now = new Timestamp(System.currentTimeMillis()); 
        long diffInMS = now.getTime() - oldTs.getTime();
        return TimeUnit.HOURS.convert(diffInMS, TimeUnit.MILLISECONDS);
    }
    
    public static long getDateDiffMinute(Timestamp oldTs) {
        Timestamp now = new Timestamp(System.currentTimeMillis()); 
        long diffInMS = now.getTime() - oldTs.getTime();
        return TimeUnit.MINUTES.convert(diffInMS, TimeUnit.MILLISECONDS);
    }
    
    public static void main(String[] args) {
        
        System.out.println(25/24);
    }
}
