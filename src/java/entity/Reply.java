/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Reply {
    private int replyID;
    private String content;
    private int replyStatus;
    private String userID;
    private int commentID;
    private int replyLike;
    private Date replyDate;
    private String userPicture;
    private String userName;
}
