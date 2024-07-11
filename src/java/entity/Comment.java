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
 * @author ACER
 */
@Builder
@Getter
@Setter
@ToString
public class Comment {
    private int commentID;
    private String content;
    private int commentStatus;
    private String userID;
    private int blogID;
    private int commentLike;
    private Date commentDate;
    private String userPicture;
    private String userName;
    private List<Reply> replies;
}
