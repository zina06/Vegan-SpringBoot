package com.kosa.Catchvegan.DTO;

import lombok.*;

@Generated
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
    private String restaurantPhone;
}
