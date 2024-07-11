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
public class Eatery {
    private int eateryID;
    private String name;
    private String image;
    private float rate;
    private int status;
    private String phone;
    private String location;
    private int mealID;
    private int priceRangeID;
    private int sizeID;
    private int serviceID;
    private String updatedByUserID;
}
