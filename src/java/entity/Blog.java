/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author legion
 */
@Builder
@Getter
@Setter
@ToString
public class Blog {
    
    private int blogID;
    private String userID;
    private String title;
    private String description;
    private Date date;
    private String billLink;
    private float rate;
    private int like;
    private int eateryID;
    private int status; //0 - On Verification , 1 - Approved , 2 - Rejected , 3- Reported 
    private String eateryLocation;
    private String eateryName;
    private String eateryAddress;
    private List<User> reporter;
    private List<String> reportReason;
    private int reportSum;
    private int foodQualityRate;
    private int environmentRate;
    private int pricingRate;
    private int serviceRate;
    private int hygienRate;
    private int commentCount;
}
