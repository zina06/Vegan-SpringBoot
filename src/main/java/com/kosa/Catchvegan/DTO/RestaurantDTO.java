package com.kosa.Catchvegan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    private Integer restaurantIdx;
    private String name;
    private String veganType;
    private String address;
    private String city;
    private String restaurantInfo;
    private String menu;
    private double longitude;
    private double latitude;
    private Integer limitPerson;
    private Integer reservePay;
    private String images;
}
