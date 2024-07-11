/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
public class BlogInfo {

    private int blogID;
    private String infoValue;
    private int status; //Do later
}
